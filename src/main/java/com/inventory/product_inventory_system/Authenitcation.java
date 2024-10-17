package com.inventory.product_inventory_system;

import java.util.Scanner;

public class Authenitcation {
	private Scanner scanner;
	private String username;
	private String password;
	
	public void SignUp() {
		scanner = new Scanner(System.in);
		System.out.println("Enter username");
		username = scanner.nextLine();
		
		System.out.println("Enter password");
		password = scanner.nextLine();
		User user = new User(username, password);
		
		user.setisLoggedIn(true);
		
	}
	
	public void Login() {
		
	}

}
