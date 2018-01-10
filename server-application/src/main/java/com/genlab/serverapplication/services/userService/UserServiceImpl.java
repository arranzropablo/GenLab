package com.genlab.serverapplication.services.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genlab.serverapplication.models.User;
import com.genlab.serverapplication.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getUser(String email) {
		return userRepository.findOne(email);
	}

	@Override
	public void addUser(User user) {
		userRepository.save(user);		
	}

}
