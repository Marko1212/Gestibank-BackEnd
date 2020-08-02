package com.gesti.bank.dto;

public class NotificationResponseDTO {
	private int idNotification;
	private String message;

	public NotificationResponseDTO(int idNotification, String message) {
		super();
		this.idNotification = idNotification;
		this.message = message;
	}

	public int getIdNotification() {
		return idNotification;
	}

	public void setIdNotification(int idNotification) {
		this.idNotification = idNotification;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
