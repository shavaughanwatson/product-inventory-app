package com.inventory.product_inventory_system;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.util.Map;

public class PrintingReports { // might have to be an interface.
	
	//make method to show product registeration
	
	//make method to show user registered - txt file should make password be in hashcode.
	
	
	public static void printingGeneralReports() throws NoProductsInInventoryException {
		//make error custom exception if no product is in the array
		
		try {
				
			//repeated code - make into method or own class/interface
			FileWriter myWriter = new FileWriter("product-data.txt");
			 myWriter.write(PrintingReports.printCurrentInventory()); //make report more into a table with java string
			  myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		      
		} catch (IOException e) {
		
			 System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}
	
	public static void printingProductRegistered(Product product) {
		//make error custom exception if no product is in the array
		
		try {
			
			String productRegistration = "Product Registration \n" + "------------------" + "\n"+ "Product SKU: "+ product.getSKU() + "\n" + "Product Name: "+ product.getName() + "\n" + "Price: " + product.getPrice() + "\n" + "Quantity: " + product.getQuantity() +"\n" + "Product registered successfully!";
				
			//repeated code - make into method or own class/interface
			FileWriter myWriter = new FileWriter("product-registration.txt");
			 myWriter.write(productRegistration); //make report more into a table with java string
			  myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		      
		} catch (IOException e) {
		
			 System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}
	
	
	
	public static String printCurrentInventory () {
		String table = String.format("%-10s %-15s %-10s %-10s %-10s %-10s\n", "ID", "Product Name", "Price", "Quantity", "Sold", "Revenue") +
                "------------------------------------------------------------------\n";
		
		for (Map.Entry<Integer, Product> entry: InventoryOperations.getProductList().entrySet()) {
			
			table += String.format("%-10s %-15s %-10s %-10s %-10s %-10s\n", entry.getValue().getSKU(), entry.getValue().getName(), entry.getValue().getPrice(), entry.getValue().getQuantity(),entry.getValue().getSold(),entry.getValue().getRevenue());
			
		}
		
		return table;
	}



public static void printingLowStockReports() throws NoProductsInInventoryException {
	//make error custom exception if no product is in the array
	
	String table = String.format("%-10s %-15s %-10s %-10s %-10s %-10s\n", "ID", "Product Name", "Price", "Quantity", "Sold", "Revenue") +
            "------------------------------------------------------------------\n";
	
	for (Map.Entry<Integer, Product> entry: InventoryOperations.getProductList().entrySet()) {
		
		if(entry.getValue().getQuantity() < 3 ) {
		
		table += String.format("%-10s %-15s %-10s %-10s %-10s %-10s\n", "001", entry.getValue().getName(), entry.getValue().getPrice(), entry.getValue().getQuantity(),entry.getValue().getSold(),entry.getValue().getRevenue());
		}
	}
		
	try {
	
		//repeated code - make into method or own class/interface
		FileWriter myWriter = new FileWriter("low-stock-products.txt");
		 myWriter.write(table);
		 myWriter.close();
	      System.out.println("Successfully wrote to the file.");
	      
	} catch (IOException e) {
		// TODO Auto-generated catch block
		 System.out.println("An error occurred.");
		e.printStackTrace();
	}
	
}



}
