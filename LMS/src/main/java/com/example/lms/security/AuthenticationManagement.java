package com.example.lms.security;

import org.springframework.stereotype.Service;

import com.example.lms.entity.User;
import com.example.lms.repository.VirtualDatabase;

@Service
public class AuthenticationManagement {

    public boolean isAuthenticate(int userId ,String password){
        User user = null;
        if(VirtualDatabase.instructors.containsKey(userId)){
            user = VirtualDatabase.instructors.get(userId);
        }
        else if(VirtualDatabase.admins.containsKey(userId)){
            user = VirtualDatabase.admins.get(userId);
        }
        else if(VirtualDatabase.students.containsKey(userId)){
            user = VirtualDatabase.students.get(userId);
        }
        return user != null &&  user.getPassword().equals(password);
    }
}
