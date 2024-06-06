package com.example.ToDoAppBackend.auth;

public interface TokenBlackList {
    void addToBlackList(String token);
    boolean isBlacklisted(String token);
}
