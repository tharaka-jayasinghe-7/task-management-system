package com.example.task_management_system.Data;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Transient // This field will not be persisted
    private String confirmPassword;
}
