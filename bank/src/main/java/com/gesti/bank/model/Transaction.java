package com.gesti.bank.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_transaction")
	private int idTransaction;

	private float amount;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	//bi-directional many-to-one association to BankAccount
	@ManyToOne
	@JoinColumn(name="bank_account_id_bank_account_from")
	private BankAccount bankAccount1;

	//bi-directional many-to-one association to BankAccount
	@ManyToOne
	@JoinColumn(name="bank_account_id_bank_account_to")
	private BankAccount bankAccount2;

	//bi-directional many-to-one association to TransactionType
	@ManyToOne
	@JoinColumn(name="transaction_type_id_transaction_type")
	private TransactionType transactionType;

	public Transaction() {
	}

	public int getIdTransaction() {
		return this.idTransaction;
	}

	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}

	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public BankAccount getBankAccount1() {
		return this.bankAccount1;
	}

	public void setBankAccount1(BankAccount bankAccount1) {
		this.bankAccount1 = bankAccount1;
	}

	public BankAccount getBankAccount2() {
		return this.bankAccount2;
	}

	public void setBankAccount2(BankAccount bankAccount2) {
		this.bankAccount2 = bankAccount2;
	}

	public TransactionType getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

}