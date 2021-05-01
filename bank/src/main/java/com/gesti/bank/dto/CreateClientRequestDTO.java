package com.gesti.bank.dto;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CreateClientRequestDTO {
	private String email;
	private String firstname;
	private String lastname;
	private String marriageStatus;
	private String numberOfChildren;
	private String pass;
	private String phone;
	private String username;
	private String additionalInfoAddress;
	private String city;
	private String country;
	private String homeNumber;
	private String street;
	private int zip;
	
	
	
	public CreateClientRequestDTO(String json) {
		super();
		JSONObject jsonObject = new JSONObject(json);
		this.additionalInfoAddress = (jsonObject.getString("additionalInfoAddress"));
		this.city = (jsonObject.getString("city"));
		this.country = (jsonObject.getString("country"));
		this.email = (jsonObject.getString("email"));
		this.firstname = (jsonObject.getString("firstname"));
		this.homeNumber = (jsonObject.getString("homeNumber"));
		this.lastname = (jsonObject.getString("lastname"));
		this.marriageStatus = (jsonObject.getString("marriageStatus"));
		this.numberOfChildren = (jsonObject.getString("numberOfChildren"));
		this.pass = (jsonObject.getString("pass"));
		this.phone = (jsonObject.getString("phone"));
		this.street = (jsonObject.getString("street"));
		this.username = (jsonObject.getString("username"));
		this.zip = (jsonObject.getInt("zip"));
		
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
	public String getMarriageStatus() {
		return marriageStatus;
	}
	public void setMarriageStatus(String marriageStatus) {
		this.marriageStatus = marriageStatus;
	}
	public String getNumberOfChildren() {
		return numberOfChildren;
	}
	public void setNumberOfChildren(String numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
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
	
	
}
