package com.gesti.bank.dto;

public class SimpleMessageResponseDTO {
	private String message;

	public SimpleMessageResponseDTO(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
