package com.example.task_management_system.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Email(message = "Invalid email format")
    private String email;

    @Column(nullable = false)
    @Pattern(regexp = "^[A-Za-z\\d@$!%*?&]{8,}$", message = "Invalid password format")
    private String password;

    @Transient // This field will not be persisted
    private String confirmPassword;
}
