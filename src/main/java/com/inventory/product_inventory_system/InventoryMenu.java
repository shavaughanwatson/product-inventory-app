package com.inventory.product_inventory_system;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InventoryMenu {
	private static int userInput;
	private static Scanner scanner;

	public static void showInventoryMenu  (User user) {
	  
	 
		do {
		try { 
			 
			scanner = new Scanner(System.in);
			System.out.println("Welcome to the Inventory Product Management System!");
			if (InventoryOperations.getProductList().size() == 0) {
				System.out.println( "------------------------------------------------------------------\n"+"No products have been imported yet\n" +  "------------------------------------------------------------------\n");
			} else {
				System.out.println(PrintingReports.printCurrentInventory());
			}
					System.out.println("Select you main menu!");
					System.out.println("1 = add product");
					System.out.println("2 = remove product");
					System.out.println("3 = get product");
					System.out.println("4 = edit product");
					System.out.println("5 = purchase products");
					System.out.println("6 = generate report");
					
					//search functionality might to be interfaces
					System.out.println("7 = search product by name");
					System.out.println("8 = search product by SKU");
					System.out.println("9 = search product by Category");
					System.out.println("0 = Logout");

					userInput = scanner.nextInt();
					scanner.nextLine(); // Consume the newline character

					if (userInput == 1) {
						InventoryOperations.addProduct();

					} else if (userInput == 2) {
						
						try {
							InventoryOperations.removeProduct();
						} catch (NoProductsInInventoryException | ProductNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (userInput == 3) {
						try {
							InventoryOperations.getProduct();
						} catch (NoProductsInInventoryException | ProductNotFoundException e) {
							
							e.printStackTrace();
						}

					} else if (userInput == 4) {
						try {
							InventoryOperations.editProduct();
						} catch (NoProductsInInventoryException | ProductNotFoundException e) {
						
							e.printStackTrace();
						}
					} else if (userInput == 5) {
						try {
							OrderPurchase.purchaseProduct();
						} catch (NoProductsInInventoryException | ProductNotFoundException e ) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						
					} else if (userInput == 6) {
						
						 
								try {
									PrintingReports.printingGeneralReports();
								} catch (NoProductsInInventoryException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			
			
						
					} else if (userInput == 7) {
						try {
							InventoryOperations.SearchProducts();
						} catch (NoProductsInInventoryException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else if (userInput == 8) {
						try {
							InventoryOperations.SearchSKU();
						} catch (NoProductsInInventoryException|ProductNotFoundException e) {
							
							e.printStackTrace();
						}
						
					} else if (userInput == 9) {
						try {
							InventoryOperations.SearchByCategorieProducts();
						} catch (NoProductsInInventoryException e) {
							
							e.printStackTrace();
						}
							
								
					
					} else if (userInput == 0) {
						
						
						Authenitcation.Logout(user); //??
						
						
					} else {
						System.out.println("Invalid choose another option");
					}
			
			
			
		} catch (InputMismatchException e) {
			System.out.println("You must enter whole numbers only. No decimals, words, or other info. Try again."
					+ "\n" + "Press any number to go back to the main menu" + "\n"
					+ "Input below:\n" + "-------------------------------");
			scanner.nextLine();
			userInput = scanner.nextInt();
			
		}
		
					

		} while (user.getIsLoggedIn() == true); 
	  
	    
	}
	
}
					

