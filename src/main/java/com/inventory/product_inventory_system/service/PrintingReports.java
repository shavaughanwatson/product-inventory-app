package com.inventory.product_inventory_system.service;



import java.util.Map;

import com.inventory.product_inventory_system.model.Product;
import com.inventory.product_inventory_system.model.User;
import com.inventory.product_inventory_system.util.error_handling.NoProductsInInventoryException;
import com.inventory.product_inventory_system.util.file_writer_util.FileWriterUtil;
import com.inventory.product_inventory_system.util.stock_management.ListUtil;

public class PrintingReports {

	public static void printingGeneralReports() throws NoProductsInInventoryException {
		
		//make error custom exception if no product is in the array
		if( ListUtil.getProductList().size() == 0 ) {
			throw new NoProductsInInventoryException("No Products have been found." + "\n" + "Please insert new Products");
		}
				
		FileWriterUtil.fileWrite("product-data.txt", PrintingReports.printCurrentInventory());
			      
		
	}
	
	public static void printingProductRegistered(Product product) {
		
		
			String productRegistration = "Product Registration \n" + "------------------" + "\n"+ "Product SKU: "+ product.getSKU() + "\n" + "Product Name: "+ product.getName() + "\n" + "Price: " + product.getPrice() + "\n" + "Quantity: " + product.getQuantity() +"\n" + "Category: " + product.getCategory() + "\n" + product.getDate() + "\n" + "Product registered successfully!";
			
			FileWriterUtil.fileWrite("product-registration.txt", productRegistration);
	}	

		

	
	public static void printingUserRegistered(User user) {
		
		
			
			String userRegistration = "User Information \n" + "------------------" + "\n"+ "Username: "+ user.getUserName() + "\n" + "Password: "+ user.getUserPassword() + "\n" + "------------------" + "\n" + "User has registered succesfully!" ;
			
			
			FileWriterUtil.fileWrite("user-registration.txt", userRegistration);
			

		
	}
	
	
	
	public static String printCurrentInventory () {
		String table = String.format("%-10s %-15s %-10s %-15s %-10s %-15s %-10s %-10s\n", "ID", "Product Name", "Price", "Category", "Quantity", "Exp.Date", "Sold", "Revenue") +
                "-----------------------------------------------------------------------------------------------------\n";
		
		for (Map.Entry<Integer, Product> entry: ListUtil.getProductList().entrySet()) {
			
			table += String.format("%-10s %-15s %-10s %-15s %-10s %-15s %-10s %-10s\n", entry.getValue().getSKU(), entry.getValue().getName(), entry.getValue().getPrice(), entry.getValue().getCategory(), entry.getValue().getQuantity(), entry.getValue().getDate(), entry.getValue().getSold(),entry.getValue().getRevenue());
			
		}
		
		return table;
	}


}
