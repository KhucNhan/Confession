package khucnhan.project.confession.controller;

import khucnhan.project.confession.model.Post;
import khucnhan.project.confession.model.Tag;
import khucnhan.project.confession.service.PostService;
import khucnhan.project.confession.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final TagService tagService;

    @Autowired
    public PostController(PostService postService, TagService tagService) {
        this.postService = postService;
        this.tagService = tagService;
    }

    // ========================== NGƯỜI DÙNG ==========================

    // Tạo bài viết mới
    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        post.setCreateAt(new Timestamp(System.currentTimeMillis()));
        post.setViews(0);
        post.setEditToken(UUID.randomUUID().toString()); // Tạo token duy nhất
        Post savedPost = postService.save(post);
        return ResponseEntity.ok(savedPost);
    }

    // Lấy tất cả bài viết (không phân trang, cho người dùng)
    @GetMapping("")
    public String listPosts(Model model) {
        List<Post> posts = postService.findAllWithoutPaging();
        List<Tag> tags = tagService.findAll();

        model.addAttribute("posts", posts);
        model.addAttribute("tags", tags);

        return "/user/index";
    }

    // Lấy chi tiết bài viết
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Optional<Post> postOpt = postService.findById(id);
        if (postOpt.isPresent()) {
            Post post = postOpt.get();
            post.setViews(post.getViews() + 1); // Tăng lượt xem
            postService.save(post); // cập nhật lại lượt xem
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xoá bài viết (dựa vào token)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id, @RequestParam String token) {
        Optional<Post> postOpt = postService.findById(id);
        if (postOpt.isPresent()) {
            Post post = postOpt.get();
            if (post.getEditToken().equals(token)) {
                postService.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(403).build(); // Forbidden
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ========================== ADMIN ==========================

    // Phân trang bài viết (chỉ admin dùng)
    @GetMapping("/admin")
    public ResponseEntity<Page<Post>> getPostsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Post> postPage = postService.findAllWithPaging(PageRequest.of(page, size));
        return ResponseEntity.ok(postPage);
    }
}
