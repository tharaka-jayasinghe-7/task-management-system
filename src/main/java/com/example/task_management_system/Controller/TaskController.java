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

    @PutMapping("/users/{user_id}/updatetask/{task_id}")
    public ResponseEntity<Task> updateTaskForUser(@PathVariable int user_id,@PathVariable int task_id, @RequestBody Task task) {
        task.setTask_id(task_id);
        Task createdTask = taskService.updateTaskForUser(user_id, task);
        return ResponseEntity.ok(createdTask);
    }

    @DeleteMapping("/deletetask/{task_id}")
    public String deleteTask(@PathVariable int task_id) {
        taskService.deleteTask(task_id);
        return "Task deleted";
    }

    @GetMapping("/gettasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
}

