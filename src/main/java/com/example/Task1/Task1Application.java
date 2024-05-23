package com.example.Task1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Task1Application {

	public static void main(String[] args) {
		SpringApplication.run(Task1Application.class, args);
		System.out.println("Ishlash kerak...");
	}

}
