package com.microservice.question_service.service;





import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.microservice.question_service.dao.Repo;
import com.microservice.question_service.entity.Quest;
import com.microservice.question_service.entity.QuestionWrapper;
import com.microservice.question_service.entity.Response;

@Component("questionService2")
public class Qservice
{
	
	
	@Autowired
	Repo questionrepository;
	
	
	public ResponseEntity<List<Quest>> GetAllQuestions() {
		// TODO Auto-generated method stub
		try {
            List<Quest> questions = questionrepository.findAll();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
		//return questionrepository.findAll();
	}

	public ResponseEntity<List<Quest>> GetQuestionsByCategory(String category) {
		// TODO Auto-generated method stub
		try {
            List<Quest> questions = questionrepository.findByCategory(category);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
		// return questionrepository.findByCategory(category);
	}

	public ResponseEntity<String> addQuestion(Quest question) {
		// TODO Auto-generated method stub\
		
		try
		{
		questionrepository.save(question);
		return new ResponseEntity<>("Questions added successfully!", HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}

	public ResponseEntity<String> deleteQuestion(Integer id) {
		// TODO Auto-generated method stub
		Optional<Quest> questions=questionrepository.findById(id);
		try
		{
			
			if (!questions.isPresent()) {
		        return new ResponseEntity<>(" Question is not present" , HttpStatus.NOT_FOUND);
		    }
			

			questionrepository.deleteById(id);
			
			return new ResponseEntity<>("successfully delete", HttpStatus.OK);
		}
			catch(Exception e)
			{
				e.printStackTrace();
				
				
			}
		return new ResponseEntity<>("Question is not deleted",HttpStatus.INTERNAL_SERVER_ERROR);
		
		
		
		// questionrepository.deleteById(id);
		// return "deleted successfully" ;
	}

	public ResponseEntity<String> updateQuestion(Quest question, Integer id) {
		// TODO Auto-generated method stub
		Optional<Quest> questions=questionrepository.findById(id);
		
		try
		{
			
			if (!questions.isPresent()) {
		        return new ResponseEntity<>(" Question id not present" , HttpStatus.NOT_FOUND);
		    }
		
		question.setId(id);
		questionrepository.save(question);
		return new ResponseEntity<>("updated successfully!",HttpStatus.OK);
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity<>("not updated successfully!",HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	public ResponseEntity<List<Integer>> getQuestionsforQuiz(String category, Integer numberofQuestions) {
        List<Integer> questions = questionrepository.findRandomQuestionByCategory(category, numberofQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionids) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Quest> questions = new ArrayList<>();

        for (Integer id : questionids) {
            questions.add(questionrepository.findById(id).get());
        }

        for (Quest question : questions) {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

	public ResponseEntity<Integer> getscore(List<Response> responses) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		
		
		int right=0;
		for(Response response : responses)
		{
			Quest questions = questionrepository.findById(response.getId()).get();
			if(response.getResponse().equals(questions.getRightAnswer()))
			{
			right++;
			}
			
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	
	}

}
