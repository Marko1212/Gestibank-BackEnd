package com.gesti.bank.dto;

public class ClientRequestForAdminDTO {
	private int idUserAccount;

	private String email;

	private String firstname;

	private String lastname;

	private String phone;
	
	private String agent;
	
	private boolean checked;

	public ClientRequestForAdminDTO(int idUserAccount, String email, String firstname, String lastname, String phone,
			String agent, boolean checked) {
		super();
		this.idUserAccount = idUserAccount;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.agent = agent;
		this.checked = checked;
	}

	public int getIdUserAccount() {
		return idUserAccount;
	}

	public void setIdUserAccount(int idUserAccount) {
		this.idUserAccount = idUserAccount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
}
