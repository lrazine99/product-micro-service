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

    public boolean reduceStock(int productId, int quantity) {
        ProductModel product = productRepository.findById(productId);
        if (product != null && product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);
            return true;
        }
        return false;
    }

    public boolean hasStock(int productId, int quantity) {
        ProductModel product = productRepository.findById(productId);
        return product != null && product.getQuantity() >= quantity;
    }
}
