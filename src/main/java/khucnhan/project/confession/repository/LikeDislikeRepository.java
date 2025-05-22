package khucnhan.project.confession.repository;

import khucnhan.project.confession.model.Category;
import khucnhan.project.confession.model.LikeDislike;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikeDislikeRepository extends CrudRepository<LikeDislike,Long> {
    List<LikeDislike> findByPost_PostId(long postId);
}
