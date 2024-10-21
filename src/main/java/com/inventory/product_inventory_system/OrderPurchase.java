package com.inventory.product_inventory_system;

import java.util.Map;
import java.util.Scanner;

public class OrderPurchase {
	
	//all this will be it's own class
     private static Scanner scanner;
     private static int userInput;
     private static	int indexInput;
     private static	int userPurchaseInput; 
     
     private static	double purchasePrice;
     private static	Product purchasedProduct;
     
		
     
	public static void  purchaseProduct() throws NoProductsInInventoryException,  ProductNotFoundException {
		
		do { 
		//get access to product in inventory array
		System.out.println("------------------" +"\n" + "Product Purchases" +"\n" + "------------------"  + "\n" + "Select the product ID to make a purchase");
		String table = String.format("%-10s %-15s %-10s %-10s \n", "ID", "Product Name", "Price", "Quantity"  ) +
                "------------------------------------------------------------------\n";
		
		for (Map.Entry<Integer, Product> entry: InventoryOperations.getProductList().entrySet()) {
			
			table += String.format("%-10s %-15s %-10s %-10s \n", entry.getValue().getSKU(), entry.getValue().getName(), entry.getValue().getPrice(), entry.getValue().getQuantity());
			
		}
		
		
		System.out.println(table);
		scanner = new Scanner(System.in);
		
       //user inputs index to select product
		System.out.println("------------------" +"\n" + "Input Product ID below:");
		indexInput = scanner.nextInt();
		
        
        //see product selected
         purchasedProduct = InventoryOperations.getProductList().get(indexInput); 
    	System.out.println("Product Details" +"\n" + "------------------" + "\n" +"Product Name: "+ purchasedProduct.getName() + "\n" + "Price: " + purchasedProduct.getPrice() + "\n" + "Available Quantity: " + purchasedProduct.getQuantity() );
    	System.out.println("How many items you would like to purchase ?" + "\n" + "------------------" +"\n" + "Input Product Quantity below:");
    	
    	//user inputs quantity taken from product
    	userPurchaseInput = scanner.nextInt(); // make error exception with  input
    	
    	if (userPurchaseInput > purchasedProduct.getQuantity()) {
    		System.out.println("Quanity is invalid "); // error exception needs to be made
    	} else {
    		purchasedProduct.setQuantity(purchasedProduct.getQuantity() - userPurchaseInput );
    		purchasePrice = userPurchaseInput * purchasedProduct.getPrice();
    		
    		purchasedProduct.setSold(purchasedProduct.getSold() + userPurchaseInput);
    		
    		purchasedProduct.setRevenue(purchasedProduct.getRevenue() + purchasePrice);
    		
    		System.out.println("Purchase successful" +"\n" + "------------------" + "\n"+ "Updated Inventory" +"\n" +"Product Name: "+ purchasedProduct.getName() + "\n" + "Price: " + purchasedProduct.getPrice() + "\n" + "Available Quantity: " + purchasedProduct.getQuantity() );
    	
    	}
    	
		System.out.println("Press 1 to select a another product or press another key to exit");
		userInput = scanner.nextInt();
		} while (userInput == 1);{ //&& product inventory list ==0
			
		}
    	
            
		
	}
}
