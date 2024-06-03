package com.example.ToDoAppBackend.controller;

import com.example.ToDoAppBackend.dto.UserDTO;
import com.example.ToDoAppBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    @PreAuthorize("authentication.name == #username")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        UserDTO.Response response = userService.loadUserDataByUsername(username);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{username}/update-email")
    @PreAuthorize("authentication.name == #username")
    public ResponseEntity<?> updateEmail(@PathVariable String username,
                                         @RequestBody UserDTO.EmailChangeRequest request) {
        UserDTO.Response response = userService.modifyEmailByUsername(username, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{username}/update-password")
    @PreAuthorize("authentication.name == #username")
    public ResponseEntity<?> updatePassword(@PathVariable String username,
                                            @RequestBody UserDTO.PasswordChangeRequest request) {
        UserDTO.Response response = userService.modifyPasswordByUsername(
                username,
                request.oldPassword(),
                request.newPassword()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
