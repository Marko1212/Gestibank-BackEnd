package com.gesti.bank.dto;

public class BankRuleResponseDTO {
	private int idBankRules;
	private float percent;
	private String ruleName;
	public int getIdBankRules() {
		return idBankRules;
	}
	public void setIdBankRules(int idBankRules) {
		this.idBankRules = idBankRules;
	}
	public float getPercent() {
		return percent;
	}
	public void setPercent(float percent) {
		this.percent = percent;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
	
}
