package com.example.task_management_system.Service;

import com.example.task_management_system.Data.Task;
import com.example.task_management_system.Data.TaskRepository;
import com.example.task_management_system.Data.User;
import com.example.task_management_system.Data.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public Task addTaskForUser(int user_id, Task task) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + user_id));

        task.setUser(user);  // Set the user for the task
        return taskRepository.save(task);  // Save the task
    }

    public Task updateTaskForUser(int user_id, Task task) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + user_id));

        task.setUser(user);  // Set the user for the task
        return taskRepository.save(task);  // Save the task
    }

    public void deleteTask(int task_id) {
        taskRepository.deleteById(task_id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByUserId(int user_id) {
        return taskRepository.findByUserId(user_id);  // Use the updated repository method
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }


}
