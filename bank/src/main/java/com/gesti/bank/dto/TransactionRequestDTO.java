package com.gesti.bank.dto;

public class TransactionRequestDTO {

	private int loggedInUserId;
	private int bankAccountFromId;
	private int bankAccountToId;
	private int transactionTypeId;
	private String description;
	private float amount;
	private String bankAccountNumberTo;	

	public TransactionRequestDTO() {
		super();
	}
	
	public int getLoggedInUserId() {
		return loggedInUserId;
	}
	public void setLoggedInUserId(int loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
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
	
	public int getTransactionTypeId() {
		return transactionTypeId;
	}

	public void setTransactionTypeId(int transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
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
	
	public String getBankAccountNumberTo() {
		return bankAccountNumberTo;
	}

	public void setBankAccountNumberTo(String bankAccountNumberTo) {
		this.bankAccountNumberTo = bankAccountNumberTo;
	}
}
