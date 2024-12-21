package com.example.lms.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.User;
import com.example.lms.entity.UserRequest.LoginRequest;
import com.example.lms.entity.UserRequest.UserCreation;
import com.example.lms.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    /*
    {
    "user": {
        "id": 1,
        "name": "Jonathan",
        "password": "Password",
        "email": "jonathan@gmail.com",
        "userType": "Student"
    }
} 
    */
    @PostMapping("/register/submit")
    public ResponseEntity<String> RegisterNewUser(@RequestBody UserCreation user) {
        userService.addNewUser(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable int id) {
        try {
            Object user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        try {
            Object user = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        try {
            Object updated = userService.updateUser(id, updatedUser);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
