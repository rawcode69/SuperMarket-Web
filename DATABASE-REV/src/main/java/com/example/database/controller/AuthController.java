package com.example.database.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.database.dto.LoginDTO;
import com.example.database.entity.User;
import com.example.database.repository.UserRepository;
import com.example.database.security.jwt.JwtUtils;
import com.example.database.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserService userService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtUtils jwtUtils;

  @GetMapping("/auth/login") // This is the path got acccess without login
  public String login() {
    return "Login is working without auth";
  }

  @PostMapping("/auth/register")
  public ResponseEntity<?> registerUser(@RequestBody User user) {

    if (userRepository.existsByUsername(user.getUsername())) {
      return ResponseEntity.badRequest().body("User name is already in use");
    }

    if (userRepository.existsByEmail(user.getEmail())) {
      return ResponseEntity.badRequest().body("Email is already in use");
    }

    User newUser = new User();
    newUser.setUsername(user.getUsername());
    newUser.setEmail(user.getEmail());
    newUser.setPassword(passwordEncoder.encode(user.getPassword()));
    newUser.setAddress(user.getAddress());

    return ResponseEntity.ok(userService.createUser(newUser));

  }

  @PostMapping("/auth/login")
  public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = jwtUtils.generateJwtToken(authentication);

    return ResponseEntity.ok(jwt);

  }

}
