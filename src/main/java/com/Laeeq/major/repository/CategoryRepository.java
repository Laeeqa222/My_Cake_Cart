package com.Laeeq.major.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Laeeq.major.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    
}
