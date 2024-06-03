package com.example.ToDoAppBackend.service;

import com.example.ToDoAppBackend.dto.UserDTO;
import com.example.ToDoAppBackend.entity.User;
import com.example.ToDoAppBackend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public List<User> loadAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
    }

    public UserDTO.Response loadUserDataByUsername(String username) {
        User user = loadUserByUsername(username);
        UserDTO.Response response = modelMapper.map(user, UserDTO.Response.class);
        return response;
    }

    public UserDTO.Response modifyEmailByUsername(String username, UserDTO.EmailChangeRequest request) {
        User user = loadUserByUsername(username);

        if (!passwordEncoder.matches(request.password(), user.getPassword()) ||
                !request.oldEmail().equals(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid old email or password");
        } else if (userRepository.existsByEmail(request.newEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email is already taken");
        }

        user.setEmail(request.newEmail());
        User savedUser = userRepository.save(user);
        UserDTO.Response response = modelMapper.map(savedUser, UserDTO.Response.class);
        return response;
    }

    public UserDTO.Response modifyPasswordByUsername(String username, String oldPassword, String newPassword) {
        User user = loadUserByUsername(username);

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid old password");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        User savedUser = userRepository.save(user);
        UserDTO.Response response = modelMapper.map(savedUser, UserDTO.Response.class);
        return response;
    }
}
