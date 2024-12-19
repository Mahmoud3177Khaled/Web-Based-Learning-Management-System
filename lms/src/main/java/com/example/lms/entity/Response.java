package com.example.lms.entity;

public class Response {
    private Object object;
    private String message;
    public Response(String message) {
        this.message = message;
    }
    public Response(Object object ,String message) {
        this.object = object;
        this.message = message;
    }
    public Object getObject() {
        return object;
    }
    public void setCode(Object object) {
        this.object = object;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
