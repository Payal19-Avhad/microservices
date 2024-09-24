package com.microservice.question_service.entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Quiz 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	
	@ElementCollection
	private List<Integer> questionids;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
//	public List<Question> getQuestions() {
//		return questions;
//	}
//	public void setQuestions(List<Question> questions) {
//		this.questions = questions;
//	}
	public List<Integer> getQuestionids() {
		return questionids;
	}
	public void setQuestionids(List<Integer> questionids) {
		this.questionids = questionids;
	}
	@Override
	public String toString() {
		return "Quiz [id=" + id + ", title=" + title + ", questionids=" + questionids + "]";
	}
		
	
	
	

}
