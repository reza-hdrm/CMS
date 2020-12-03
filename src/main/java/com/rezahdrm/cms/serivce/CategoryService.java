package com.rezahdrm.cms.serivce;

import com.rezahdrm.cms.model.Category;
import com.rezahdrm.cms.repositroy.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Category> categoryForCreatePost(){
        return categoryRepository.findALLCreatePost();
    }


    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public void save(Category category){
        categoryRepository.save(category);
    }

    public Object findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
