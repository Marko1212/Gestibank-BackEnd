package com.gesti.bank.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
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
	
//	@Column(name="creation_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable=false)
	@Column(name="creation_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

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
	@OneToMany(mappedBy="bankAccountFrom")
	private List<Transaction> transactionsFrom;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="bankAccountTo")
	private List<Transaction> transactionsTo;

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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public List<Transaction> getTransactionsFrom() {
		return this.transactionsFrom;
	}

	public void setTransactionsFrom(List<Transaction> transactionsFrom) {
		this.transactionsFrom = transactionsFrom;
	}

	public Transaction addTransactionsFrom(Transaction transactionsFrom) {
		getTransactionsFrom().add(transactionsFrom);
		transactionsFrom.setBankAccountFrom(this);

		return transactionsFrom;
	}

	public Transaction removeTransactionsFrom(Transaction transactionsFrom) {
		getTransactionsFrom().remove(transactionsFrom);
		transactionsFrom.setBankAccountFrom(null);

		return transactionsFrom;
	}

	public List<Transaction> getTransactionsTo() {
		return this.transactionsTo;
	}

	public void setTransactionsTo(List<Transaction> transactionsTo) {
		this.transactionsTo = transactionsTo;
	}

	public Transaction addTransactionsTo(Transaction transactionsTo) {
		getTransactionsTo().add(transactionsTo);
		transactionsTo.setBankAccountTo(this);

		return transactionsTo;
	}

	public Transaction removeTransactionsTo(Transaction transactionsTo) {
		getTransactionsTo().remove(transactionsTo);
		transactionsTo.setBankAccountTo(null);

		return transactionsTo;
	}

}