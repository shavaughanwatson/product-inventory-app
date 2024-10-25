package com.inventory.product_inventory_system.service;

import java.util.Map;
import java.util.Scanner;

import com.inventory.product_inventory_system.model.Product;
import com.inventory.product_inventory_system.util.error_handling.NoProductsInInventoryException;
import com.inventory.product_inventory_system.util.error_handling.ProductNotFoundException;
import com.inventory.product_inventory_system.util.stock_management.ListUtil;

public class OrderPurchase {
	
	//all this will be it's own class
   
     private static int userInput;
     private static	int indexInput;
     private static	int userPurchaseInput; 
     
     private static	double purchasePrice;
     private static	Product purchasedProduct;
     
		
     
	public static void  purchaseProduct(Scanner scanner) throws NoProductsInInventoryException,  ProductNotFoundException {
		if( ListUtil.getProductList().size() == 0 ) {
			throw new NoProductsInInventoryException("No Products have been found." + "\n" + "Please insert new Products");
		}
		do { 
		//get access to product in inventory array
		System.out.println("------------------" +"\n" + "Product Purchases" +"\n" + "------------------"  + "\n" + "Select the product ID to make a purchase");
		String table = String.format("%-10s %-15s %-10s %-10s \n", "ID", "Product Name", "Price", "Quantity"  ) +
                "------------------------------------------------------------------\n";
		
		for (Map.Entry<Integer, Product> entry: ListUtil.getProductList().entrySet()) {
			
			table += String.format("%-10s %-15s %-10s %-10s \n", entry.getValue().getSKU(), entry.getValue().getName(), entry.getValue().getPrice(), entry.getValue().getQuantity());
			
		}
		
		
		System.out.println(table);

	
       //user inputs index to select product
		System.out.println("------------------" +"\n" + "Input Product ID below:");
		indexInput = scanner.nextInt();
		
        
        //see product selected
         purchasedProduct = ListUtil.getProductList().get(indexInput); 
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
