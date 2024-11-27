package com.example.task_management_system.Data; // Or com.example.task_management_system.dto if you prefer

public class AdminResponse {
    private Long admin_id;
    private String email;

    public AdminResponse(Long admin_id, String email) {
        this.admin_id = admin_id;
        this.email = email;
    }

    public Long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Long admin_id) {
        this.admin_id = admin_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
