package com.microservice.question_service.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.question_service.entity.Quest;
import com.microservice.question_service.entity.QuestionWrapper;
import com.microservice.question_service.entity.Response;
import com.microservice.question_service.service.Qservice;



@RestController
public class QuesController 
{
	@Autowired
	Qservice questionservice;
	
	@Autowired
    Environment environment;
	
	@GetMapping("/questions")
	public ResponseEntity<List<Quest>> GetAllQuestions()
	{
		return questionservice.GetAllQuestions();
	}
	
	@GetMapping("/question/category/{category}")
	public ResponseEntity<List<Quest>> GetQuestionsByCategory(@PathVariable String category)
	{
		return questionservice.GetQuestionsByCategory(category);
	}

	@PostMapping("/question/add")
	public ResponseEntity<String> addQuestion(@RequestBody Quest question)
	{
		return questionservice.addQuestion(question);
		
	}
	
	@DeleteMapping("/question/delete/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Integer id)
	{
		return questionservice.deleteQuestion(id);
	}
	
	@PutMapping("/question/update/{id}")
	public ResponseEntity<String> updateQuestion(@RequestBody Quest question,@PathVariable Integer id)
	{
		return questionservice.updateQuestion(question,id);
	}
	
	
	// generate or create
	// getQuestions (questionid)
	// getScore
	
	
	
	@GetMapping("/question/generate")
	public ResponseEntity<List<Integer>> getQuestionForQuiz
	(@RequestParam String category, @RequestParam Integer NumberofQuestions)
	{
		return questionservice.getQuestionsforQuiz(category,NumberofQuestions);
		
	}
	
	@PostMapping("/question/getQuestions")
	
	public ResponseEntity<List<QuestionWrapper>> getQuestionfromId(@RequestBody List<Integer> questionids)
	{
		
		System.out.println(environment.getProperty("local.server.port"));
	     

		
		
		
		return questionservice.getQuestionFromId(questionids);
	}
	
	@PostMapping("/question/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
	{
		return questionservice.getscore(responses);
		//return null;
		
	}
	

}
