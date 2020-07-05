package com.gesti.bank.dto;

import java.util.List;

public class VerifiedClientsRequestDTO {
	
	private List<VerifiedClientRequestDTO> validated;

	
	
	public VerifiedClientsRequestDTO() {
		super();
	}

	public VerifiedClientsRequestDTO(List<VerifiedClientRequestDTO> validated) {
		super();
		this.validated = validated;
	}

	public List<VerifiedClientRequestDTO> getValidated() {
		return validated;
	}

	public void setValidated(List<VerifiedClientRequestDTO> validated) {
		this.validated = validated;
	}
	
	
	
	

}
