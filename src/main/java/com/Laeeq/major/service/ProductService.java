package com.Laeeq.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Laeeq.major.model.Product;
import com.Laeeq.major.repository.ProductsRepository;

@Service
public class ProductService {
    @Autowired
    private ProductsRepository productsRepository;
    public List<Product> getAllProduct(){
        return productsRepository.findAll();
    }
    public void addProduct(Product pro){
        productsRepository.save(pro);
    }
    public void removeProduct(long id){
          productsRepository.deleteById(id);
    }
    public Optional<Product> findProductById(long id){
        return productsRepository.findById(id);
    }
    public List<Product> findProductsByCategoryId(int id){
        return productsRepository.findProductsByCategory_Id(id);
    }
}
