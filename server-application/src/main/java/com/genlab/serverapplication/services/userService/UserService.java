package com.genlab.serverapplication.services.userService;

import com.genlab.serverapplication.models.User;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserService {
	public User getUser(String email);
	
	public void addUser(User user);

    public boolean exists(String email);

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

	public List<User> getAllUsers();

}
