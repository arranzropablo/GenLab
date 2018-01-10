package com.genlab.serverapplication.services.userService;

import com.genlab.serverapplication.models.User;


public interface UserService {
	public User getUser(String email);
	
	public void addUser(User user);
}
