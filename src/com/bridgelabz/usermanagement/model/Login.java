package com.bridgelabz.usermanagement.model;

/**
 * @Author : Vishal Yadav
 * @Date : 16/01/2020
 * @Version : 1.0
 */
public class Login {
	private String email;
	private String password;
	private String userType;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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

	@Override
	public String toString() {
		return "Login [email=" + email + ", password=" + password + "]";
	}
}
