package com.example.ToDoAppBackend.controller;

import com.example.ToDoAppBackend.dto.UserDTO;
import com.example.ToDoAppBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserDTO.Response response = userService.loadUserDataByUsername(username);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/change-email")
    public ResponseEntity<?> updateEmail(@RequestBody UserDTO.EmailChangeRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserDTO.Response response = userService.modifyEmailByUsername(username, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/change-password")
    public ResponseEntity<?> updatePassword(@RequestBody UserDTO.PasswordChangeRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserDTO.Response response = userService.modifyPasswordByUsername(
                username,
                request.oldPassword(),
                request.newPassword()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
