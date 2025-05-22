package khucnhan.project.confession.service;

import khucnhan.project.confession.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category save(Category category);
    Optional<Category> findById(long id);
    List<Category> findAll();
    void deleteById(long id);
}
