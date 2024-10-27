package com.inventory.product_inventory_system.authentication;

import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import com.inventory.product_inventory_system.model.User;
import com.inventory.product_inventory_system.service.PrintingReports;
import com.inventory.product_inventory_system.util.error_handling.InvalidLoginException;
import com.inventory.product_inventory_system.util.input_util.InputUtil;
import com.inventory.product_inventory_system.util.inventory_menu.InventoryMenu;
import com.inventory.product_inventory_system.util.print_util.PrintingUtil;

public class Authenitcation {

	private static String username;
	private static String password;

	public static void SignUp() {

		username = InputUtil.getUserInput("Enter username", String.class);
		password = InputUtil.getUserInput("Enter password", String.class);

		User user = new User(username, password); // create user object with inputs
		System.out.println(user);
		user.setisLoggedIn(true);
		PrintingUtil.printingUserRegistered(user);
		InventoryMenu.showInventoryMenu(user);
	}

	public static void Logout(User user) {
		user.setisLoggedIn(false);
	}

	public static void Login() throws InvalidLoginException {

		String filePath = "user-registration.txt";
		try {
			List<String> lines = Files.readAllLines(Paths.get(filePath));
			String storedUsername = null;
			String storedPassword = null;
			for (String line : lines) {
				line = line.trim();

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

			username = InputUtil.getUserInput("Enter username", String.class);
			password = InputUtil.getUserInput("Enter password", String.class);

			// text file is read looking for username and password
			if (storedUsername.equals(username) && storedPassword.equals(password)) {
				User user = new User(username, password);
				user.setisLoggedIn(true);
				System.out.println("Login successful!");
				InventoryMenu.showInventoryMenu(user); 

			} else {
				throw new InvalidLoginException("Invalid Username/Password");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}