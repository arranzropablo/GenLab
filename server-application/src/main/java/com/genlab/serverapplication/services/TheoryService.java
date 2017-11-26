package com.genlab.serverapplication.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genlab.serverapplication.models.Theory;
import com.genlab.serverapplication.repositories.TheoryRepository;

@Service
public class TheoryService {
	@Autowired
	private TheoryRepository theoryRepository;
	
	public List<Theory> getAllTheory() {
		List<Theory> theories = new ArrayList<Theory>();
		theoryRepository.findAll().forEach(theories::add);
		return theories;
	}
	
	public Theory getTheory(long id) {
		return theoryRepository.findOne(id);
	}
	
	public void addTheory(Theory t) {
		theoryRepository.save(t);
	}
	
	public void deleteTheory(long id) {
		theoryRepository.delete(id);
	}
}
