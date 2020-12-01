package com.rezahdrm.cms.serivce;

import com.rezahdrm.cms.model.Category;
import com.rezahdrm.cms.repositroy.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Category> categoryForCreatePost(){
        return categoryRepository.findALLCreatePost();
    }


    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}
