package com.example.ToDoAppBackend.service;

import com.example.ToDoAppBackend.entity.Task;
import com.example.ToDoAppBackend.entity.TaskList;
import com.example.ToDoAppBackend.repository.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListService {
    @Autowired
    private TaskListRepository taskListRepository;

    public List<TaskList> getAllTaskLists() {
        return taskListRepository.findAll();
    }

    public List<TaskList> getAllByUserId(Integer userId) {
        return taskListRepository.findAllByUserId(userId);
    }

    public TaskList getById(Integer id) {
        return taskListRepository.findById(id).get();
    }
}
