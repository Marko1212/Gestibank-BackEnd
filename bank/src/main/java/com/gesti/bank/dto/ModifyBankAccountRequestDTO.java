package com.gesti.bank.dto;

public class ModifyBankAccountRequestDTO {
	private int idBankAccount;
	private int idBankAccountType;
	private int idBankRules;
	public int getIdBankAccount() {
		return idBankAccount;
	}
	public void setIdBankAccount(int idBankAccount) {
		this.idBankAccount = idBankAccount;
	}
	public int getIdBankAccountType() {
		return idBankAccountType;
	}
	public void setIdBankAccountType(int idBankAccountType) {
		this.idBankAccountType = idBankAccountType;
	}
	public int getIdBankRules() {
		return idBankRules;
	}
	public void setIdBankRules(int idBankRules) {
		this.idBankRules = idBankRules;
	}
	
	
}
