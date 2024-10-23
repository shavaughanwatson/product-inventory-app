package com.inventory.product_inventory_system;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class PrintingReports {

	public static void printingGeneralReports() throws NoProductsInInventoryException {
		//make error custom exception if no product is in the array
		if( InventoryOperations.getProductList().size() == 0 ) {
			throw new NoProductsInInventoryException("No Products have been found." + "\n" + "Please insert new Products");
		}
		
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
		
		try {
			
			String productRegistration = "Product Registration \n" + "------------------" + "\n"+ "Product SKU: "+ product.getSKU() + "\n" + "Product Name: "+ product.getName() + "\n" + "Price: " + product.getPrice() + "\n" + "Quantity: " + product.getQuantity() +"\n" + "Category: " + product.getCategory() + "\n" + product.getDate() + "\n" + "Product registered successfully!";
				
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
	
	public static void printingUserRegistered(User user) {
		
		
		try {
			
			String userRegistration = "User Information \n" + "------------------" + "\n"+ "Username: "+ user.getUserName() + "\n" + "Password: "+ user.getUserPassword() + "\n" + "------------------" + "\n" + "User has registered succesfully!" ;
				
			//repeated code - make into method or own class/interface
			FileWriter myWriter = new FileWriter("user-registration.txt");
			 myWriter.write(userRegistration); //make report more into a table with java string
			  myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		      
		} catch (IOException e) {
		
			 System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
	}
	
	
	
	public static String printCurrentInventory () {
		String table = String.format("%-10s %-15s %-10s %-15s %-10s %-15s %-10s %-10s\n", "ID", "Product Name", "Price", "Category", "Quantity", "Exp.Date", "Sold", "Revenue") +
                "-----------------------------------------------------------------------------------------------------\n";
		
		for (Map.Entry<Integer, Product> entry: InventoryOperations.getProductList().entrySet()) {
			
			table += String.format("%-10s %-15s %-10s %-15s %-10s %-15s %-10s %-10s\n", entry.getValue().getSKU(), entry.getValue().getName(), entry.getValue().getPrice(), entry.getValue().getCategory(), entry.getValue().getQuantity(), entry.getValue().getDate(), entry.getValue().getSold(),entry.getValue().getRevenue());
			
		}
		
		return table;
	}


}
