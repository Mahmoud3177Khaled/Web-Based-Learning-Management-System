package com.example.lms.entity;

public class Instructor extends User {







    public Instructor (int id, String name,String password,String email) {
        super(id, name, password, email,"Instructor");
    };
}
