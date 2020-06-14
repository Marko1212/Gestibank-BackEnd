package com.gesti.bank.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the documents database table.
 * 
 */
@Entity
@Table(name="documents")
@NamedQuery(name="Document.findAll", query="SELECT d FROM Document d")
public class Document implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_documents")
	private int idDocuments;

	private String description;

	private String path;

	//bi-directional many-to-one association to UserAccount
	@ManyToOne
	@JoinColumn(name="user_account_id_user_account")
	private UserAccount userAccount;

	public Document() {
	}
	
	

	public Document(String description, String path, UserAccount userAccount) {
		super();
		this.description = description;
		this.path = path;
		this.userAccount = userAccount;
	}



	public int getIdDocuments() {
		return this.idDocuments;
	}

	public void setIdDocuments(int idDocuments) {
		this.idDocuments = idDocuments;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}