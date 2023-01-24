package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class InsertUserForm {

	@NotBlank(message = "Please enter your username")
	private String username;
	@NotBlank(message = "Please enter your password")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[.?/-])[a-zA-Z0-9.?/-]{8,16}$", message = "Password must be 8 to 16 characters including uppercase and lowercase letters and symbols (.?/-).")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "InsertUserForm [username=" + username + ", password=" + password + "]";
	}

}
