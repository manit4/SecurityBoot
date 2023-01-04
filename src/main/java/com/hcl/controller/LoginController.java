package com.hcl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
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

}
