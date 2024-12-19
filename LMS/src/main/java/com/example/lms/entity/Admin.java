package com.example.lms.entity;


public class Admin extends User {







    public Admin (int id, String name,String password,String email) {
        super(id, name, password, email,"Admin");
    };

}
