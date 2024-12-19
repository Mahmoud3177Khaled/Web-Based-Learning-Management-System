package com.example.lms.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.Student;
import com.example.lms.entity.UserRequest.UserCreation;
import com.example.lms.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

    @Autowired
    UserService UserService;

    @PostMapping("/login/submit")
    public ResponseEntity<String> postMethodName(@RequestBody UserCreation user) {
        UserService.addNewUser(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable int id) {
        try {
            Object user = UserService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
