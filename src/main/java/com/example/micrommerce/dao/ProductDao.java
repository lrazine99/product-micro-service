package com.example.micrommerce.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.micrommerce.model.ProductModel;

@Service
public class ProductDao {

    @Autowired
    private ProductRepositoryInterface productRepository;

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    public ProductModel findById(int id) {
        return productRepository.findById(id);
    }

    public ProductModel save(ProductModel product) {
        return productRepository.save(product);
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return productRepository.existsById(id);
    }
}
