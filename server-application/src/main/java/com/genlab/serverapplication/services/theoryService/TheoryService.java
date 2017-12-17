package com.genlab.serverapplication.services.theoryService;

import java.util.List;

import com.genlab.serverapplication.models.Theory;

public interface TheoryService {
	
	public List<Theory> getAllTheory();
	
	public Theory getTheory(int id);
	
	public int saveTheory(Theory t);
	
	public void deleteTheory(int id);
	
	public List<Theory> getTheoryBySection(int sectionid);
	
	public boolean existsTheory(int id);
	
}
