package com.inventory.product_inventory_system;
import java.io.File;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;

public class PrintingReports {
	
	public static void printingGeneralReports() {
		System.out.println(InventoryOperations.getProductList().values());
		try {
			File myObj = new File("C:\\Users\\Owner\\Documents\\txt.file");
			
			if (myObj.createNewFile()) {
				 System.out.println("File created"); 
			} else {
				System.out.println("File already exists"); 
			}
			
			FileWriter myWriter = new FileWriter("filename.txt");
			 myWriter.write("Files in Java might be tricky, but it is fun enough!");
			  myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}

}
