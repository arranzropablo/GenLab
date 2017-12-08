package com.genlab.serverapplication.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.genlab.serverapplication.models.Test;

public interface TestRepository extends CrudRepository<Test, Integer>{
	
	public List<Test> findBySectionid(int sectionid);
}
