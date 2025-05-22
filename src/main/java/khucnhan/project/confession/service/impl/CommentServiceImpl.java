package khucnhan.project.confession.service.impl;

import khucnhan.project.confession.model.Comment;
import khucnhan.project.confession.repository.CommentRepository;
import khucnhan.project.confession.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment){
        return (Comment) commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> findById(long id){
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findByPostId(long postId){
        return commentRepository.findByPost_PostId(postId);
    }

    @Override
    public void deleteById(long id){
        commentRepository.deleteById(id);
    }

    @Override
    public void deleteByPostId(long postId){
        List<Comment> comments = commentRepository.findByPost_PostId(postId);
        commentRepository.deleteAll(comments);
    }
}
