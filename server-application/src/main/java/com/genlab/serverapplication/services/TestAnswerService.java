package com.genlab.serverapplication.services;

import java.util.List;
import com.genlab.serverapplication.models.TestAnswer;

public interface TestAnswerService {
	
	public List<TestAnswer> getAllAnswers();
	
	public List<TestAnswer> getAllAnswersByQuestion(int id);
	
	public TestAnswer getAnswer(int id);
	
	public int saveAnswer(TestAnswer t);
	
	public void deleteAnswer(int id);
	
	public boolean existsAnswer(int id);
}
