package com.example.task_management_system.Service;

import com.example.task_management_system.Data.Admin;
import com.example.task_management_system.Data.AdminRepository;
import com.example.task_management_system.Data.AdminResponse;
import jakarta.transaction.Transactional;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.orElse(null);
    }

    public String validateAndRegisterAdmin(Admin admin) {
        // Sanitize inputs before processing
        admin.setEmail(StringEscapeUtils.escapeHtml4(admin.getEmail()));
        admin.setPassword(StringEscapeUtils.escapeHtml4(admin.getPassword()));
        admin.setConfirmPassword(StringEscapeUtils.escapeHtml4(admin.getConfirmPassword()));

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

        // Sanitize inputs
        String sanitizedEmail = StringEscapeUtils.escapeHtml4(email);
        String sanitizedPassword = StringEscapeUtils.escapeHtml4(password);

        Admin admin = adminRepository.findByEmail(sanitizedEmail);

        if (admin == null) {
            System.out.println("Admin not found for email: " + sanitizedEmail); // Debug log
            return null;
        }

        System.out.println("Admin found: " + admin); // Debug log
        System.out.println("Provided password: " + sanitizedPassword); // Debug log

        if (!admin.getPassword().equals(sanitizedPassword)) {
            System.out.println("Password mismatch for email: " + sanitizedEmail); // Debug log
            return null;
        }

        // Return sanitized admin details
        return new AdminResponse(admin.getId(), StringEscapeUtils.escapeHtml4(admin.getEmail()));
    }

    public boolean deleteAdminById(Long id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
