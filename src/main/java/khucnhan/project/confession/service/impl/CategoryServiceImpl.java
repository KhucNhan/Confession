package khucnhan.project.confession.service.impl;

import khucnhan.project.confession.model.Category;
import khucnhan.project.confession.repository.CategoryRepository;
import khucnhan.project.confession.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category){
        return (Category) categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(long id){
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAll(){
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public void deleteById(long id){
        categoryRepository.deleteById(id);
    }
}
