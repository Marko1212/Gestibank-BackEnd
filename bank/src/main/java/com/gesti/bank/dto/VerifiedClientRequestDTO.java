package com.gesti.bank.dto;

public class VerifiedClientRequestDTO {
	
	private int idClient;
	private int idRequest;
	private boolean valid;
	private int idAgent;
	
	
	
	public VerifiedClientRequestDTO() {
		super();
	}
	public VerifiedClientRequestDTO(int idClient, int idRequest, boolean valid, int idAgent) {
		super();
		this.idClient = idClient;
		this.idRequest = idRequest;
		this.valid = valid;
		this.idAgent = idAgent;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public int getIdRequest() {
		return idRequest;
	}
	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public int getIdAgent() {
		return idAgent;
	}
	public void setIdAgent(int idAgent) {
		this.idAgent = idAgent;
	}
	
	

}
