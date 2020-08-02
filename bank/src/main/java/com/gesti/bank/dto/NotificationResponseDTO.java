package com.gesti.bank.dto;

import java.util.Date;

public class NotificationResponseDTO {
	private int idNotification;
	private String message;
	private Date time;

	public NotificationResponseDTO(int idNotification, String message, Date time) {
		super();
		this.idNotification = idNotification;
		this.message = message;
		this.time = time;
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
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
