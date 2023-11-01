package com.example.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.database.dto.ProductDTO;
import com.example.database.entity.Category;
import com.example.database.entity.Product;
import com.example.database.repository.CategoryRepository;
import com.example.database.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProudctById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(ProductDTO productDto) {

        Category category = categoryRepository.findById(productDto.getCategoryID()).orElse(null);

        if (category != null) {

            Product product = new Product();

            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setQty(productDto.getQty());
            product.setCategory(category);

            return productRepository.save(product);
        } else {
            return null;
        }
    }

    @Override
    public Product updateProduct(Long id, Product product) {

        Product existinProduct = getProudctById(id);

        if (existinProduct != null) {
            existinProduct.setName(product.getName());
            existinProduct.setQty(product.getQty());
            existinProduct.setPrice(product.getPrice());

            return productRepository.save(existinProduct);
        } else {
            return null;
        }
    }

    @Override
    public List<Product> getProductByCategory(Long id) {

        Category category = categoryRepository.findById(id).orElse(null);
       // System.out.println(category.getName());
        if (category != null) {
            return productRepository.findProductByCateogry(category);
        } else {
            return null;
        }
    }

}
