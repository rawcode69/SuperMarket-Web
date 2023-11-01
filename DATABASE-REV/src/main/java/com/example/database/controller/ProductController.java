package com.example.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.database.dto.ProductDTO;
import com.example.database.entity.Product;
import com.example.database.service.ProductService;

@CrossOrigin(origins = "*")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productddto) {
        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productddto));

        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to create the product");
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {

        Product product = productService.getProudctById(id);

        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.status(200).body(productService.updateProduct(id, product));
    }

    @GetMapping("/categories/{id}/products")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable Long id){
        return ResponseEntity.status(200).body(productService.getProductByCategory(id));
    }
   
}
