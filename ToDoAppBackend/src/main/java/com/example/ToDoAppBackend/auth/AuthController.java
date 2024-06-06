package com.example.ToDoAppBackend.auth;

import com.example.ToDoAppBackend.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.StringUtils;
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

    @Autowired
    private TokenBlackList tokenBlackList;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO.LoginRequest userLogin) throws IllegalAccessException {
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(
                                userLogin.username(),
                                userLogin.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();


        log.info("Token requested for user :{}", authentication.getAuthorities());
        String token = authService.generateToken(user);

        AuthDTO.Response response = new AuthDTO.Response("User logged in successfully", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthDTO.RegisterRequest request) {
        log.info("Register request ( username: " + request.username() + ", email: " + request.email() + " )");
        AuthDTO.RegisterResponse response = authService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = authService.extractTokenFromRequest(request);
        tokenBlackList.addToBlackList(token);
        return ResponseEntity.ok("Logged out succcesfully");
    }
}