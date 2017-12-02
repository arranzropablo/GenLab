package com.genlab.serverapplication.services;

import com.genlab.serverapplication.models.Theory;

import java.util.List;

public interface TheoryService {
	
	public List<Theory> getAllTheory();
	
	public Theory getTheory(long id);
	
	public void saveTheory(Theory t);
	
	public void deleteTheory(long id);
	
	public boolean existsTheory(long id);
	
}
