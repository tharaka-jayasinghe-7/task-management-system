package com.example.task_management_system.Service;


import com.example.task_management_system.Data.Admin;
import com.example.task_management_system.Data.AdminRepository;
import com.example.task_management_system.Data.AdminResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public String validateAndRegisterAdmin(Admin admin) {
        // Validate email
        if (admin.getEmail() == null || admin.getEmail().isEmpty()) {
            return "Email must not be empty";
        }
        if (!admin.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return "Invalid email format";
        }

        // Validate password
        if (admin.getPassword() == null || admin.getPassword().isEmpty()) {
            return "Password must not be empty";
        }

        // Validate confirmPassword
        if (admin.getConfirmPassword() == null || admin.getConfirmPassword().isEmpty()) {
            return "Confirm Password must not be empty";
        }
        if (!admin.getPassword().equals(admin.getConfirmPassword())) {
            return "Passwords do not match";
        }

        // Check if admin already exists
        if (adminRepository.findByEmail(admin.getEmail()) != null) {
            return "Email is already registered";
        }

        // Save admin (do not save confirmPassword in DB)
        admin.setConfirmPassword(null);
        adminRepository.save(admin);

        return "Success";
    }

    public AdminResponse authenticateAdmin(String email, String password) {
        System.out.println("Authenticating email: " + email); // Debug log
        Admin admin = adminRepository.findByEmail(email);

        if (admin == null) {
            System.out.println("Admin not found for email: " + email); // Debug log
            return null;
        }

        System.out.println("Admin found: " + admin); // Debug log
        System.out.println("Provided password: " + password); // Debug log

        if (!admin.getPassword().equals(password)) {
            System.out.println("Password mismatch for email: " + email); // Debug log
            return null;
        }

        return new AdminResponse(admin.getId(), admin.getEmail());
    }
}