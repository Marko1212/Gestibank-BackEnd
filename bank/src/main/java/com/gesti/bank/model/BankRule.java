package com.gesti.bank.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the bank_rules database table.
 * 
 */
@Entity
@Table(name="bank_rules")
@NamedQuery(name="BankRule.findAll", query="SELECT b FROM BankRule b")
public class BankRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_bank_rules")
	private int idBankRules;

	private float percent;

	@Column(name="rule_name")
	private String ruleName;

	//bi-directional many-to-one association to BankAccount
	@OneToMany(mappedBy="bankRule")
	private List<BankAccount> bankAccounts;

	public BankRule() {
	}

	public int getIdBankRules() {
		return this.idBankRules;
	}

	public void setIdBankRules(int idBankRules) {
		this.idBankRules = idBankRules;
	}

	public float getPercent() {
		return this.percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public List<BankAccount> getBankAccounts() {
		return this.bankAccounts;
	}

	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public BankAccount addBankAccount(BankAccount bankAccount) {
		getBankAccounts().add(bankAccount);
		bankAccount.setBankRule(this);

		return bankAccount;
	}

	public BankAccount removeBankAccount(BankAccount bankAccount) {
		getBankAccounts().remove(bankAccount);
		bankAccount.setBankRule(null);

		return bankAccount;
	}

}