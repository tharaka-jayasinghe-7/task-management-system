package com.example.task_management_system.Service;

import com.example.task_management_system.Data.Task;
import com.example.task_management_system.Data.TaskRepository;
import com.example.task_management_system.Data.User;
import com.example.task_management_system.Data.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TaskService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public Task addTaskForUser(int userId, Task task) {
        User user = new User();
        user.setId(userId);

        task.setUser(user); // Associate the task with the user
        return taskRepository.save(task); // Save the task
    }
}
