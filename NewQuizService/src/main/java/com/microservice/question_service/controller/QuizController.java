package com.microservice.question_service.controller;

import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservice.question_service.entity.QuestionWrapper;
import com.microservice.question_service.entity.Quizdto;
import com.microservice.question_service.entity.Response;
import com.microservice.question_service.service.QuizService;



@Controller
public class QuizController 
{
	
	@Autowired
	QuizService quizService;
	
	@GetMapping("/quiz/create")
    public String showCreateQuizForm(Model model) {
        model.addAttribute("quizdto", new Quizdto()); // Passing a new Quizdto object to the view
        return "createQuiz"; // Return Thymeleaf view
    }
	
	@PostMapping("/quiz/create")
	public ResponseEntity<String> createQuiz(@RequestBody Quizdto quizdto)
	//(@ModelAttribute("quizdto") Quizdto quizdto, Model model)
	{
		// return new ResponseEntity<>("i am good", HttpStatus.OK);
		//System.out.println("Received Quiz: " + quizdto);
		 // quizService.createQuiz(dto.getCategoryName(), dto.getNumQuestions(), dto.getTitle());
	        // Return success view or redirect to another page
         return quizService.createQuiz(quizdto.getCategoryName(), quizdto.getNumQuestions(), quizdto.getTitle());
	       
    }
	
	
	@GetMapping("/quiz/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id)
	{
	
		return quizService.getQuizQuestion(id);
		 
	}
	
	
	
	// This is for testing purpose in postman like the particular id is exist or not
	@GetMapping("/quiz/test/{id}")
	public ResponseEntity<List<QuestionWrapper>> testQuizQuestion(@PathVariable int id) {
	    return quizService.getQuizQuestion(id); // This will return the raw data as JSON
	}

//	@GetMapping("/quiz/get/{id}")
//	public String getQuizQuestions(@PathVariable int id, Model model)
//	{
//	
//		 ResponseEntity<List<QuestionWrapper>> response = quizService.getQuizQuestion(id);
//		 model.addAttribute("questions", response.getBody());
//		 return "getQuiz";
//	}
	
	
	
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses)
	{
		return quizService.calculateResult(id,responses);
	}
	
}