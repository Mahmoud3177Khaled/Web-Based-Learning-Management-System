package com.example.lms.security;
import org.springframework.stereotype.Service;

import com.example.lms.entity.User;
import com.example.lms.repository.VirtualDatabase;

@Service
public class AuthorizationManagement {

    public boolean isAuthorized(int userId, String userType){
        User user = VirtualDatabase.users.get(userId);
        return user.getUserType().equals(userType);
    }
}
