package com.gesti.bank.dto;

public class CreateSavingAccountRequestDTO {
	int loggedInAgentId;
	int clientId;
	public int getLoggedInAgentId() {
		return loggedInAgentId;
	}
	public void setLoggedInAgentId(int loggedInAgentId) {
		this.loggedInAgentId = loggedInAgentId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	

}
