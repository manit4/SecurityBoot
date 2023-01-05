package com.hcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hcl.entity.User;
import com.hcl.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/signin")
	public String signin() {
		System.out.println("inside signin()");
		return "login";
	}
	
	@GetMapping("/welcomePage")
	public String welcome() {
		System.out.println("inside welcome");
		return "welcome";
	}
	
	@GetMapping("/registrationPage")
	public String registrationPage() {
		System.out.println("inside registrationPage()...");
		return "registration";
	}
	
	@GetMapping("/register")
	public String registerUser(String id, String password, String name, String role, String isEnabled) {

		System.out.println("inside register 1"+isEnabled);
		role = "Role_"+role;
		
		String encodedPassword = passwordEncoder.encode(password);
		
		User user = new User(id, encodedPassword, name, role);
		
		if(isEnabled != null) {
			
			user.setEnabled(true);
		}
		
		userService.addUser(user);
		
		return "login";

	}

}
