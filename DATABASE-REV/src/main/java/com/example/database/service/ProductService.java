package com.example.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.database.dto.ProductDTO;
import com.example.database.entity.Product;

@Service
public interface ProductService {
    List<Product> getAllProducts();

    Product getProudctById(Long id);

    Product createProduct(ProductDTO productDto);

    Product updateProduct(Long id, Product product);

    List<Product> getProductByCategory(Long id);
}
