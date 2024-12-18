package com.example.lms.controller;

import org.springframework.stereotype.Controller;

@Controller
public class RoleAccessControl {
    public static boolean userType(String received , String type){
        return received.equals(type);
    }
}
