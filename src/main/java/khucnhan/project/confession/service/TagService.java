package khucnhan.project.confession.service;

import khucnhan.project.confession.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Tag save(Tag tag);
    Optional<Tag> findById(long id);
    List<Tag> findAll();
    void deleteById(long id);
}
