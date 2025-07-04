package com.example.micrommerce.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.micrommerce.dao.ProductDao;
import com.example.micrommerce.model.ProductModel;

@Service
public class StockConsumer {

    @Autowired
    private ProductDao productDao;

    @RabbitListener(queues = "${product.queue}")
    public void handleStockReduction(String message) {
        try {
            String[] parts = message.replace("{", "").replace("}", "").split(",");
            Integer productId = Integer.parseInt(parts[0].split(":")[1].trim());
            Integer quantity = Integer.parseInt(parts[1].split(":")[1].trim());
            
            ProductModel product = productDao.findById(productId);
            if (product != null && product.getQuantity() >= quantity) {
                product.setQuantity(product.getQuantity() - quantity);
                productDao.save(product);
                System.out.println("Stock reduced for product " + productId + " by " + quantity);
            }
        } catch (Exception e) {
            System.err.println("Error processing stock reduction: " + e.getMessage());
        }
    }
}