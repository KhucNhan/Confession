package khucnhan.project.confession.service.impl;

import khucnhan.project.confession.model.LikeDislike;
import khucnhan.project.confession.repository.LikeDislikeRepository;
import khucnhan.project.confession.service.LikeDislikeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeDislikeServiceImpl implements LikeDislikeService {

    private final LikeDislikeRepository likeDislikeRepository;

    public LikeDislikeServiceImpl(LikeDislikeRepository likeDislikeRepository){
        this.likeDislikeRepository = likeDislikeRepository;
    }

    @Override
    public LikeDislike save(LikeDislike likeDislike){
        return (LikeDislike) likeDislikeRepository.save(likeDislike);
    }

    @Override
    public Optional<LikeDislike> findById(long id){
        return likeDislikeRepository.findById(id);
    }

    @Override
    public List<LikeDislike> findByPostId(long postId){
        return likeDislikeRepository.findByPost_PostId(postId);
    }

    @Override
    public void deleteById(long id){
        likeDislikeRepository.deleteById(id);
    }
}
