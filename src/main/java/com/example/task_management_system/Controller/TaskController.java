package com.example.task_management_system.Controller;

import com.example.task_management_system.Data.Task;
import com.example.task_management_system.Service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @PostMapping("/users/{user_id}/addtask")
    public ResponseEntity<Task> addTaskForUser(@PathVariable int user_id, @RequestBody Task task) {
        log.info("Adding task for user with ID: {}", user_id);
        log.debug("Task details: {}", task);
        Task createdTask = taskService.addTaskForUser(user_id, task);
        log.debug("Task created with ID: {}", createdTask.getTask_id());
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/users/{user_id}/updatetask/{task_id}")
    public ResponseEntity<Task> updateTaskForUser(@PathVariable int user_id, @PathVariable int task_id, @RequestBody Task task) {
        log.info("Updating task with ID: {} for user with ID: {}", task_id, user_id);
        log.debug("Updated task details: {}", task);
        task.setTask_id(task_id);
        Task updatedTask = taskService.updateTaskForUser(user_id, task);
        log.debug("Task updated: {}", updatedTask);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/deletetask/{task_id}")
    public String deleteTask(@PathVariable int task_id) {
        log.info("Deleting task with ID: {}", task_id);
        taskService.deleteTask(task_id);
        log.debug("Task with ID {} deleted successfully", task_id);
        return "Task deleted";
    }

    @GetMapping("/gettasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        log.info("Fetching all tasks");
        List<Task> tasks = taskService.getAllTasks();
        log.debug("Number of tasks fetched: {}", tasks.size());
        return ResponseEntity.ok(tasks);
    }
    //task by user ID?
    @GetMapping("/users/{user_id}/tasks")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable int user_id) {
        log.info("Fetching tasks for user with ID: {}", user_id);
        List<Task> tasks = taskService.getTasksByUserId(user_id);
        log.debug("Number of tasks fetched for user ID {}: {}", user_id, tasks.size());
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/gettask")
    public ResponseEntity<List<Map<String, Object>>> getAllTask() {
        // Fetch all tasks from the service
        List<Task> tasks = taskService.getAllTask();

        // Convert tasks into a Map to include user_id
        List<Map<String, Object>> response = tasks.stream().map(task -> {
            Map<String, Object> taskMap = new HashMap<>();
            taskMap.put("task_id", task.getTask_id());
            taskMap.put("title", task.getTitle());
            taskMap.put("description", task.getDescription());
            taskMap.put("date", task.getDate());
            taskMap.put("user_id", task.getUser() != null ? task.getUser().getUser_id() : null); // Fetch user_id from User object
            return taskMap;
        }).collect(Collectors.toList());

        // Return the list of tasks with user IDs
        return ResponseEntity.ok(response);}


}

