package com.example.task_management_system.Service;

import com.example.task_management_system.Data.User;
import com.example.task_management_system.Data.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int user_id) {
        userRepository.deleteById(user_id);
    }

    public User getUserById(int user_id) {
        Optional<User> user = userRepository.findById(user_id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);  // This method is defined in your repository
    }

    public User authenticateUser(String email, String password) {
        System.out.println("Authenticating email: " + email); // Debug log

        // Find the user by email
        User user = userRepository.findByEmail(email);

        if (user == null) {
            System.out.println("User not found for email: " + email); // Debug log
            return null;
        }

        System.out.println("User found: " + user); // Debug log
        System.out.println("Provided password: " + password); // Debug log

        // Compare the provided password with the stored password (plain text)
        if (!user.getPassword().equals(password)) {
            System.out.println("Password mismatch for email: " + email); // Debug log
            return null;
        }

        // Return the user object after successful authentication
        return user;
    }

}
