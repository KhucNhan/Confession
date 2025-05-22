package khucnhan.project.confession.repository;

import khucnhan.project.confession.model.Category;
import khucnhan.project.confession.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment,Long> {
    List<Comment> findByPost_PostId(long postId);
}
