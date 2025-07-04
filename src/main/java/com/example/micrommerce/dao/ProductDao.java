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

    /*
     * 
     * public ProductModel findById(int id) {
     * return products.stream()
     * .filter(product -> product.getId() == id)
     * .findFirst()
     * .orElse(null);
     * }
     * 
     * @Override
     * public ProductModel save(ProductModel product) {
     * if (product.getId() == 0) {
     * // Assign a new ID to the product
     * product.setId(products.size() + 1);
     * products.add(product);
     * } else {
     * // Update existing product
     * for (int i = 0; i < products.size(); i++) {
     * if (products.get(i).getId() == product.getId()) {
     * products.set(i, product);
     * break;
     * }
     * }
     * }
     * return product;
     * }
     * 
     * @Override
     * public void deleteById(int id) {
     * products.removeIf(product -> product.getId() == id);
     * }
     */
}
