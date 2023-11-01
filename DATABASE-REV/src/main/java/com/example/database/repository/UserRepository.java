package com.example.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.database.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // If it is required any customer query - - - add here
}
