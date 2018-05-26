package com.genlab.serverapplication.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.genlab.serverapplication.models.Theory;

public interface TheoryRepository extends CrudRepository<Theory, Integer>{
	
	public List<Theory> findBySectionid(int sectionid);
	
}
