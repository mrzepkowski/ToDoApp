package com.example.ToDoAppBackend.controller;

import com.example.ToDoAppBackend.dto.ToDoListDTO;
import com.example.ToDoAppBackend.entity.ToDoList;
import com.example.ToDoAppBackend.service.ToDoListService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todolists")
public class ToDoListController {
    @Autowired
    private ToDoListService toDoListService;

    @GetMapping("/{username}/all")
    @PreAuthorize("authentication.name == #username")
    public ResponseEntity<?> getAllByUsername(@PathVariable String username) {
        return new ResponseEntity<>(
                toDoListService.loadAllByUsername(username),
                HttpStatus.OK
        );
    }

    @GetMapping("/{username}/{id}")
    @PreAuthorize("authentication.name == #username")
    public ResponseEntity<?> getByUsernameAndId (@PathVariable String username, @PathVariable Integer id) {
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

    @GetMapping("/{username}/{colorId}")
    @PreAuthorize("authentication.name == #username")
    public ResponseEntity<?> getToDoListsByColor(@PathVariable String username, @PathVariable Integer colorId) {
        return new ResponseEntity<>(
                toDoListService.loadAllByUsernameAndColorId(username, colorId),
                HttpStatus.OK
        );
    }

    @PostMapping("/{username}/create")
    @PreAuthorize("authentication.name == #username")
    public ResponseEntity<?> create(@PathVariable String username, @RequestBody ToDoListDTO.Request request) {
        return new ResponseEntity<>(
                toDoListService.saveToDoList(username, request),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{username}/{id}/update")
    @PreAuthorize("authentication.name == #username")
    public ResponseEntity<?> update(@PathVariable String username,
                                    @PathVariable Integer id,
                                    @RequestBody ToDoListDTO.Request request) {
        return new ResponseEntity<>(
                toDoListService.modifyByUsernameAndId(username, id, request),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{username}/{id}/delete")
    @PreAuthorize("authentication.name == #username")
    public ResponseEntity<?> delete(@PathVariable String username, @PathVariable Integer id) {
        toDoListService.removeByUsernameAndId(username, id);
        return ResponseEntity.ok("To-do list removed");
    }
}
