package com.example.userservice.service;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new User
    public User createUser(User user){
        try {
            return userRepository.save(user);
        } catch (DuplicateKeyException e) {
            // Handle duplicate key exception
            if (e.getMessage().contains("username")) {
                throw new RuntimeException("Username already exists");
            } else if (e.getMessage().contains("email")) {
                throw new RuntimeException("Email already exists");
            } else {
                throw new RuntimeException("Duplicate key error");
            }
        }
    }

    // Get a user by ID
    public Optional<User> getUserbyId(String id){
        return userRepository.findById(id);
    }

    // Get a user by username
    public Optional<User> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    // Get a user by e-mail
    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    // Update a user's username
    public User updateUsername(String id, String newUsername){
        Optional<User> existingUser = userRepository.findById(id);

        if(existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(newUsername);
            return userRepository.save(user);
        }

        throw new RuntimeException("User Not Found");
    }

    // Update a user's e-mail
    public User updateEmail(String id, String newEmail){
        Optional<User> existingUser = userRepository.findById(id);

        if(existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(newEmail);
            return userRepository.save(user);
        }

        throw new RuntimeException("User Not Found");
    }

    // Update a user's password
    public User updatePassword(String id, String newPassword){
        Optional<User> existingUser = userRepository.findById(id);

        if(existingUser.isPresent()) {
            User user = existingUser.get();
            user.setPassword(newPassword);
            return userRepository.save(user);
        }

        throw new RuntimeException("User Not Found");
    }


    // Delete a user
    public void deleteUser(String id) {
        Optional<User> existingUser = userRepository.findById(id);

        if(existingUser.isPresent()){
            userRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("User Not Found");
        }
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
