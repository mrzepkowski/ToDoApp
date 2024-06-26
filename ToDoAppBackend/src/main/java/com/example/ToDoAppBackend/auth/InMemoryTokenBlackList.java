package com.example.ToDoAppBackend.auth;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class InMemoryTokenBlackList implements TokenBlackList {
    private Set<String> blacklist = new HashSet<>();
    @Override
    public void addToBlackList(String token) {
        blacklist.add(token);
    }

    @Override
    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }
}
