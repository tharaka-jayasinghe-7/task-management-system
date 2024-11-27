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
}
