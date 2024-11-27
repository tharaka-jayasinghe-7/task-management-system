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

    @PutMapping("/updateuser/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "User deleted";
    }

    @GetMapping("/getuser/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

}
