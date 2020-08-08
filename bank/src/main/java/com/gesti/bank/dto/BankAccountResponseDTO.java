package com.gesti.bank.dto;

import java.util.Date;

import com.gesti.bank.model.BankAccountType;
import com.gesti.bank.model.BankRule;
import com.gesti.bank.model.UserAccount;

public class BankAccountResponseDTO {
	private int idBankAccount;
	private String bankAccountNumber;
	private int bankAccountTypeId;
	private String bankAccountTypeName;
	private int userAccountId;
	private String userAccountFullName;
	private int bankRuleId;
	private float bankRulePercent;
	private String bankRuleName;
	private Date creationDate;
	private boolean clientSavingAccountFlag;
	
	
	public BankAccountResponseDTO(int idBankAccount, String bankAccountNumber, int bankAccountTypeId,
			String bankAccountTypeName, int userAccountId, String userAccountFullName, int bankRuleId,
			float bankRulePercent, String bankRuleName, Date creationDate, boolean clientSavingAccountFlag) {
		super();
		this.idBankAccount = idBankAccount;
		this.bankAccountNumber = bankAccountNumber;
		this.bankAccountTypeId = bankAccountTypeId;
		this.bankAccountTypeName = bankAccountTypeName;
		this.userAccountId = userAccountId;
		this.userAccountFullName = userAccountFullName;
		this.bankRuleId = bankRuleId;
		this.bankRulePercent = bankRulePercent;
		this.bankRuleName = bankRuleName;
		this.creationDate = creationDate;
		this.clientSavingAccountFlag = clientSavingAccountFlag;
	}
	public int getIdBankAccount() {
		return idBankAccount;
	}
	public void setIdBankAccount(int idBankAccount) {
		this.idBankAccount = idBankAccount;
	}
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public int getBankAccountTypeId() {
		return bankAccountTypeId;
	}
	public void setBankAccountTypeId(int bankAccountTypeId) {
		this.bankAccountTypeId = bankAccountTypeId;
	}
	public String getBankAccountTypeName() {
		return bankAccountTypeName;
	}
	public void setBankAccountTypeName(String bankAccountTypeName) {
		this.bankAccountTypeName = bankAccountTypeName;
	}
	public int getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(int userAccountId) {
		this.userAccountId = userAccountId;
	}
	public String getUserAccountFullName() {
		return userAccountFullName;
	}
	public void setUserAccountFullName(String userAccountFullName) {
		this.userAccountFullName = userAccountFullName;
	}
	public int getBankRuleId() {
		return bankRuleId;
	}
	public void setBankRuleId(int bankRuleId) {
		this.bankRuleId = bankRuleId;
	}
	public float getBankRulePercent() {
		return bankRulePercent;
	}
	public void setBankRulePercent(float bankRulePercent) {
		this.bankRulePercent = bankRulePercent;
	}
	public String getBankRuleName() {
		return bankRuleName;
	}
	public void setBankRuleName(String bankRuleName) {
		this.bankRuleName = bankRuleName;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public boolean isClientSavingAccountFlag() {
		return clientSavingAccountFlag;
	}
	public void setSavingAccountFlag(boolean clientSavingAccountFlag) {
		this.clientSavingAccountFlag = clientSavingAccountFlag;
	}
	
	
}
