package com.genlab.serverapplication.services.testsService;

import java.util.List;

import com.genlab.serverapplication.models.TestQuestion;

public interface TestQuestionService {
	
	public List<TestQuestion> getAllQuestions();
	
	public List<TestQuestion> getAllQuestionsByTest(int id);
	
	public TestQuestion getQuestion(int id);
	
	public TestQuestion saveQuestion(TestQuestion t);
	
	public void deleteQuestion(int id);
	
	public boolean existsQuestion(int id);
}
