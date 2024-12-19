package com.example.lms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.AuthenticationRequest;
import com.example.lms.entity.User;
import com.example.lms.repository.VirtualDatabase;

@Service
public class AuthenticationManagement {
    @Autowired
    private VirtualDatabase virtualDatabase;

    public boolean isAuthenticate(AuthenticationRequest authenticationRequest){
        User user = virtualDatabase.users.get(authenticationRequest.getId());
        if(user != null &&  user.getPassword() == authenticationRequest.getPassword()){
            return true;
        }
        return false;
    }
}
