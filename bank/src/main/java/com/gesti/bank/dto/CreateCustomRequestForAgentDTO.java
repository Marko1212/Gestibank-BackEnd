package com.gesti.bank.dto;

public class CreateCustomRequestForAgentDTO {
	private int loggedInUserId;
	private String description;
	private String title;
	
	public int getLoggedInUserId() {
		return loggedInUserId;
	}
	public void setLoggedInUserId(int loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
