package com.microservice.question_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class NewQuestionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewQuestionServiceApplication.class, args);
	}

}
