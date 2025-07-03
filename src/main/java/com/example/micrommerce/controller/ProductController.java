package com.example.micrommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.micrommerce.dao.ProductDao;
import com.example.micrommerce.model.ProductModel;

@RestController
public class ProductController {

    @Autowired
    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/products")
    public List<ProductModel> getProducts() {
        System.out.println("Fetching all products" + productDao.findAll());
        return productDao.findAll();
    }

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable int id) {
        return "{\"message\":\"Hello World bonjour " + id + "\", \"status\":200}";
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable int id) {
        // Here you would typically call a service to delete the product by id
        return "{\"message\":\"Product with id " + id + " deleted\", \"status\":200}";
    }

    // @PostMapping("/products")
    // public ProductModel createProduct(@RequestBody ProductModel product) {
    // return productService.createProduct(product);
    // }
}
