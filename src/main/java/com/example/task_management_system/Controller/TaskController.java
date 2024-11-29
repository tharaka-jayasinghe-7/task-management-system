package com.example.task_management_system.Controller;

import com.example.task_management_system.Data.Task;
import com.example.task_management_system.Service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}

