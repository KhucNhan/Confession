package khucnhan.project.confession.service;

import khucnhan.project.confession.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> findById(Long id);
    Post save(Post post);
    void deleteById(Long id);
    List<Post> findAllWithoutPaging();
    Page<Post> findAllWithPaging(Pageable pageable);

}

