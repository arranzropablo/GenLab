package com.genlab.serverapplication.services.testsService;

import java.util.List;

import com.genlab.serverapplication.models.Test;

public interface TestService {
	
	public List<Test> getAllTest();
	
	public Test getTest(int id);
	
	public int saveTest(Test t);
	
	public void deleteTest(int id);
	
	public List<Test> getTestBySection(int sectionid);
	
	public boolean existsTest(int id);
}
