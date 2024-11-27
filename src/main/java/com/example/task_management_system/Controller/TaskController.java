package com.example.task_management_system.Controller;

import com.example.task_management_system.Data.Task;
import com.example.task_management_system.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/users/{id}/addtask")
    public ResponseEntity<Task> addTask(@PathVariable int id, @RequestBody Task task) {
        Task createdTask = taskService.addTaskForUser(id, task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }
}

