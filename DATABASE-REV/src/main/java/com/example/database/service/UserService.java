package com.example.database.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.database.dto.UserUserNameDTO;
import com.example.database.entity.User;

@Service
public interface UserService {
    User createUser(User user);

    List<User> getAllUser();

    User getUserById(Long id);

    User updateUser(Long id, User user);

    User changeUserName(Long id, UserUserNameDTO userNameDTO);
}
