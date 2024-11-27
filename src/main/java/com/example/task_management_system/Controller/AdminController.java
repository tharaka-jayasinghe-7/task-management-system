package com.example.task_management_system.Controller;


import com.example.task_management_system.Data.Admin;
import com.example.task_management_system.Data.AdminResponse;
import com.example.task_management_system.Service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Register a new admin
    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody Admin admin) {
        String validationResponse = adminService.validateAndRegisterAdmin(admin);
        if (!validationResponse.equals("Success")) {
            return ResponseEntity.badRequest().body(validationResponse);
        }
        return ResponseEntity.ok("Admin registered successfully");
    }

    // Admin Login
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Admin admin) {
        AdminResponse response = adminService.authenticateAdmin(admin.getEmail(), admin.getPassword());

        if (response == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect email or password");
        }

        // Return admin details (admin_id and email)
        return ResponseEntity.ok(response); // Return the response containing admin_id and email
    }
}
