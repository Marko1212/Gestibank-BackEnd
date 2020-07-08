package com.gesti.bank.service;

public interface EmailService {
	
	void sendVerificationEmail(String name, String username, String password, String emailTo);
}
