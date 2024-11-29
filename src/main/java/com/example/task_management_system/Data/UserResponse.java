package com.example.task_management_system.Data;

public class UserResponse {

    private int userId;
    private String email;

    public UserResponse(int userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
