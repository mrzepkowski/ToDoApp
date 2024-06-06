package com.example.ToDoAppBackend.controller;

import com.example.ToDoAppBackend.dto.ToDoItemDTO;
import com.example.ToDoAppBackend.service.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todoitems")
public class ToDoItemController {
    @Autowired
    private ToDoItemService toDoItemService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(
                toDoItemService.loadAll(username),
                HttpStatus.OK
        );
    }

    @GetMapping("/completed")
    public ResponseEntity<?> getAllCompleted() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(
                toDoItemService.loadAllCompleted(username),
                HttpStatus.OK
        );
    }

    @GetMapping("/all/{to-do-list-id}")
    public ResponseEntity<?> getAllByToDoListId(@PathVariable(name = "to-do-list-id") Long toDoListId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(
                toDoItemService.loadAllByToDoListId(username, toDoListId),
                HttpStatus.OK
        );
    }

    @GetMapping("/starred")
    public ResponseEntity<?> getAllStarred() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(
                toDoItemService.loadAllStarred(username),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(
                toDoItemService.loadById(username, id),
                HttpStatus.OK
        );
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ToDoItemDTO.SaveRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(
                toDoItemService.save(username, request),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id,
                                    @RequestBody ToDoItemDTO.UpdateRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(
                toDoItemService.modify(username, id, request),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        toDoItemService.remove(username, id);
        return new ResponseEntity<>("To-do item removed", HttpStatus.OK);
    }
}
