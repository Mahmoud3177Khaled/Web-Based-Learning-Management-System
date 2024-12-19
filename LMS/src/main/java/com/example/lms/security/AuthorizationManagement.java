package com.example.lms.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.AuthenticationRequest;
import com.example.lms.entity.User;
import com.example.lms.repository.VirtualDatabase;

@Service
public class AuthorizationManagement {
    @Autowired
    private VirtualDatabase virtualDatabase;
    public boolean isAuthorized(AuthenticationRequest authenticationRequest , String userType){
        User user = virtualDatabase.users.get(authenticationRequest.getId());
        if(user.getUserType() == userType){
            return true;
        }
        return false;
    }
}
