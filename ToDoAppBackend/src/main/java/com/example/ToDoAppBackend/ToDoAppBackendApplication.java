package com.example.ToDoAppBackend;

import com.example.ToDoAppBackend.auth.InMemoryTokenBlackList;
import com.example.ToDoAppBackend.auth.TokenBlackList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableWebSecurity
@Order(2)
public class ToDoAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoAppBackendApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	TokenBlackList tokenBlackList() {
		return new InMemoryTokenBlackList();
	}
}
