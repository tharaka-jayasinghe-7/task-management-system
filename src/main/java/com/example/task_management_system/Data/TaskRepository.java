package com.example.task_management_system.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    // This method fetches all tasks for a given user from the database
    @Query("SELECT t FROM Task t WHERE t.user.user_id = :userId")
    List<Task> findByUserId(int userId);
}

