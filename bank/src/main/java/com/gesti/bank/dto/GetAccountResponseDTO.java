package com.gesti.bank.dto;

public class GetAccountResponseDTO {
	private String email;
	private String firstname;
	private String lastname;
	private String pass;
	private String phone;
	private String username;
	private String additionalInfoAddress;
	private String city;
	private String country;
	private String homeNumber;
	private String street;
	private int zip;
	private int idUserAccount;
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
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAdditionalInfoAddress() {
		return additionalInfoAddress;
	}
	public void setAdditionalInfoAddress(String additionalInfoAddress) {
		this.additionalInfoAddress = additionalInfoAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHomeNumber() {
		return homeNumber;
	}
	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public int getIdUserAccount() {
		return idUserAccount;
	}
	public void setIdUserAccount(int idUserAccount) {
		this.idUserAccount = idUserAccount;
	}
	public GetAccountResponseDTO(String email, String firstname, String lastname, String pass, String phone,
			String username, String additionalInfoAddress, String city, String country, String homeNumber,
			String street, int zip, int idUserAccount) {
		super();
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.pass = pass;
		this.phone = phone;
		this.username = username;
		this.additionalInfoAddress = additionalInfoAddress;
		this.city = city;
		this.country = country;
		this.homeNumber = homeNumber;
		this.street = street;
		this.zip = zip;
		this.idUserAccount = idUserAccount;
	}
	

}
