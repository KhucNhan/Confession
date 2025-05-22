package khucnhan.project.confession.service;

import khucnhan.project.confession.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment save(Comment comment);
    Optional<Comment> findById(long id);
    List<Comment> findByPostId(long postId);
    void deleteById(long id);
    void deleteByPostId(long postId);
}
