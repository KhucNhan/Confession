package khucnhan.project.confession.service;

import khucnhan.project.confession.model.LikeDislike;

import java.util.List;
import java.util.Optional;

public interface LikeDislikeService {
    LikeDislike save(LikeDislike likeDislike);
    Optional<LikeDislike> findById(long id);
    List<LikeDislike> findByPostId(long postId);
    void deleteById(long id);
}
