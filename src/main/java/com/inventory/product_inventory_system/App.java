package com.inventory.product_inventory_system;

import java.util.Scanner;

public class App
{
    public static void main( String[] args ){
	    
    	
		Scanner scanner = new Scanner(System.in); // have to be within the scope
		int userInput;
		System.out.println("Welcome to the Inventory Product Management System!");
	
		do {

			System.out.println("Homepage!");
			
			if (InventoryOperations.getProductList().size() == 0) {
				System.out.println("No products have been imported yet");
			} else {
				System.out.println(InventoryOperations.getProductList());
			}
		
			System.out.println("Select you main menu!");
			System.out.println("1 = add product");
			System.out.println("2 = remove product");
			System.out.println("3 = get product");
			System.out.println("4 = edit product");
			System.out.println("5 = purchase products");
			System.out.println("6 = generate report");

			userInput = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character

			if (userInput == 1) {
				 InventoryOperations.addProduct();

			} else if (userInput == 2) {
				 InventoryOperations.removeProduct();

			} else if (userInput == 3) {
				InventoryOperations.getProduct();
		
			} else if (userInput == 4) {
				InventoryOperations.editProduct();
			} else if (userInput == 5) {
				OrderPurchase.purchaseProduct();

			} else if (userInput == 6) {
				PrintingReports.printingGeneralReports();
				//Report
				/*
				for(Product product: productList.values()) {
					System.out.println("Product Name | Quantity | SKU |\n"+product.getName() + "|" + product.getQuantity() + "|" + product.getSKU() + "|");
				}
			
				System.out.println(productList.values());
				*/
			} else  {
				System.out.println("choose another option");
			}

		} while (userInput != 6);

		scanner.close();
    }
}
