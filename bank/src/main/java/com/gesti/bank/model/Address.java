package com.gesti.bank.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_address")
	private int idAddress;

	@Column(name="additional_info")
	private String additionalInfo;

	private String city;

	private String country;

	@Column(name="home_number")
	private String homeNumber;

	private String street;

	private int zip;

	//bi-directional many-to-one association to UserAccount
	@OneToMany(mappedBy="address")
	private List<UserAccount> userAccounts;

	public Address() {
	}

	public int getIdAddress() {
		return this.idAddress;
	}

	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}

	public String getAdditionalInfo() {
		return this.additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHomeNumber() {
		return this.homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getZip() {
		return this.zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public List<UserAccount> getUserAccounts() {
		return this.userAccounts;
	}

	public void setUserAccounts(List<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}

	public UserAccount addUserAccount(UserAccount userAccount) {
		getUserAccounts().add(userAccount);
		userAccount.setAddress(this);

		return userAccount;
	}

	public UserAccount removeUserAccount(UserAccount userAccount) {
		getUserAccounts().remove(userAccount);
		userAccount.setAddress(null);

		return userAccount;
	}

}