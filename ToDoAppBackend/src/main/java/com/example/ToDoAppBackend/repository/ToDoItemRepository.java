package com.example.ToDoAppBackend.repository;

import com.example.ToDoAppBackend.entity.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Integer> {
}
