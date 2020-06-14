package com.gesti.bank.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the bank_account_type database table.
 * 
 */
@Entity
@Table(name="bank_account_type")
@NamedQuery(name="BankAccountType.findAll", query="SELECT b FROM BankAccountType b")
public class BankAccountType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_bank_account_type")
	private int idBankAccountType;

	private String name;

	//bi-directional many-to-one association to BankAccount
	@OneToMany(mappedBy="bankAccountType")
	private List<BankAccount> bankAccounts;

	public BankAccountType() {
	}

	public int getIdBankAccountType() {
		return this.idBankAccountType;
	}

	public void setIdBankAccountType(int idBankAccountType) {
		this.idBankAccountType = idBankAccountType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BankAccount> getBankAccounts() {
		return this.bankAccounts;
	}

	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public BankAccount addBankAccount(BankAccount bankAccount) {
		getBankAccounts().add(bankAccount);
		bankAccount.setBankAccountType(this);

		return bankAccount;
	}

	public BankAccount removeBankAccount(BankAccount bankAccount) {
		getBankAccounts().remove(bankAccount);
		bankAccount.setBankAccountType(null);

		return bankAccount;
	}

}