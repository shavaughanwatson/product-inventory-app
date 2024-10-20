package com.inventory.product_inventory_system;

import java.util.Scanner;

public class OrderPurchase {
     private static Scanner scanner;
     private static int userInput;
     private static	int indexInput;
     private static	double purchasePrice;
     private static	Product purchasedProduct;
     private static	int userPurchaseInput; 
		
     
	public static void  purchaseProduct() {
		
		do { 
		//get access to product in inventory array
		System.out.println("Choose which item to purchase");
		
		System.out.println(InventoryOperations.getProductList());// make exception if there's no product in list
		scanner = new Scanner(System.in);
		
  
       //user inputs index to select product
		indexInput = scanner.nextInt();
		
		
        System.out.println("How many items you would like to purchase");
        
        //see product selected
         purchasedProduct = InventoryOperations.getProductList().get(indexInput); 
    	System.out.println(purchasedProduct);
    	
    	//user inputs quantity taken from product
    	userPurchaseInput = scanner.nextInt(); // make error exception with  input
    	
    	if (userPurchaseInput > purchasedProduct.getQuantity()) {
    		System.out.println("Quanity is invalid "); // error exception needs to be made
    	} else {
    		purchasedProduct.setQuantity(purchasedProduct.getQuantity() - userPurchaseInput );
    		purchasePrice = userPurchaseInput * purchasedProduct.getPrice();
    		
    	
    		purchasedProduct.setSold(purchasedProduct.getSold() + userPurchaseInput);
    		
    		purchasedProduct.setRevenue(purchasedProduct.getRevenue() + purchasePrice);
    		
    		System.out.println("You have purchasesd " + userPurchaseInput + " "+  purchasedProduct.getName() + " worth " +  purchasePrice + " Here is the current stock of product:" +  purchasedProduct);
    		PrintingReports.printingGeneralReports("order-purchase.txt", "You have purchasesd " + userPurchaseInput + "  " + purchasedProduct.getName() + "worth" +  purchasePrice + "Here is the current stock of product: " +  purchasedProduct);
        	System.out.println();
        	System.out.println("Here is the current stock of product: " +  purchasedProduct);
    	}
    	
		System.out.println("Press one to select a another product or press another key to exit");
		userInput = scanner.nextInt();
		} while (userInput == 1);{ //&& product inventory list ==0
			
		}
    	
            
		
	}
}
