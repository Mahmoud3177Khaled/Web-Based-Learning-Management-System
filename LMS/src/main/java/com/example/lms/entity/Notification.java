package com.example.lms.entity;

public class Notification {
    private int id;
    private String message;
    private boolean isRead;

    public Notification(int id, String message, boolean isRead) {
        this.id = id;
        this.message = message;
        this.isRead = isRead;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
