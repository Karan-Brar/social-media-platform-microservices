package com.example.userservice.service;

import com.example.userservice.misc.ErrorResponse;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new User
    public ResponseEntity<?> createUser(User user){
        try {
            User savedUser = userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (DuplicateKeyException e) {
            String errorMessage;
            // Handle duplicate key exception
            if (e.getMessage().contains("username")) {
                errorMessage = "User with username already exists";
            } else if (e.getMessage().contains("email")) {
                errorMessage = "User with email already exists";
            } else {
                errorMessage = "Duplicate key error";
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(errorMessage));
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
    public ResponseEntity<?> updateUsername(String id, String newUsername){
        Optional<User> existingUser = userRepository.findById(id);

        if(existingUser.isPresent()) {
         try{
             User user = existingUser.get();
             user.setUsername(newUsername);
             User savedUser = userRepository.save(user);
             return ResponseEntity.status(HttpStatus.OK).body(savedUser);
         }
         catch(DuplicateKeyException e) {
             String errorMessage = "User with username already exists";
             return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(errorMessage));
         }

        }

        String errorMessage = "User not found";
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(errorMessage));
    }

    // Update a user's e-mail
    public ResponseEntity<?> updateEmail(String id, String newEmail){
        Optional<User> existingUser = userRepository.findById(id);

        if(existingUser.isPresent()) {
            try{
                User user = existingUser.get();
                user.setEmail(newEmail);
                User savedUser = userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body(savedUser);
            }
            catch(DuplicateKeyException e) {
                String errorMessage = "User with e-mail already exists";
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(errorMessage));
            }
        }

        String errorMessage = "User not found";
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(errorMessage));
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
