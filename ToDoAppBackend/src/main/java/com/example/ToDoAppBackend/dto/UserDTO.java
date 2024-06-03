package com.example.ToDoAppBackend.dto;

public class UserDTO {
    public record Response(String username, String email) {}

    public record EmailChangeRequest(String oldEmail, String newEmail, String password) {}

    public record PasswordChangeRequest(String oldPassword, String newPassword) {}
}