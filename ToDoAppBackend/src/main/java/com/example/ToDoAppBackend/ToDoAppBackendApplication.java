package com.example.ToDoAppBackend;

import com.example.ToDoAppBackend.configuration.RsaKeyConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyConfigProperties.class)
@SpringBootApplication
public class ToDoAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoAppBackendApplication.class, args);
	}

}
