package com.example.ToDoAppBackend.controller;

import com.example.ToDoAppBackend.entity.User;
import com.example.ToDoAppBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {
    @Autowired
    private UserService userService;
    @GetMapping("/{username}")
    @PreAuthorize("#user.username == #username")
    public ResponseEntity<String> sayHello(@PathVariable String username, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok("Hello, " + username);
    }
}
