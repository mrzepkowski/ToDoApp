package com.example.ToDoAppBackend.controller;

import com.example.ToDoAppBackend.entity.ToDoList;
import com.example.ToDoAppBackend.service.ToDoListService;
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
    public ResponseEntity<?> getAllToDoLists(@PathVariable String username) {
        return new ResponseEntity<>(toDoListService.loadAllByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/{username}/{id}")
    @PreAuthorize("authentication.name == #username")
    public ResponseEntity<?> getToDoListById (@PathVariable String username, @PathVariable Integer id) {
        ToDoList toDoList = toDoListService.loadById(id);
        if (id.equals(toDoList.getId())) {
            return new ResponseEntity<>(toDoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid id.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{username}/{colorId}")
    @PreAuthorize("authentication.name == #username")
    public ResponseEntity<?> getToDoListsByColor(@PathVariable String username, @PathVariable Integer colorId) {
        return new ResponseEntity<>(toDoListService.loadAllByUsernameAndColorId(username, colorId), HttpStatus.OK);
    }

    @PostMapping("/{username}/add")
    @PreAuthorize("authentication.name == #username")
    public ResponseEntity<?> createToDoList(@PathVariable String username, @RequestBody ToDoList toDoList) {
        if (toDoListService.saveToDoList(toDoList) != null) {
            return new ResponseEntity<>("To-do list created.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid data.", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{username}/{id}/changeTitle")
    @PreAuthorize("authentication.name == #username")
    public ResponseEntity<?> changeToDoListTitle(@PathVariable String username, @PathVariable Integer id, @RequestBody String newTitle) {
        int nRowsUpdated = toDoListService.modifyTitleByIdAndUsername(newTitle, id, username); //number of rows being updated
        if (nRowsUpdated > 0) {
            return ResponseEntity.ok("Title updated.");
        } else {
            return new ResponseEntity<>("To-do list not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{username}/{id}/delete")
    @PreAuthorize("authentication.name == #username")
    public ResponseEntity<?> deleteToDoList(@PathVariable String username, @PathVariable Integer id) {
        if (toDoListService.removeToDoListById(id, username) == 1) {
            return ResponseEntity.ok("To-do list removed.");
        } else {
            return new ResponseEntity<>("Invalid id", HttpStatus.NOT_FOUND);
        }
    }
}
