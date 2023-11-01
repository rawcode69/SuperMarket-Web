package com.example.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.database.dto.UserUserNameDTO;
import com.example.database.entity.User;
import com.example.database.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, User user) {

        User existingUser = getUserById(id);

        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setAddress(user.getAddress());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    @Override
    public User changeUserName(Long id, UserUserNameDTO userUserNameDTO) {

        User existingUser = getUserById(id);

        if (existingUser != null) {
            existingUser.setUsername(userUserNameDTO.getUserName());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

}
