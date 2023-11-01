package com.example.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.database.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    
    //Add here some aditional queries when required

}
