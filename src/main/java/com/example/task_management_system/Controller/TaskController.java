package com.example.task_management_system.Controller;

import com.example.task_management_system.Data.Task;
import com.example.task_management_system.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/users/{user_id}/addtask")
    public ResponseEntity<Task> addTaskForUser(@PathVariable int user_id, @RequestBody Task task) {
        Task createdTask = taskService.addTaskForUser(user_id, task);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/gettasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
}

