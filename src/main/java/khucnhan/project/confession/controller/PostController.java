package khucnhan.project.confession.controller;

import khucnhan.project.confession.model.Post;
import khucnhan.project.confession.service.CategoryService;
import khucnhan.project.confession.service.PostService;
import khucnhan.project.confession.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final TagService tagService;
    private final CategoryService categoryService;
    private final HttpServletRequest request;

    public PostController(PostService postService, TagService tagService, CategoryService categoryService, HttpServletRequest request) {
        this.postService = postService;
        this.tagService = tagService;
        this.categoryService = categoryService;
        this.request = request;
    }

    // ========================== NGƯỜI DÙNG ==========================

    // Tạo bài viết mới
    @PostMapping("/create")
    public String createPost(@RequestBody Post post, Model model) {
        post.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        post.setViews(0);
        post.setEditToken(UUID.randomUUID().toString()); // Tạo token duy nhất
        Post savedPost = postService.save(post);
        model.addAttribute("token", savedPost.getEditToken());
        return "redirect:/posts";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("categories", categoryService.findAll());
        return "/user/create";
    }

    // Lấy tất cả bài viết (không phân trang, cho người dùng)
    @GetMapping("")
    public String listPosts(@RequestParam(defaultValue = "new") String sort, Model model) {
        List<Post> posts = switch (sort.toLowerCase()) {
            case "hot" -> postService.findHotPosts();
            case "popular" -> postService.findPopularPosts();
            case "trending" -> postService.findTrendingPosts();
            default -> postService.findNewestPosts();
        };

        model.addAttribute("posts", posts);
        model.addAttribute("sort", sort);
        model.addAttribute("tags", tagService.findAll());

        String requestedWith = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestedWith)) {
            // Nếu là AJAX request thì trả về fragment postsList thôi
            return "/fragments/postsList :: postsList";
        }

        // Load trang đầy đủ cho request thông thường
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
