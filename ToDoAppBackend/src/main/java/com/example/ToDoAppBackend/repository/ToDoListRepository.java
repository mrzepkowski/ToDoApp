package com.example.ToDoAppBackend.repository;

import com.example.ToDoAppBackend.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {
    public List<ToDoList> findAllByUsername(String username);

    public Optional<ToDoList> findByUsernameAndId(String username, Integer id);

    public List<ToDoList> findAllByUsernameAndColorId(String username, Integer colorId);
}
