package com.gesti.bank.dto;

import java.util.Date;
import java.util.List;

public class GetUnresolvedRequestsForAgentResponseDTO {
	private int idRequest;
	private String title;
	private String description;
	private Date time;
	
	private int idClient;
	private String email;
	private String firstname;
	private String lastname;
	private String marriageStatus;
	private int numberOfChildren;
	private String pass;
	private String phone;
	private String username;
	private String additionalInfoAddress;
	private String city;
	private String country;
	private String homeNumber;
	private String street;
	private int zip;
	private boolean valid;
	
	List<FileInfoResponseDTO> files;

	public GetUnresolvedRequestsForAgentResponseDTO(int idRequest, String title, String description, Date time,
			int idClient, String email, String firstname, String lastname, String marriageStatus, int numberOfChildren,
			String pass, String phone, String username, String additionalInfoAddress, String city, String country,
			String homeNumber, String street, int zip, boolean valid, List<FileInfoResponseDTO> files) {
		super();
		this.idRequest = idRequest;
		this.title = title;
		this.description = description;
		this.time = time;
		this.idClient = idClient;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.marriageStatus = marriageStatus;
		this.numberOfChildren = numberOfChildren;
		this.pass = pass;
		this.phone = phone;
		this.username = username;
		this.additionalInfoAddress = additionalInfoAddress;
		this.city = city;
		this.country = country;
		this.homeNumber = homeNumber;
		this.street = street;
		this.zip = zip;
		this.files = files;
		this.valid = valid;
	}

	public int getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
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

	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(int numberOfChildren) {
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

	public List<FileInfoResponseDTO> getFiles() {
		return files;
	}

	public void setFiles(List<FileInfoResponseDTO> files) {
		this.files = files;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
	
	
}
