package com.inventory.product_inventory_system;

import java.util.LinkedHashMap;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class InventoryOperations {
	private static Map<Integer, Product> productList = new LinkedHashMap<>();
	private static Scanner scanner; 
	private static int id = 0;
	private static String inputName;
	private static int inputQuantity;
	
	
	public static void addProduct () {
		
		try {
			
			//initialize scanner inputting product name and quantity
			scanner = new Scanner(System.in);
			System.out.println("Enter name of product");
			inputName = scanner.nextLine();
			
			System.out.println("Enter quantity");
			inputQuantity = scanner.nextInt();
			
			
			Product product = new Product(inputName, inputQuantity);
			//RegisterProduct
			FileWriter myWriter = new FileWriter("productRegistered.txt");
			 String productInfo = product.toString();
			 myWriter.write(productInfo);
			 myWriter.close();
			
			
			getProductList().put(id++, product);
			System.out.println(productList);
			//scanner.close();
			

		      System.out.println("Successfully wrote to the file.");
		      
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 System.out.println("An error occurred.");
			e.printStackTrace();
		}
		

	}
	
	public static void removeProduct( ) {
		
		System.out.println("remove a product from list");
		System.out.println(getProductList());
		//initialize scanner inputting product name and quantity
		scanner = new Scanner(System.in);

		int productIndex = scanner.nextInt();
		getProductList().remove(productIndex);

		System.out.println(productList);
		
	}


	public static void getProduct() {
		
		int userInput;
		
		do  { 
			System.out.println("get product");
			System.out.println(productList);
			scanner = new Scanner(System.in);
			
			int indexInput = scanner.nextInt();
			System.out.println(productList.get(indexInput));
			
			System.out.println("Press one to select a another product or press another key to exit");
			userInput = scanner.nextInt();
		} while (userInput == 1);
		
	}
	

	
	
	public static void editProduct() {
		
		int userInput;
	
		int indexInput;
		
		do  { 
			System.out.println("Select a product you would like to edit");
			System.out.println(productList);
			scanner = new Scanner(System.in);
			indexInput = scanner.nextInt();
			
			scanner.nextLine();
			System.out.println(productList.get(indexInput));
			System.out.println("Edit Product Name");
			inputName = scanner.nextLine();
			
			productList.get(indexInput).setName(inputName); // get object value and can performed methods on it
			
			System.out.println("Edit Product Quantity");
			inputQuantity = scanner.nextInt();
			productList.get(indexInput).setQuantity(inputQuantity);
			System.out.println(productList);
			System.out.println("Press one to select a another product or press another key to exit");
			userInput = scanner.nextInt();
		} while (userInput == 1);
		
	}

	public static Map<Integer, Product> getProductList() {
		return productList;
	}



	public static Scanner getScanner() {
		return scanner; 
	}

	
	
	
}
