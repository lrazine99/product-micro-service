package com.example.micrommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.micrommerce.model.ProductModel;

@Repository
public interface ProductInterface extends JpaRepository<ProductModel, Integer> {

    ProductModel findById(int id);

    void deleteById(int id);
}
