package com.hcl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.entity.User;

@RestController
public class HomeController {
	
	@GetMapping("/message")
	public String getMessage() {
		
		return "Hello people";
	}
	
//	@GetMapping("/user")
//	public User getUser() {
//		
//		User user = new User(100, "abc@123", "Nikhil");
//		
//		return user;
//	}
	
	@GetMapping("/personalMessage")
	public String messageForAdmin() {
		
		return "Hi, There is money hidden right under your house. Git that out and enjoy";
	}
	

}
