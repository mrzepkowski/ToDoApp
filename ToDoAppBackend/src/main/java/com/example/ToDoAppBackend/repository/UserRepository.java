package com.example.ToDoAppBackend.repository;

import com.example.ToDoAppBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Integer> {
    public Optional<User> findByUsername(String username);
    public Optional<User> findById(Integer id);
    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
}
