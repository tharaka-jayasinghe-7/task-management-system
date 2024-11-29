package com.example.task_management_system.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.example.task_management_system.Data.Admin;
import com.example.task_management_system.Data.AdminResponse;
import com.example.task_management_system.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.apache.commons.text.StringEscapeUtils;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    // Fetch all admins
    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        System.out.println("Admins retrieved: " + admins); // Log the list of admins
        return ResponseEntity.ok(admins);
    }

    // Fetch admin by ID
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Admin admin = adminService.getAdminById(id);
        if (admin == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(admin);
    }

    // Register a new admin
    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody Admin admin) {
        log.info("Registering a new admin: {}", admin.getEmail());
        try {
            // Sanitize inputs before saving to DB
            admin.setEmail(StringEscapeUtils.escapeHtml4(admin.getEmail())); // Sanitize email input
            admin.setPassword(StringEscapeUtils.escapeHtml4(admin.getPassword())); // Sanitize password input
            admin.setConfirmPassword(StringEscapeUtils.escapeHtml4(admin.getConfirmPassword())); // Sanitize confirmPassword input

            String validationResponse = adminService.validateAndRegisterAdmin(admin);
            if (!validationResponse.equals("Success")) {
                log.warn("Validation failed for admin registration: {}", validationResponse);
                return ResponseEntity.badRequest().body(validationResponse);
            }
            log.info("Admin registered successfully with email: {}", admin.getEmail());
            return ResponseEntity.ok("Admin registered successfully");
        } catch (Exception e) {
            log.error("Error occurred during admin registration for email: {}", admin.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during registration");
        }
    }

    // Admin Login
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Admin admin) {
        log.info("Admin login attempt for email: {}", admin.getEmail());
        try {
            // Sanitize inputs before proceeding with authentication
            String sanitizedEmail = StringEscapeUtils.escapeHtml4(admin.getEmail());
            String sanitizedPassword = StringEscapeUtils.escapeHtml4(admin.getPassword());

            AdminResponse response = adminService.authenticateAdmin(sanitizedEmail, sanitizedPassword);

            if (response == null) {
                log.warn("Failed login attempt for email: {}", sanitizedEmail);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect email or password");
            }

            // Return the sanitized response containing admin_id and email
            response.setEmail(StringEscapeUtils.escapeHtml4(response.getEmail()));
            log.info("Admin login successful for email: {}", sanitizedEmail);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error occurred during admin login for email: {}", admin.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }

    // Delete an admin by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        boolean isDeleted = adminService.deleteAdminById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Admin deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found");
        }
    }
}
