package com.gesti.bank.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.gesti.bank.model.Address;
import com.gesti.bank.model.BankAccount;
import com.gesti.bank.model.Document;
import com.gesti.bank.model.Notification;
import com.gesti.bank.model.Request;
import com.gesti.bank.model.Role;

public class ClientResponseForAdminDTO {
	
	private int idUserAccount;

	private String email;

	private String firstname;

	private String lastname;

	private String phone;
	

	public ClientResponseForAdminDTO(int idUserAccount, String email, String firstname, String lastname, String phone) {
		super();
		this.idUserAccount = idUserAccount;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
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



}
