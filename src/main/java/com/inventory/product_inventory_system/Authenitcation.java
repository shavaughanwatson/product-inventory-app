package com.inventory.product_inventory_system;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Authenitcation {
	private static  Map<Integer, User> users = new LinkedHashMap<>(); //need fake database to store users
	private static Scanner scanner;
	private static String username;
	private static String password;
	private static int id = 0; //id should be in user class
	
	public static void SignUp() {
		//create new user
		scanner = new Scanner(System.in);
		System.out.println("Enter username");
		username = scanner.nextLine(); //will have to create custom exception if user already exist
		
		System.out.println("Enter password");
		password = scanner.nextLine();
		
		
		User user = new User(username, password); //create user object with inputs
		System.out.println(user);
		//add create new file to show user is registered
		
		users.put(id++, user);
		System.out.println(users);
		user.setisLoggedIn(true);	
	}
	
	public static Map<Integer, User> getUsers(){
		return users;
		
	}
	
	public static void Logout() {
		//set userLoggin(false)
		
	}
	
	
	public static void Login() {
		//initailze scanner
		///in while (loop)
		//input username and password
		
		// show exception error if custom user or password doesn't exist
		//look into the array database to see if array contains user OR password
		
	}

}
