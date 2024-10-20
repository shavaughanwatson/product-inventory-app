package com.inventory.product_inventory_system;

import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;

public class PrintingReports {
	
	public static void printingGeneralReports(String filename,String reportInput) {
		
		try {
			
			FileWriter myWriter = new FileWriter(filename);
			 myWriter.write(reportInput);
			  myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		      
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}



public static void printingLowStockReports() {
		
	try {
	
		
		FileWriter myWriter = new FileWriter("low-stock-products.txt");
		 myWriter.write(InventoryOperations.getProductList().entrySet().stream()
					.filter( entry -> entry.getValue().getQuantity() < 3)
					.toList()
					.toString());
		  myWriter.close();
	      System.out.println("Successfully wrote to the file.");
	      
	} catch (IOException e) {
		// TODO Auto-generated catch block
		 System.out.println("An error occurred.");
		e.printStackTrace();
	}
	
}

}
