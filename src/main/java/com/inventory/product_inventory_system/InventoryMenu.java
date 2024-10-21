package com.inventory.product_inventory_system;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InventoryMenu {
	private static int userInput;
	private static Scanner scanner;
	
 public static void showInventoryMenu  (User user) {
	     scanner = new Scanner(System.in);
	 
		do {
		
			
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
					System.out.println("7 = sign up");

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
						do {
							try { 
								System.out.println("Which report you would like to print out" + "\n" + "Press 1 for general report" +" Press 2 for low stock");
								userInput = scanner.nextInt();
								if(userInput == 1) {
								try {
									PrintingReports.printingGeneralReports();
								} catch (NoProductsInInventoryException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								}
								
								if(userInput == 2) {
									try {
										PrintingReports.printingLowStockReports();
									} catch (NoProductsInInventoryException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							} catch (InputMismatchException e) {
								System.out.println("You must enter whole numbers only. No decimals, words, or other info. Try again."
										+ "\n" + "Press 1 to product select a another product; or press another key to exit" + "\n"
										+ "Input below:\n" + "-------------------------------");
							}
						} while (userInput == 6);
			
						
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
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								
					
					} else if (userInput == 0) {
						
						user.setisLoggedIn(false);
						
						
					} else {
						System.out.println("Invalid choose another option");
					}
					
					

		} while (user.getIsLoggedIn() == true); 
		
		
 }
}
