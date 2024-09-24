package com.microservice.question_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NewQuizServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewQuizServiceApplication.class, args);
	}

}
