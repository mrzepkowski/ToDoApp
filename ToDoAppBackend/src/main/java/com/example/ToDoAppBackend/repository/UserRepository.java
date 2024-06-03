package com.example.ToDoAppBackend.repository;

import com.example.ToDoAppBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Integer> {
    public Optional<User> findByUsername(String username);

    public Optional<User> findById(Integer id);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);

    @Modifying
    @Query("update users set password = ?2 where username = ?1")
    public int setPasswordByUsername(String username, String newPassword);
}
