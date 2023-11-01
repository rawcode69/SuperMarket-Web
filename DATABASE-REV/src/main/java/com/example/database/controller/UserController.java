package com.example.database.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.database.dto.UserUserNameDTO;
import com.example.database.entity.User;
import com.example.database.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @PutMapping("/users/{id}/change-userName")
    public ResponseEntity<User> changeUserName (@PathVariable Long id, @RequestBody UserUserNameDTO userNameDTO){
        return ResponseEntity.status(200).body(userService.changeUserName(id, userNameDTO));
    }
}
