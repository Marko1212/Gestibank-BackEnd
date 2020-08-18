package com.gesti.bank.service;

public interface EmailService {
	
	void sendVerificationEmail(String name, String username, String password, String emailTo);
	
	void sendConfirmationOfReceiptOfRequestEmail(String name, String emailTo);

	void sendRejectionEmail(String name, String email);
	
	void sendChequeBookCreationConfirmationEmail(String name, String bankAccountTypeName, String bankAccountNumber, String emailTo);

	void sendSavingAccountCreationEmail(String firstname, String emailTo);

	void sendPasswordResetEmail(String firstname, String passwordResetLink, String emailTo);

}
