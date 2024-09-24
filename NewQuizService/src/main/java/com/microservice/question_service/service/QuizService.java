package com.microservice.question_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.microservice.question_service.Feign.QuizInterface;
import com.microservice.question_service.dao.QuizRepository;
import com.microservice.question_service.entity.QuestionWrapper;
import com.microservice.question_service.entity.Quiz;
import com.microservice.question_service.entity.Response;

import jakarta.persistence.criteria.CriteriaBuilder.In;




@Component
public class QuizService 
{
   
   @Autowired	
   QuizRepository quizrepo;
   
   @Autowired
   QuizInterface quizinterface;
   
	public ResponseEntity<String> createQuiz(String category, int num, String title) {
		// TODO Auto-generated method stub
		
		List<Integer> questions= quizinterface.getQuestionForQuiz(category, num).getBody();
		
		

		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionids(questions);
		quizrepo.save(quiz);	
		
		
		
		
	//	List<Integer> questions=questionrepo.findRandomQuestionByCategory(category, num);
//		Quiz quiz=new Quiz();
//		quiz.setTitle(title);
//		quiz.setQuestions(questions);
//		quizrepo.save(quiz);
	
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(int id) {
		// TODO Auto-generated method stub
		Quiz quiz= quizrepo.findById(id).get();
		
		List<Integer> questionIds = quiz.getQuestionids();
		ResponseEntity<List<QuestionWrapper>> questions= quizinterface.getQuestionfromId(questionIds);

		
//	    List<Question> questionfromDB=quiz.get().getQuestions();
//	    List<QuestionWrapper> queforUser=new ArrayList<>();
//	    for(Question q: questionfromDB)
//	    {
//	    	QuestionWrapper qw=new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
//	        queforUser.add(qw);
//	    }
	     
	    return questions;
	}

	public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
		// TODO Auto-generated method stub
		
		
		ResponseEntity<Integer> score = quizinterface.getScore(responses);
		
		return score;

	
		
//		Quiz quiz=quizrepo.findById(id).get();
//		List<Question> questions=quiz.getQuestions();
//		int right=0;
//		int i=0;
//		for(Response response : responses)
//		{
//			if(questions != null && response.getResponse().equals(questions.get(i).getRightAnswer()))
//			{  
//			right++;
//			}
//			
//		}
			}

}
