package com.example.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.database.entity.Category;
import com.example.database.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
   // Add custom queries in here.........

   @Query ("SELECT p from Product p WHERE p.category = :category")
   List<Product> findProductByCateogry(@Param("category") Category category);

}
