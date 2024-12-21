package com.example.lms.security;

import org.springframework.stereotype.Service;

import com.example.lms.entity.User;
import com.example.lms.repository.VirtualDatabase;

@Service
public class AuthenticationManagement {

    public boolean isAuthenticate(int userId ,String password){
        User user = VirtualDatabase.users.get(userId);
        return user != null &&  user.getPassword().equals(password);
    }
}
