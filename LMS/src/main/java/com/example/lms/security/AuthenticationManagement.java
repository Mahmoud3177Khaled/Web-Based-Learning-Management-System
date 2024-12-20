package com.example.lms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.User;
import com.example.lms.repository.VirtualDatabase;

@Service
public class AuthenticationManagement {
    @Autowired
    private VirtualDatabase virtualDatabase;

    public boolean isAuthenticate(int userId ,String password){
        User user = virtualDatabase.users.get(userId);
        return user != null &&  user.getPassword().equals(password);
    }
}
