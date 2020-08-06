package com.gesti.bank.dto;

public class PasswordChangeRequestDTO {
	
		private int loggedInUserId;
		private String oldPassword;
		private String newPassword;
		public int getLoggedInUserId() {
			return loggedInUserId;
		}
		public void setLoggedInUserId(int loggedInUserId) {
			this.loggedInUserId = loggedInUserId;
		}
		public String getOldPassword() {
			return oldPassword;
		}
		public void setOldPassword(String oldPassword) {
			this.oldPassword = oldPassword;
		}
		public String getNewPassword() {
			return newPassword;
		}
		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}
		

}
