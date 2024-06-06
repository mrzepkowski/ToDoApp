package com.example.ToDoAppBackend.controller;

import com.example.ToDoAppBackend.dto.ToDoListDTO;
import com.example.ToDoAppBackend.entity.ToDoList;
import com.example.ToDoAppBackend.service.ToDoListService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todolists")
public class ToDoListController {
    @Autowired
    private ToDoListService toDoListService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(
                toDoListService.loadAllByUsername(username),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
            ToDoList toDoList = toDoListService.loadByUsernameAndId(username, id);
            return new ResponseEntity<>(toDoList, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(
                    "To-do list not found for user " + username + " and id " + id,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("/by-color-id/{color-id}")
    public ResponseEntity<?> getByColorId(@PathVariable(name = "color-id") Long colorId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(
                toDoListService.loadAllByUsernameAndColorId(username, colorId),
                HttpStatus.OK
        );
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ToDoListDTO.Request request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(
                toDoListService.saveToDoList(username, request),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,
                                    @RequestBody ToDoListDTO.Request request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(
                toDoListService.modifyByUsernameAndId(username, id, request),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        toDoListService.removeByUsernameAndId(username, id);
        return ResponseEntity.ok("To-do list removed");
    }
}
