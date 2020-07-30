package com.gesti.bank.dto;

import java.util.Date;

public class TransactionResponseDTO {

	private int bankAccountFromId;
	private int bankAccountToId;
	private String transactionTypeName;
	private String description;
	private float amount;
	private Date time;
	private int idTransaction;
	
	public TransactionResponseDTO(int bankAccountFromId, int bankAccountToId, String transactionTypeName,
			String description, float amount, Date time, int idTransaction) {
		super();
		this.bankAccountFromId = bankAccountFromId;
		this.bankAccountToId = bankAccountToId;
		this.transactionTypeName = transactionTypeName;
		this.description = description;
		this.amount = amount;
		this.time = time;
		this.idTransaction = idTransaction;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getIdTransaction() {
		return idTransaction;
	}
	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}
	public void setTransactionTypeName(String transactionTypeName) {
		this.transactionTypeName = transactionTypeName;
	}
	public int getBankAccountFromId() {
		return bankAccountFromId;
	}
	public void setBankAccountFromId(int bankAccountFromId) {
		this.bankAccountFromId = bankAccountFromId;
	}
	public int getBankAccountToId() {
		return bankAccountToId;
	}
	public void setBankAccountToId(int bankAccountToId) {
		this.bankAccountToId = bankAccountToId;
	}
	
	public String getTransactionTypeName() {
		return transactionTypeName;
	}

	public void setTransactionTypeId(String transactionTypeName) {
		this.transactionTypeName = transactionTypeName;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
}
