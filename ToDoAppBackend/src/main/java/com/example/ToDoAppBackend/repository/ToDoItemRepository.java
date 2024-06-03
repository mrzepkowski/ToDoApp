package com.example.ToDoAppBackend.repository;

import com.example.ToDoAppBackend.entity.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Integer> {
    List<ToDoItem> findAllByUsername();
}
