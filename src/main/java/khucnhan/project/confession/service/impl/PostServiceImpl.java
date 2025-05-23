package khucnhan.project.confession.service.impl;

import khucnhan.project.confession.model.Post;
import khucnhan.project.confession.repository.PostRepository;
import khucnhan.project.confession.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return (Post) postRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> findAllWithoutPaging() {
        return (List<Post>) postRepository.findAll(); // CrudRepository trả về Iterable
    }

    @Override
    public Page<Post> findAllWithPaging(Pageable pageable) {
        return postRepository.findAll(pageable); // PagingAndSortingRepository
    }

    @PersistenceContext
    private EntityManager entityManager;

    // 1. NEW: bài mới nhất
    public List<Post> findNewestPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    // 2. HOT: nhiều like + comment trong 3 ngày
    public List<Post> findHotPosts() {
        List<BigInteger> result = entityManager
                .createNativeQuery("CALL findHotPosts()")
                .getResultList();

        List<Long> ids = result.stream()
                .map(BigInteger::longValue)
                .collect(Collectors.toList());

        if (ids.isEmpty()) return List.of();

        return postRepository.findByPostIdIn(ids);
    }


    // 3. POPULAR: nhiều like nhất
    public List<Post> findPopularPosts() {
        List<BigInteger> result = entityManager
                .createNativeQuery("CALL findPopularPosts()")
                .getResultList();

        List<Long> ids = result.stream()
                .map(BigInteger::longValue)
                .collect(Collectors.toList());

        if (ids.isEmpty()) return List.of();

        return postRepository.findByPostIdIn(ids);
    }

    // 4. TRENDING: tương tác cao nhất trong 1 ngày gần đây (like - dislike + comment)
    public List<Post> findTrendingPosts() {
        List<BigInteger> result = entityManager
                .createNativeQuery("CALL findTrendingPosts()")
                .getResultList();

        List<Long> ids = result.stream()
                .map(BigInteger::longValue)
                .collect(Collectors.toList());

        if (ids.isEmpty()) return List.of();

        return postRepository.findByPostIdIn(ids);
    }
}
