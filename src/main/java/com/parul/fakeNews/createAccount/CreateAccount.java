// pojo class
package com.parul.fakeNews.createAccount;

public class CreateAccount {
	private String userName;
	private String email;
	private String password;
	private String re_Password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRe_Password() {
		return re_Password;
	}

	public void setRe_Password(String re_Password) {
		this.re_Password = re_Password;
	}

	@Override
	public String toString() {
		return "CreateAccount [userName=" + userName + ", email=" + email + ", password=" + password + ", re_Password="
				+ re_Password + "]";
	}

}
