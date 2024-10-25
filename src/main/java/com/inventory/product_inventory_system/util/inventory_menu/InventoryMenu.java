package com.inventory.product_inventory_system.util.inventory_menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.inventory.product_inventory_system.authentication.Authenitcation;
import com.inventory.product_inventory_system.model.User;
import com.inventory.product_inventory_system.service.InventoryOperations;
import com.inventory.product_inventory_system.service.OrderPurchase;
import com.inventory.product_inventory_system.service.PrintingReports;
import com.inventory.product_inventory_system.util.error_handling.NoProductsInInventoryException;
import com.inventory.product_inventory_system.util.error_handling.ProductNotFoundException;
import com.inventory.product_inventory_system.util.input_util.InputUtil;
import com.inventory.product_inventory_system.util.stock_management.ListUtil;

public class InventoryMenu {
	private static int userInput;
	

	public static void showInventoryMenu  (User user) {
	  
	 
		do {
		try { 
			 
			System.out.println("Welcome to the Inventory Product Management System!");
			if (ListUtil.getProductList().size() == 0) {
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

					userInput = InputUtil.scanner.nextInt();
					InputUtil.scanner.nextLine(); // Consume the newline character

					if (userInput == 1) {
						InventoryOperations.addProduct(InputUtil.scanner);

					} else if (userInput == 2) {
						
						try {
							InventoryOperations.removeProduct(InputUtil.scanner);
						} catch (NoProductsInInventoryException | ProductNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} else if (userInput == 3) {
						try {
							InventoryOperations.getProduct(InputUtil.scanner);
						} catch (NoProductsInInventoryException | ProductNotFoundException e) {
							
							e.printStackTrace();
						}

					} else if (userInput == 4) {
						try {
							InventoryOperations.editProduct(InputUtil.scanner);
						} catch (NoProductsInInventoryException | ProductNotFoundException e) {
						
							e.printStackTrace();
						}
					} else if (userInput == 5) {
						try {
							OrderPurchase.purchaseProduct(InputUtil.scanner);
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
							InventoryOperations.SearchProducts(InputUtil.scanner);
						} catch (NoProductsInInventoryException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} else if (userInput == 8) {
						try {
							InventoryOperations.SearchSKU(InputUtil.scanner);
						} catch (NoProductsInInventoryException|ProductNotFoundException e) {
							
							e.printStackTrace();
						}
						
					} else if (userInput == 9) {
						try {
							InventoryOperations.SearchByCategorieProducts(InputUtil.scanner);
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
			InputUtil.scanner.nextLine();
			userInput = InputUtil.scanner.nextInt();
			
		}
					

		} while (user.getIsLoggedIn() == true); 
	  
	    
	}
	
}

/*
  IDEA FOR REFACTORING 
   private int getProductIndex() {
        System.out.println("Input Product ID below:");
        return scanner.nextInt(); // Consider adding error handling here
    } //
    
    
    public class OrderPurchase {
	
    private Scanner scanner; // Pass this in the constructor
    private Product purchasedProduct;

    public OrderPurchase(Scanner scanner) {
        this.scanner = scanner;
    }
    
        public int getIntInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();  // Using the injected scanner
    }
    
 
 */
					

