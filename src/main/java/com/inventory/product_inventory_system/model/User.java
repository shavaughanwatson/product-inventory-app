package com.inventory.product_inventory_system.model;

public class User {
	private String username;
	private String password;
	private boolean isLoggedIn;
	
	
	public User (String username, String password) {
		this.username = username;
		this.password = password;
		this.isLoggedIn = false;
	}
	
	public String getUserName () {
		return this.username;
	}
	
	public String getUserPassword() {
		return this.password;
	}
	
	public void setUserName (String username) {
		 this.username = username;
	}
	
	public void setUserPassword(String password) {
		 this.password = password;
	}
	
	public void setisLoggedIn(boolean isLoggedIn) {
		 this.isLoggedIn = isLoggedIn;
	}
	
	public boolean getIsLoggedIn() {
		return this.isLoggedIn;
	}
	
	public String toString() {
		String UserInfo = "Username: " + username + "Password: " + password;
		return UserInfo;
	}

}
