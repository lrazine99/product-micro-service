package com.example.micrommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<ProductModel> getProductById(@PathVariable int id) {
        ProductModel product = productDao.findById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel product) {
        ProductModel savedProduct = productDao.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable int id, @RequestBody ProductModel product) {
        if (!productDao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        product.setId(id);
        ProductModel updatedProduct = productDao.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        if (!productDao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productDao.deleteById(id);
        return ResponseEntity.ok("{\"message\":\"Product with id " + id + " deleted\", \"status\":200}");
    }

    @PostMapping("/products/{id}/reduce-stock")
    public ResponseEntity<String> reduceStock(@PathVariable int id, @RequestBody int quantity) {
        boolean success = productDao.reduceStock(id, quantity);
        if (success) {
            return ResponseEntity.ok("{\"message\":\"Stock reduced successfully\", \"status\":200}");
        } else {
            return ResponseEntity.badRequest().body("{\"message\":\"Insufficient stock or product not found\", \"status\":400}");
        }
    }

    @GetMapping("/products/{id}/check-stock/{quantity}")
    public ResponseEntity<String> checkStock(@PathVariable int id, @PathVariable int quantity) {
        boolean hasStock = productDao.hasStock(id, quantity);
        return ResponseEntity.ok("{\"available\":" + hasStock + ", \"status\":200}");
    }
}
