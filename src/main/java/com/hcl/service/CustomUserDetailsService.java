package com.hcl.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hcl.entity.CustomUserDetails;
import com.hcl.entity.User;
import com.hcl.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("inisde loadUserByUsername");
		User user = null;
		
		Optional<User> optional = userRepository.findById(username);
		
		if(optional.isPresent()) {
			
			user = optional.get();
		}
		
		return new CustomUserDetails(user);
	}
	
	

}
