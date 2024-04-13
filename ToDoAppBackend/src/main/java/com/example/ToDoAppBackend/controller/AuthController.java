package com.example.ToDoAppBackend.controller;

import com.example.ToDoAppBackend.entity.AuthDTO;
import com.example.ToDoAppBackend.entity.User;
import com.example.ToDoAppBackend.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO.LoginRequest userLogin) throws IllegalAccessException {
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(
                                userLogin.username(),
                                userLogin.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User userDetails = (User) authentication.getPrincipal();


        log.info("Token requested for user :{}", authentication.getAuthorities());
        String token = authService.generateToken(authentication);

        AuthDTO.Response response = new AuthDTO.Response("User logged in successfully", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthDTO.RegisterRequest userRegister) {
        User user = new User();
        user.setUsername(userRegister.username());
        user.setEmail(userRegister.email());
        user.setPassword(new BCryptPasswordEncoder().encode(userRegister.password()));

        if (authService.createUser(user)) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}