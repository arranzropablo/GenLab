package com.genlab.serverapplication.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.genlab.serverapplication.models.Problem;

public interface ProblemRepository extends CrudRepository<Problem, Integer> {
	
	public List<Problem> findBySectionid(int sectionid);
}
