package com.example.ToDoAppBackend.repository;

import com.example.ToDoAppBackend.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskListRepository extends JpaRepository<TaskList, Integer> {
    public List<TaskList> findAllByUserId(Integer userId);
    public Optional<TaskList> findById(Integer id);
}
