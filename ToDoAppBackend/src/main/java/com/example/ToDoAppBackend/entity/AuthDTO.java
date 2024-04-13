package com.example.ToDoAppBackend.entity;

public class AuthDTO {
    public record LoginRequest(String username, String password) {
    }

    public record Response(String message, String token) {
    }

    public record RegisterRequest(String username, String email, String password) {

    }
}
