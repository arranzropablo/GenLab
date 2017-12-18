package com.genlab.serverapplication.services.testsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genlab.serverapplication.models.TestQuestion;
import com.genlab.serverapplication.repositories.TestQuestionRepository;

@Service
public class TestQuestionServiceImpl implements TestQuestionService{
	
	@Autowired
	private TestQuestionRepository testQuestionRepository;
	
	public List<TestQuestion> getAllQuestions() {
		return (List<TestQuestion>) testQuestionRepository.findAll();
	}
	
	public List<TestQuestion> getAllQuestionsByTest(int id) {
		return (List<TestQuestion>) testQuestionRepository.findAll();
	}
	
	public 	TestQuestion getQuestion(int id) {
		return testQuestionRepository.findOne(id);
	}
	
	public TestQuestion saveQuestion(TestQuestion t) {
		return testQuestionRepository.save(t);
	}
	
	public void deleteQuestion(int id) {
		testQuestionRepository.delete(id);
	}
	
	public boolean existsQuestion(int id) {
		return testQuestionRepository.exists(id);
	}

}
