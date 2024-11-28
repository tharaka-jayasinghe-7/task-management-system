package com.example.task_management_system.Controller;

import com.example.task_management_system.Data.User;
import com.example.task_management_system.Data.UserDTO;
import com.example.task_management_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getusers")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/updateuser/{user_id}")
    public User updateUser(@PathVariable int user_id, @RequestBody User user) {
        user.setUser_id(user_id);
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteuser/{user_id}")
    public String deleteUser(@PathVariable int user_id) {
        userService.deleteUser(user_id);
        return "User deleted";
    }

    @GetMapping("/getuser/{user_id}")
    public User getUserById(@PathVariable int user_id) {
        return userService.getUserById(user_id);
    }

}
