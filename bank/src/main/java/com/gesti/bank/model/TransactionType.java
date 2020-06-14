package com.gesti.bank.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the transaction_type database table.
 * 
 */
@Entity
@Table(name="transaction_type")
@NamedQuery(name="TransactionType.findAll", query="SELECT t FROM TransactionType t")
public class TransactionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_transaction_type")
	private int idTransactionType;

	private String name;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="transactionType")
	private List<Transaction> transactions;

	public TransactionType() {
	}

	public int getIdTransactionType() {
		return this.idTransactionType;
	}

	public void setIdTransactionType(int idTransactionType) {
		this.idTransactionType = idTransactionType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setTransactionType(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setTransactionType(null);

		return transaction;
	}

}