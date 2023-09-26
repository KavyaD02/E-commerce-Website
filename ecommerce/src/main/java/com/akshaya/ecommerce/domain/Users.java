package com.akshaya.ecommerce.domain;

public class Users {
	
	private String username;
	private String password;
	private Object role;
	
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
	public Object getRole() {
		return role;
	}
	public void setRole(Object role) {
		this.role = role;
	}

}
