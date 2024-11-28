package com.example.task_management_system.Data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository  extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
}