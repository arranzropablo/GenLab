package com.genlab.serverapplication.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.genlab.serverapplication.models.TestAnswer;
import com.genlab.serverapplication.repositories.TestAnswerRepository;

@Service
public class TestAnswerServiceImpl implements TestAnswerService{
	
	@Autowired
	private TestAnswerRepository testAnswerRepository;
	
	public List<TestAnswer> getAllAnswers() {
		return (List<TestAnswer>) testAnswerRepository.findAll();
	}
	
	public List<TestAnswer> getAllAnswersByQuestion(int id) {
		return (List<TestAnswer>) testAnswerRepository.findAll();
	}
	
	public 	TestAnswer getAnswer(int id) {
		return testAnswerRepository.findOne(id);
	}
	
	public int saveAnswer(TestAnswer t) {
		return testAnswerRepository.save(t).getId();
	}
	
	public void deleteAnswer(int id) {
		testAnswerRepository.delete(id);
	}
	
	public boolean existsAnswer(int id) {
		return testAnswerRepository.exists(id);
	}

}
