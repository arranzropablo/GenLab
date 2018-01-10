package com.genlab.serverapplication.repositories;

import org.springframework.data.repository.CrudRepository;
import com.genlab.serverapplication.models.User;


public interface UserRepository extends CrudRepository<User, String>{
	
}
