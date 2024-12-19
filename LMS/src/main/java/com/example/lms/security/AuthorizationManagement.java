package com.example.lms.security;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.lms.entity.AuthenticationRequest;
import com.example.lms.repository.VirtualDatabase;

public class AuthorizationManagement {
    @Autowired
    private VirtualDatabase virtualDatabase;
    public boolean isAuthorized(AuthenticationRequest authenticationRequest , String userType){
        return true;
    }
}
