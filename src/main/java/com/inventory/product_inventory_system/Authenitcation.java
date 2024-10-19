package com.inventory.product_inventory_system;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Authenitcation {
	private static  Map<Integer, User> users = new LinkedHashMap<>();
	private static Scanner scanner;
	private static String username;
	private static String password;
	private static int id = 0;
	
	public static void SignUp() {
		//create new user
		scanner = new Scanner(System.in);
		System.out.println("Enter username");
		username = scanner.nextLine();
		
		System.out.println("Enter password");
		password = scanner.nextLine();
		User user = new User(username, password);
		System.out.println(user);
		users.put(id++, user);
		System.out.println(users);
		
		user.setisLoggedIn(true);	
	}
	
	public static Map<Integer, User> getUsers(){
		return users;
		
	}
	
	public static void Logout() {
		
	}
	
	
	public static void Login() {
		
	}

}
