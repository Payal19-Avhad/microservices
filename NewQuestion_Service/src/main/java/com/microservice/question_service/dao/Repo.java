package com.microservice.question_service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservice.question_service.entity.Quest;


@Repository
public interface Repo extends JpaRepository<Quest, Integer>
	{
	   List<Quest> findByCategory(String category);

	   @Query(value="SELECT q.id FROM question q where q.category=:category ORDER BY RAND() LIMIT :num", nativeQuery = true)
	   
	    List<Integer> findRandomQuestionByCategory(String category, int num);
		
	}

