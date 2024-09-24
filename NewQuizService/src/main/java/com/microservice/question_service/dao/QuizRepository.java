package com.microservice.question_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.question_service.entity.Quiz;


public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}
