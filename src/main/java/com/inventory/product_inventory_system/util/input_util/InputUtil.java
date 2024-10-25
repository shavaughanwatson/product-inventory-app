package com.inventory.product_inventory_system.util.input_util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InputUtil {
	public static Scanner scanner = new Scanner(System.in);
	private static String inputName;
	private static int inputQuantity;
	private static double inputPrice;
	private static String inputQuery;
	private static int userInput;
	private static String dateInput;
	private static int categoryInput;
	
	
	public static String getInputName(String prompt) {
		 System.out.print(prompt);
		inputName = scanner.nextLine(); 
		return inputName;
	}
	
	public static void setInputName(String inputName) {
		InputUtil.inputName = inputName;
	}
	
	
	public static int getInputQuantity( String prompt) {
		System.out.println(prompt);
		inputQuantity = scanner.nextInt();
		return inputQuantity;
	}

	public static double getInputPrice(String prompt) {
		 System.out.print(prompt);
		 inputPrice = scanner.nextDouble();
		return inputPrice;
	}
	
	public static void setInputPrice(double inputPrice) {
		InputUtil.inputPrice = inputPrice;
	}


	public static String getCategoryInput (String prompt) {
		System.out.println(prompt);
		userInput = scanner.nextInt();
		String[] categories = {"FOOD" , "MERCHANDISE"}; 
		return categories[categoryInput];
	}
	

	public static void setCategoryInput(int categoryInput) {
		InputUtil.categoryInput = categoryInput;
	}

	public static LocalDate getDateInput(String prompt) {
		System.out.println(prompt);
		scanner.nextLine();
	    dateInput = scanner.nextLine();  
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    LocalDate expirationDate = LocalDate.parse(dateInput, formatter); 
		return expirationDate;
	}

	public static void setDateInput(String dateInput) {
		InputUtil.dateInput = dateInput;
	}

	public static int getUserInput() {
		userInput = scanner.nextInt();
		return userInput;
	}
	
	public static int getUserInput(String prompt) {
		System.out.println(prompt);
		userInput = scanner.nextInt();
		return userInput;
	}

	public static void setUserInput(int userInput) {
		InputUtil.userInput = userInput;
	}

	public static int getProductIndex(String prompt) {
		System.out.println(prompt);
		userInput = scanner.nextInt();
		return userInput;
	}


	public static void setInputQuantity(int inputQuantity) {
		InputUtil.inputQuantity = inputQuantity;
	}


	public static String getInputQuery() {
		return inputQuery;
	}

	public static void setInputQuery(String inputQuery) {
		InputUtil.inputQuery = inputQuery;
	}
}
