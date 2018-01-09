package com.genlab.serverapplication.services.testsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genlab.serverapplication.models.Test;
import com.genlab.serverapplication.repositories.TestRepository;

@Service
public class TestServiceImpl implements TestService{
	
	@Autowired
	private TestRepository testRepository;
	
	public List<Test> getAllTest() {
		return (List<Test>) testRepository.findAll();
	}
	
	public Test getTest(int id) {
		return testRepository.findOne(id);
	}
	
	public int saveTest(Test t) {
		return testRepository.save(t).getId();
	}
	
	public List<Test> getTestBySection(int sectionid){
		return testRepository.findBySectionid(sectionid);
	}
	
	public void deleteTest(int id) {
		testRepository.delete(id);
	}
	
	public boolean existsTest(int id) {
		return testRepository.exists(id);
	}

}
