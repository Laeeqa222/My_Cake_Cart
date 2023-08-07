package com.Laeeq.major.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Laeeq.major.model.Product;



public interface ProductsRepository  extends JpaRepository<Product, Long>{
     List<Product> findProductsByCategory_Id(int id);
}
