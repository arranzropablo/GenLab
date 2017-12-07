package com.genlab.serverapplication.services.theoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genlab.serverapplication.models.Theory;
import com.genlab.serverapplication.repositories.TheoryRepository;

@Service
public class TheoryServiceImpl implements TheoryService {
	
	@Autowired
	private TheoryRepository theoryRepository;
	
	public List<Theory> getAllTheory() {
		return (List<Theory>) theoryRepository.findAll();
	}
	
	public Theory getTheory(int id) {
		return theoryRepository.findOne(id);
	}
	
	public int saveTheory(Theory t) {
		return theoryRepository.save(t).getId();
	}
	
	public List<Theory> getTheoryBySection(int sectionid){
		return theoryRepository.findBySectionid(sectionid);
	}
	
	public void deleteTheory(int id) {
		theoryRepository.delete(id);
	}
	
}
