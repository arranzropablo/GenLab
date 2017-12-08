package com.genlab.serverapplication.repositories;

import org.springframework.data.repository.CrudRepository;
import com.genlab.serverapplication.models.TestQuestion;

public interface TestQuestionRepository extends CrudRepository<TestQuestion, Integer>{
	
}
