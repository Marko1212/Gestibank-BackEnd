package com.gesti.bank.dto;

import java.util.List;

public class AssignClientRequestDTO {
	private List<ClientRequestForAdminDTO> clients;


	public AssignClientRequestDTO() {
		super();
	}

	public AssignClientRequestDTO(List<ClientRequestForAdminDTO> clients) {
		super();
		this.clients = clients;
	}

	public List<ClientRequestForAdminDTO> getClients() {
		return clients;
	}

	public void setClients(List<ClientRequestForAdminDTO> clients) {
		this.clients = clients;
	}

}
