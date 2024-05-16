package com.example.ToDoAppBackend.service;

import com.example.ToDoAppBackend.entity.User;
import com.example.ToDoAppBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public List<User> loadAllUsers() {
        return userRepository.findAll();
    }

    public User loadUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(User::new)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
    }
}
