package com.gesti.bank.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user_account database table.
 * 
 */
@Entity
@Table(name="user_account")
@NamedQuery(name="UserAccount.findAll", query="SELECT u FROM UserAccount u")
public class UserAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user_account")
	private int idUserAccount;

	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	private String firstname;

	private String lastname;

	@Column(name="marriage_status")
	private String marriageStatus;

	@Column(name="number_of_children")
	private int numberOfChildren;

	private String pass;

	private String phone;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	private String username;

	private byte valid;

	//bi-directional many-to-one association to BankAccount
	@OneToMany(mappedBy="userAccount")
	private List<BankAccount> bankAccounts;

	//bi-directional many-to-one association to Document
	@OneToMany(mappedBy="userAccount")
	private List<Document> documents;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="userAccount")
	private List<Notification> notifications;

	//bi-directional many-to-one association to Request
	@OneToMany(mappedBy="userAccountFrom")
	private List<Request> requestsFrom;

	//bi-directional many-to-one association to Request
	@OneToMany(mappedBy="userAccountTo")
	private List<Request> requestsTo;

	//bi-directional many-to-one association to Address
	@ManyToOne
	private Address address;

	//bi-directional many-to-one association to Role
	@ManyToOne
	private Role role;

	public UserAccount() {
	}

	public int getIdUserAccount() {
		return this.idUserAccount;
	}

	public void setIdUserAccount(int idUserAccount) {
		this.idUserAccount = idUserAccount;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMarriageStatus() {
		return this.marriageStatus;
	}

	public void setMarriageStatus(String marriageStatus) {
		this.marriageStatus = marriageStatus;
	}

	public int getNumberOfChildren() {
		return this.numberOfChildren;
	}

	public void setNumberOfChildren(int numberOfChildren) {
		this.numberOfChildren = numberOfChildren;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte getValid() {
		return this.valid;
	}

	public void setValid(byte valid) {
		this.valid = valid;
	}

	public List<BankAccount> getBankAccounts() {
		return this.bankAccounts;
	}

	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public BankAccount addBankAccount(BankAccount bankAccount) {
		getBankAccounts().add(bankAccount);
		bankAccount.setUserAccount(this);

		return bankAccount;
	}

	public BankAccount removeBankAccount(BankAccount bankAccount) {
		getBankAccounts().remove(bankAccount);
		bankAccount.setUserAccount(null);

		return bankAccount;
	}

	public List<Document> getDocuments() {
		return this.documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public Document addDocument(Document document) {
		getDocuments().add(document);
		document.setUserAccount(this);

		return document;
	}

	public Document removeDocument(Document document) {
		getDocuments().remove(document);
		document.setUserAccount(null);

		return document;
	}

	public List<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Notification addNotification(Notification notification) {
		getNotifications().add(notification);
		notification.setUserAccount(this);

		return notification;
	}

	public Notification removeNotification(Notification notification) {
		getNotifications().remove(notification);
		notification.setUserAccount(null);

		return notification;
	}

	public List<Request> getRequestsFrom() {
		return this.requestsFrom;
	}

	public void setRequestsFrom(List<Request> requestsFrom) {
		this.requestsFrom = requestsFrom;
	}

	public Request addRequestsFrom(Request requestsFrom) {
		getRequestsFrom().add(requestsFrom);
		requestsFrom.setUserAccountFrom(this);

		return requestsFrom;
	}

	public Request removeRequestsFrom(Request requestsFrom) {
		getRequestsFrom().remove(requestsFrom);
		requestsFrom.setUserAccountFrom(null);

		return requestsFrom;
	}

	public List<Request> getRequestsTo() {
		return this.requestsTo;
	}

	public void setRequestsTo(List<Request> requestsTo) {
		this.requestsTo = requestsTo;
	}

	public Request addRequestsTo(Request requestsTo) {
		getRequestsTo().add(requestsTo);
		requestsTo.setUserAccountTo(this);

		return requestsTo;
	}

	public Request removeRequestsTo(Request requestsTo) {
		getRequestsTo().remove(requestsTo);
		requestsTo.setUserAccountTo(null);

		return requestsTo;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}