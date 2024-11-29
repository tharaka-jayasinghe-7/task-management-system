package com.example.task_management_system.Controller;

import com.example.task_management_system.Data.User;
import com.example.task_management_system.Data.UserResponse;
import com.example.task_management_system.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/user")
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/getusers")
    public List<User> getUsers() {
        log.info("Fetching all users");
        List<User> users = userService.getAllUsers();
        log.debug("Number of users fetched: {}", users.size());
        return users;
    }

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user) {
        log.info("Adding new user: {}", user);
        User addedUser = userService.addUser(user);
        log.debug("User added with ID: {}", addedUser.getUser_id());
        return addedUser;
    }

    @PutMapping("/updateuser/{user_id}")
    public User updateUser(@PathVariable int user_id, @RequestBody User user) {
        log.info("Updating user with ID: {}", user_id);
        user.setUser_id(user_id);
        User updatedUser = userService.updateUser(user);
        log.debug("User updated: {}", updatedUser);
        return updatedUser;
    }

    @DeleteMapping("/deleteuser/{user_id}")
    public String deleteUser(@PathVariable int user_id) {
        log.info("Deleting user with ID: {}", user_id);
        userService.deleteUser(user_id);
        log.debug("User with ID {} deleted successfully", user_id);
        return "User deleted";
    }

    @GetMapping("/getuser/{user_id}")
    public User getUserById(@PathVariable int user_id) {
        log.info("Fetching user with ID: {}", user_id);
        User user = userService.getUserById(user_id);
        if (user == null) {
            log.error("User with ID {} not found", user_id);
        } else {
            log.debug("Fetched user: {}", user);
        }
        return user;
    }

    @GetMapping("/getuserbyemail/{email}")
    public User getUserByEmail(@PathVariable String email) {
        log.info("Fetching user with email: {}", email);

        User user = userService.getUserByEmail(email);

        if (user == null) {
            log.error("User with email {} not found", email);
            return null;  // You could also return a custom error response or throw an exception
        } else {
            log.debug("Fetched user: {}", user);
            return user;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User loginUser) {
        log.info("User login attempt for email: {}", loginUser.getEmail());
        try {
            // Authenticate user by email and password
            User user = userService.authenticateUser(loginUser.getEmail(), loginUser.getPassword());

            if (user == null) {
                log.warn("Failed login attempt for email: {}", loginUser.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect email or password");
            }

            // If user found, return UserResponse with userId and email
            UserResponse response = new UserResponse(user.getUser_id(), user.getEmail());
            log.info("User login successful for email: {}", loginUser.getEmail());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error occurred during user login for email: {}", loginUser.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }
}

