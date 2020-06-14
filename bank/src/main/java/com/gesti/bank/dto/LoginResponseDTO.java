package com.gesti.bank.dto;

public class LoginResponseDTO {
	private int idUserAccount;
	private String roleName;
	
	public LoginResponseDTO(int idUserAccount, String roleName) {
		super();
		this.idUserAccount = idUserAccount;
		this.roleName = roleName;
	}
	public int getIdUserAccount() {
		return idUserAccount;
	}
	public void setIdUserAccount(int idUserAccount) {
		this.idUserAccount = idUserAccount;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	

}
