package com.example.task_management_system.Controller;

import org.springframework.web.bind.annotation.*;
import com.example.task_management_system.Data.Admin;
import com.example.task_management_system.Data.AdminResponse;
import com.example.task_management_system.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.apache.commons.text.StringEscapeUtils;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Register a new admin
    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody Admin admin) {
        // Sanitize inputs before saving to DB 
        admin.setEmail(StringEscapeUtils.escapeHtml4(admin.getEmail())); // Sanitize email input
        admin.setPassword(StringEscapeUtils.escapeHtml4(admin.getPassword())); // Sanitize password input
        admin.setConfirmPassword(StringEscapeUtils.escapeHtml4(admin.getConfirmPassword())); // Sanitize confirmPassword input

        String validationResponse = adminService.validateAndRegisterAdmin(admin);
        if (!validationResponse.equals("Success")) {
            return ResponseEntity.badRequest().body(validationResponse);
        }
        return ResponseEntity.ok("Admin registered successfully");
    }

    // Admin Login
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Admin admin) {
        // Sanitize inputs before proceeding with authentication
        String sanitizedEmail = StringEscapeUtils.escapeHtml4(admin.getEmail());
        String sanitizedPassword = StringEscapeUtils.escapeHtml4(admin.getPassword());

        AdminResponse response = adminService.authenticateAdmin(sanitizedEmail, sanitizedPassword);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect email or password");
        }

        // Return the sanitized response containing admin_id and email
        response.setEmail(StringEscapeUtils.escapeHtml4(response.getEmail()));
        return ResponseEntity.ok(response);
    }
}
