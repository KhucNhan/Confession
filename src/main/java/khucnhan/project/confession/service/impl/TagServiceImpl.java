package khucnhan.project.confession.service.impl;

import khucnhan.project.confession.model.Tag;
import khucnhan.project.confession.repository.TagRepository;
import khucnhan.project.confession.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag save(Tag tag){
        return (Tag) tagRepository.save(tag);
    }

    @Override
    public Optional<Tag> findById(long id){
        return tagRepository.findById(id);
    }

    @Override
    public List<Tag> findAll(){
        return (List<Tag>) tagRepository.findAll();
    }

    @Override
    public void deleteById(long id){
        tagRepository.deleteById(id);
    }
}
