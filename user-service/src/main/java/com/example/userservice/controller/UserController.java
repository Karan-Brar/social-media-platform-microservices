package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST endpoint to create a new user
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    // GET endpoint to obtain an existing user by id
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable String id){
        return userService.getUserbyId(id);
    }

    // GET endpoint to obtain an existing user by username
    @GetMapping("/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    // GET endpoint to obtain an existing user by email3
    @GetMapping("/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    // PATCH endpoint to update the username of an existing user
    @PatchMapping("{id}/username")
    public ResponseEntity<?> updateUsername(@PathVariable String id, @RequestBody Map<String, String> request){
        String newUsername = request.get("newUsername");
        return userService.updateUsername(id, newUsername);
    }

    // PATCH endpoint to update the email of an existing user
    @PatchMapping("{id}/email")
    public User updateEmail(@PathVariable String id, @RequestBody Map<String, String> request){
        String newEmail = request.get("newEmail");
        return userService.updateEmail(id, newEmail);
    }

    // PATCH endpoint to update the password of an existing user
    @PatchMapping("{id}/password")
    public User updatePassword(@PathVariable String id, @RequestBody Map<String, String> request){
        String newPassword = request.get("newPassword");
        return userService.updatePassword(id, newPassword);
    }

    // DELETE endpoint to delete an existing user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUser(id);
    }

    // GET endpoint to get ALL the users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
