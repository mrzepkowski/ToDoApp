package com.example.ToDoAppBackend.repository;

import com.example.ToDoAppBackend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
