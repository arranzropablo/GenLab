package com.genlab.serverapplication.services.userService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.genlab.serverapplication.models.User;
import com.genlab.serverapplication.repositories.UserRepository;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService, UserDetailsService  {
	
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

    @Override
    public boolean exists(String email) {
        return userRepository.exists(email);
    }

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			User u = getUser(email);

			ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();
			for (String r : u.getRoles().split("[,]")) {
				roles.add(new SimpleGrantedAuthority("ROLE_" + r));
			}
			return new org.springframework.security.core.userdetails.User(
					u.getEmail(), u.getPassword(), roles);
		}catch(Exception e) {
			throw new UsernameNotFoundException("No such user: " + email);
		}
	}

}
