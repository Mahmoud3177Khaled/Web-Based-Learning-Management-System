package com.example.lms.security;
import org.springframework.stereotype.Service;

import com.example.lms.repository.VirtualDatabase;

@Service
public class AuthorizationManagement {

    public boolean isAuthorized(int userId, String userType){
        if(VirtualDatabase.instructors.containsKey(userId) && userType.equals("Instructor")){
            return true;
        }
        else if(VirtualDatabase.admins.containsKey(userId) && userType.equals("Admin")){
            return true;
        }
        else if(VirtualDatabase.students.containsKey(userId) && userType.equals("Student")){
            return true;
        }
        return false;
    }
}
