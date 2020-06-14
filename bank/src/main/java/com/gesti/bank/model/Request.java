package com.gesti.bank.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the request database table.
 * 
 */
@Entity
@NamedQuery(name="Request.findAll", query="SELECT r FROM Request r")
public class Request implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_request")
	private int idRequest;

	private String description;

	@Column(name="request_status")
	private byte requestStatus;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	private String title;

	//bi-directional many-to-one association to UserAccount
	@ManyToOne
	@JoinColumn(name="user_account_id_user_account_from")
	private UserAccount userAccountFrom;

	//bi-directional many-to-one association to UserAccount
	@ManyToOne
	@JoinColumn(name="user_account_id_user_account_to")
	private UserAccount userAccountTo;

	public Request() {
	}

	public int getIdRequest() {
		return this.idRequest;
	}

	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getRequestStatus() {
		return this.requestStatus;
	}

	public void setRequestStatus(byte requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UserAccount getUserAccountFrom() {
		return this.userAccountFrom;
	}

	public void setUserAccountFrom(UserAccount userAccountFrom) {
		this.userAccountFrom = userAccountFrom;
	}

	public UserAccount getUserAccountTo() {
		return this.userAccountTo;
	}

	public void setUserAccountTo(UserAccount userAccountTo) {
		this.userAccountTo = userAccountTo;
	}

}