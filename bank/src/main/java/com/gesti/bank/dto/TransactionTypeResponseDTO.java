package com.gesti.bank.dto;

public class TransactionTypeResponseDTO {
	private int idTransactionType;
	private String name;
	
	public TransactionTypeResponseDTO(int idTransactionType, String name) {
		super();
		this.idTransactionType = idTransactionType;
		this.name = name;
	}
	public int getIdTransactionType() {
		return idTransactionType;
	}
	public void setIdTransactionType(int idTransactionType) {
		this.idTransactionType = idTransactionType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
