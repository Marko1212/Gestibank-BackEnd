package com.gesti.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gesti.bank.service.EmailService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("/proba")
	public String testMethod() {
		emailService.sendVerificationEmail("Pera", "pera", "perica@mailinator.com");
		return "it works!";
	}

}
