package com.gesti.bank.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the bank_account database table.
 * 
 */
@Entity
@Table(name="bank_account")
@NamedQuery(name="BankAccount.findAll", query="SELECT b FROM BankAccount b")
public class BankAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_bank_account")
	private int idBankAccount;

	@Column(name="bank_account_number")
	private String bankAccountNumber;

	@Column(name="bank_account_status")
	private byte bankAccountStatus;

	//bi-directional many-to-one association to BankAccountType
	@ManyToOne
	@JoinColumn(name="bank_account_type_id_bank_account_type")
	private BankAccountType bankAccountType;

	//bi-directional many-to-one association to UserAccount
	@ManyToOne
	@JoinColumn(name="user_account_id_user_account")
	private UserAccount userAccount;

	//bi-directional many-to-one association to BankRule
	@ManyToOne
	@JoinColumn(name="bank_rules_id_bank_rules")
	private BankRule bankRule;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="bankAccount1")
	private List<Transaction> transactions1;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="bankAccount2")
	private List<Transaction> transactions2;

	public BankAccount() {
	}

	public int getIdBankAccount() {
		return this.idBankAccount;
	}

	public void setIdBankAccount(int idBankAccount) {
		this.idBankAccount = idBankAccount;
	}

	public String getBankAccountNumber() {
		return this.bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public byte getBankAccountStatus() {
		return this.bankAccountStatus;
	}

	public void setBankAccountStatus(byte bankAccountStatus) {
		this.bankAccountStatus = bankAccountStatus;
	}

	public BankAccountType getBankAccountType() {
		return this.bankAccountType;
	}

	public void setBankAccountType(BankAccountType bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public BankRule getBankRule() {
		return this.bankRule;
	}

	public void setBankRule(BankRule bankRule) {
		this.bankRule = bankRule;
	}

	public List<Transaction> getTransactions1() {
		return this.transactions1;
	}

	public void setTransactions1(List<Transaction> transactions1) {
		this.transactions1 = transactions1;
	}

	public Transaction addTransactions1(Transaction transactions1) {
		getTransactions1().add(transactions1);
		transactions1.setBankAccount1(this);

		return transactions1;
	}

	public Transaction removeTransactions1(Transaction transactions1) {
		getTransactions1().remove(transactions1);
		transactions1.setBankAccount1(null);

		return transactions1;
	}

	public List<Transaction> getTransactions2() {
		return this.transactions2;
	}

	public void setTransactions2(List<Transaction> transactions2) {
		this.transactions2 = transactions2;
	}

	public Transaction addTransactions2(Transaction transactions2) {
		getTransactions2().add(transactions2);
		transactions2.setBankAccount2(this);

		return transactions2;
	}

	public Transaction removeTransactions2(Transaction transactions2) {
		getTransactions2().remove(transactions2);
		transactions2.setBankAccount2(null);

		return transactions2;
	}

}