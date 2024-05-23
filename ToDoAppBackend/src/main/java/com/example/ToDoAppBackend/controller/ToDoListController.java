package com.example.ToDoAppBackend.controller;

import com.example.ToDoAppBackend.entity.ToDoList;
import com.example.ToDoAppBackend.entity.User;
import com.example.ToDoAppBackend.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasklists")
public class ToDoListController {
    @Autowired
    private ToDoListService toDoListService;
    @GetMapping("/{username}/all")
    @PreAuthorize("#authentication.username == #username")
    public List<ToDoList> getAllTaskLists(@PathVariable String username) {
        return toDoListService.loadAllByUsername(username);
    }

    @GetMapping("/{id}")
    @PreAuthorize("#user.id == id")
    public ToDoList getTaskListById(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        return toDoListService.loadById(id);
    }

    @GetMapping("/{username}/{colorId}")
    public List<ToDoList> getTaskListsByColor(@PathVariable String username, @PathVariable Integer colorId) {
        return null;
    }

    @PostMapping("/{username}/add")
    public ResponseEntity<?> createTaskList(@PathVariable String username, @RequestBody ToDoList toDoList) {
        return null;
    }

    @PatchMapping("/{id}/changeData")
    public ResponseEntity<?> changeTaskListTitle(@PathVariable Integer id, @RequestBody ToDoList toDoList) {
        return null;
    }
}
