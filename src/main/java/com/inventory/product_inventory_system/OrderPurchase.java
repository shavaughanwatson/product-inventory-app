package com.inventory.product_inventory_system;

import java.util.Scanner;

public class OrderPurchase {
     private static Scanner scanner;
     
	public static void  purchaseProduct() {
		
		int userInput;
		
		do { 
		//get access to product in inventory array
		System.out.println("Choose which item to purchase");
		System.out.println(InventoryOperations.getProductList());
		scanner = new Scanner(System.in);
		
  
        int indexInput = scanner.nextInt();//user inputs index to select product
        
        System.out.println("How many items you would like to purchase");
        Product purchasedProduct = InventoryOperations.getProductList().get(indexInput); 
    	System.out.println(purchasedProduct);
    	
    	int userPurchaseInput = scanner.nextInt();//user inputs quantity taken from product
    	
    	if (userPurchaseInput > purchasedProduct.getQuantity()) {
    		System.out.println("Quanity is invalid ");
    	} else {
    		purchasedProduct.setQuantity(purchasedProduct.getQuantity() - userPurchaseInput );
        	System.out.println("You have selected " + userPurchaseInput + " " + purchasedProduct.getName());
        	System.out.println("Here is the current stock of product: " +  purchasedProduct);
    	}
    	
		System.out.println("Press one to select a another product or press another key to exit");
		userInput = scanner.nextInt();
		} while (userInput == 1);{
			
		}
    	
            
		
	}
}
