package com.example.ToDoAppBackend.repository;

import com.example.ToDoAppBackend.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
    public List<ToDoList> findAllByuserUsername(String username);

    public Optional<ToDoList> findByuserUsernameAndId(String username, Long id);

    public List<ToDoList> findAllByuserUsernameAndColorId(String username, Long colorId);
}
