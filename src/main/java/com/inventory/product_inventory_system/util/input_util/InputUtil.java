package com.inventory.product_inventory_system.util.input_util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.inventory.product_inventory_system.model.Product;
import com.inventory.product_inventory_system.util.error_handling.InvalidProductQuantity;
import com.inventory.product_inventory_system.util.error_handling.ProductNotFoundException;
import com.inventory.product_inventory_system.util.print_util.PrintingUtil;
import com.inventory.product_inventory_system.util.stock_management.ListUtil;

public class InputUtil {
	public static Scanner scanner = new Scanner(System.in);
	private static String dateInput;
	private static int categoryInput;

	private static int userInput;

	public static <T> T getUserInput(String prompt, Class<T> type) {
	    System.out.print(prompt);

	    if (type == Integer.class) {
	        if (scanner.hasNextInt()) {
	            return type.cast(scanner.nextInt());
	        } else {
	            throw new NoSuchElementException("Expected an integer input but none was found.");
	        }
	    } else if (type == String.class) {
	        if (scanner.hasNextLine()) {
	            return type.cast(scanner.nextLine());
	        } else {
	            throw new NoSuchElementException("Expected a string input but none was found.");
	        }
	    } else if (type == Double.class) {
	        if (scanner.hasNextDouble()) {
	            return type.cast(scanner.nextDouble());
	        } else {
	            throw new NoSuchElementException("Expected a double input but none was found.");
	        }
	    }
	    return null;
	}

	// Interfaces
	public static String getCategoryInput(String prompt) {
		System.out.println(prompt);
		categoryInput = scanner.nextInt();
		String[] categories = { "FOOD", "MERCHANDISE" };
		return categories[categoryInput];
	}

	public static LocalDate getDateInput(String prompt) {
		System.out.println(prompt);
		scanner.nextLine();
		dateInput = scanner.nextLine();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate expirationDate = LocalDate.parse(dateInput, formatter);
		return expirationDate;
	}

	public static int getUserInput(String prompt) {
		userInput = scanner.nextInt();
		return userInput;
	}

	public static int getUserInput() {
		return userInput;
	}

	public static int selectedProductSKU() throws ProductNotFoundException {
		
		System.out.println(PrintingUtil.printCurrentInventory() + "\nSelect Product SKU: \nInput Product ID below:");		
		int selectedSKU = scanner.nextInt();
		if (!ListUtil.getProductList().containsKey(selectedSKU)) {
			throw new ProductNotFoundException("No Products found in inventory");
		}
		return selectedSKU;
	}

	public static void inputNewProductQuantity(int selectedProduct) {

		PrintingUtil.displayProductMenuOptions(selectedProduct);
		userInput = InputUtil.scanner.nextInt();
		while (userInput == 2) {
			int addedQuantityInput;
			addedQuantityInput = InputUtil.getUserInput("How much would you like to add?", Integer.class);
			int updatedQuantity = ListUtil.getProductList().get(selectedProduct).getQuantity() + addedQuantityInput;
			ListUtil.getProductList().get(selectedProduct).setQuantity(updatedQuantity);

			PrintingUtil.confirmNewProduct("New Quantity has been added", selectedProduct, addedQuantityInput);
			PrintingUtil.displayExitOptions();
			userInput = InputUtil.scanner.nextInt();
		}
		

	}

	public static void inputNewProductInfo(int selectedProductSKU) {
	
		String inputName;
		double inputPrice;
		PrintingUtil.displayProductMenuOptions(selectedProductSKU);
		
		
				System.out.println(ListUtil.getProductList().get(selectedProductSKU));
				System.out.println("Edit Product Name");
				System.out.println("------------------" + "\n" + "Input Product Name below:");
				inputName = InputUtil.scanner.nextLine();

				ListUtil.getProductList().get(selectedProductSKU).setName(inputName); // get object value and can performed
				// methods on it

				System.out.println("Edit Product Price");
				System.out.println("------------------" + "\n" + "Input Product Price below:");

				inputPrice = InputUtil.scanner.nextDouble();
				ListUtil.getProductList().get(selectedProductSKU).setPrice(inputPrice);

				PrintingUtil.confirmNewProduct("Product Info has been updated!", selectedProductSKU);
				
				
			
	
		
		}

	

	public static void inputPurchasedProduct(Product product)  throws InvalidProductQuantity {
		System.out.println("Product Details" + "\n" + "------------------" + "\n" + "Product Name: " + product.getName()
				+ "\n" + "Price: " + product.getPrice() + "\n" + "Available Quantity: " + product.getQuantity());
		System.out.println("How many items you would like to purchase ?" + "\n" + "------------------" + "\n"
				+ "Input Product Quantity below:");

		// user inputs quantity taken from product
		userInput = scanner.nextInt(); // make error exception with input

		if (userInput > product.getQuantity()) {
			throw new InvalidProductQuantity("Quanity is invalid "); // error exception needs to be made
		}
		
			product.setQuantity(product.getQuantity() - userInput);
			double purchasePrice = userInput * product.getPrice();
			System.out.println(purchasePrice);
			product.setSold(product.getSold() + userInput);
			System.out.println(product.getPrice());
			product.setRevenue(product.getRevenue() + purchasePrice);
			System.out.println(product.getRevenue());
			System.out.println("Purchase successful" + "\n" + "------------------" + "\n" + "Updated Inventory" + "\n"
					+ "Product Name: " + product.getName() + "\n" + "Price: " + product.getPrice() + "\n"
					+ "Available Quantity: " + product.getQuantity());

			System.out.println("Press 1 to select a another product or press another key to exit");
			userInput = scanner.nextInt();
		}

		
	}


