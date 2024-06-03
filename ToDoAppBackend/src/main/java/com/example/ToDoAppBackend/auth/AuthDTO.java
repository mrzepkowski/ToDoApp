package com.example.ToDoAppBackend.auth;

public class AuthDTO {
    public record LoginRequest(String username, String password) {}

    public record Response(String message, String token) {}

    public record RegisterRequest(String username, String email, String password) {}

    public record RegisterResponse(String username, String email) {}
}
