package com.example.ToDoAppBackend.repository;

import com.example.ToDoAppBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);

    public Optional<User> findById(Long id);

    public boolean existsByEmail(String email);
}
