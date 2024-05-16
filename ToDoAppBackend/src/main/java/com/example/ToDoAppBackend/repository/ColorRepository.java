package com.example.ToDoAppBackend.repository;

import com.example.ToDoAppBackend.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color, Integer> {
    public Color findByName(String name);
}