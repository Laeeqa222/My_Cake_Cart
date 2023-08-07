package com.Laeeq.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Laeeq.major.model.Category;
import com.Laeeq.major.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    CategoryRepository categoryRepository;
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public void addCategory(Category c){
        categoryRepository.save(c);
    }

    public void deleteCategoryById(int id){
        categoryRepository.deleteById(id);
    }

    public Optional<Category> updateCategoryById(int id){
        return categoryRepository.findById(id);
    }
}
