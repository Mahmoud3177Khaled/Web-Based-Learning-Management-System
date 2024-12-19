package com.example.lms.entity;

public class AuthenticationRequest {
    private int id;
    private String password;
    public AuthenticationRequest(int id, String password) {
        this.id = id;
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
