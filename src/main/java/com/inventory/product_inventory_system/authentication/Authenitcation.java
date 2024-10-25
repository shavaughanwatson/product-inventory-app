package com.inventory.product_inventory_system.authentication;


import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import com.inventory.product_inventory_system.model.User;
import com.inventory.product_inventory_system.service.PrintingReports;
import com.inventory.product_inventory_system.util.error_handling.InvalidLoginException;
import com.inventory.product_inventory_system.util.inventory_menu.InventoryMenu;

public class Authenitcation {

	private static Scanner scanner;
	private static String username;
	private static String password;

	public static void SignUp() {
		// create new user
		scanner = new Scanner(System.in);

		System.out.println("Enter username");
		username = scanner.nextLine(); // will have to create custom exception if user already exist

		System.out.println("Enter password");
		password = scanner.nextLine();

		User user = new User(username, password); // create user object with inputs
		System.out.println(user);
		user.setisLoggedIn(true);
		PrintingReports.printingUserRegistered(user);
		InventoryMenu.showInventoryMenu(user);
	}

	public static void Logout(User user) {
		user.setisLoggedIn(false);
	}

	public static void Login() throws InvalidLoginException  {
		
	       String filePath = "user-registration.txt";
	        try {
	            List<String> lines = Files.readAllLines(Paths.get(filePath));
	            String storedUsername = null;
	            String storedPassword = null;
	            for (String line : lines) {
	                line = line.trim(); // Remove leading and trailing whitespaces

	                // Extract username
	                if (line.startsWith("Username:")) {
	                    storedUsername = line.replace("Username:", "").trim();
	                }

	                // Extract password
	                if (line.startsWith("Password:")) {
	                    storedPassword = line.replace("Password:", "").trim();
	                }

	                // Check if both username and password have been found
	                if (storedUsername != null && storedPassword != null) {
	                    break; // We have both, so we can stop looping
	                }
	            }
		
		scanner = new Scanner(System.in);
		//user inputs their username and password
		System.out.println("Enter username");
		username = scanner.nextLine(); //will have to create custom exception if user already exist
		
		System.out.println("Enter password");
		password = scanner.nextLine();
		
		//text file is read looking for username and password
   
            
            // Validate the input username and password
           
                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    User user = new User(username, password);
                    user.setisLoggedIn(true);
                    System.out.println("Login successful!");
                    InventoryMenu.showInventoryMenu(user); // Proceed to inventory menu
                    
             
                } else {
                    throw new InvalidLoginException("Invalid Username/Password");
                }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	
	}

}