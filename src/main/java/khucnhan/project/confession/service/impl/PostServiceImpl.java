package khucnhan.project.confession.service.impl;

import khucnhan.project.confession.model.Post;
import khucnhan.project.confession.repository.PostRepository;
import khucnhan.project.confession.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
