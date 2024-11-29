package com.example.task_management_system.Data;

public class UserResponse {

    private int user_id;
    private String email;

    public UserResponse(int user_id, String email) {
        this.user_id = user_id;
        this.email = email;
    }

    // Getters and Setters
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int userId) {
        this.user_id = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
