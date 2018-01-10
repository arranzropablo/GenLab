package com.genlab.serverapplication;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.genlab.serverapplication.models.User;
import com.genlab.serverapplication.services.userService.UserServiceImpl;

public class GenlabUserDetailsService implements UserDetailsService{
	private static Logger log = Logger.getLogger(GenlabUserDetailsService.class);
	@Autowired
	private UserServiceImpl service;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			User u = service.getUser(email);
			
			ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();
	        for (String r : u.getRoles().split("[,]")) {
	        	roles.add(new SimpleGrantedAuthority("ROLE_" + r));
		        log.info("Roles for " + email + " include " + roles.get(roles.size()-1));
	        }
	        
			return new org.springframework.security.core.userdetails.User(
	        		u.getEmail(), u.getPassword(), roles); 
		}catch(Exception e) {
			log.info("No such user: " + email);
			throw new UsernameNotFoundException("No such user: " + email);
		}
	}

}
