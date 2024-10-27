package com.inventory.product_inventory_system.util.print_util;

import java.util.Map;

import com.inventory.product_inventory_system.model.Product;
import com.inventory.product_inventory_system.model.User;
import com.inventory.product_inventory_system.util.file_writer_util.FileWriterUtil;

import com.inventory.product_inventory_system.util.stock_management.ListUtil;

public class PrintingUtil {
	
	public static void printingProductRegistered(Product product) {

		String productRegistration = "Product Registration \n" + "------------------" + "\n" + "Product SKU: "
				+ product.getSKU() + "\n" + "Product Name: " + product.getName() + "\n" + "Price: " + product.getPrice()
				+ "\n" + "Quantity: " + product.getQuantity() + "\n" + "Category: " + product.getCategory() + "\n"
				+ product.getDate() + "\n" + "Product registered successfully!";

		FileWriterUtil.fileWrite("product-registration.txt", productRegistration);
	}

	
	
	public static void printingUserRegistered(User user) {

		String userRegistration = "User Information \n" + "------------------" + "\n" + "Username: "
				+ user.getUserName() + "\n" + "Password: " + user.getUserPassword() + "\n" + "------------------" + "\n"
				+ "User has registered succesfully!";

		FileWriterUtil.fileWrite("user-registration.txt", userRegistration);

	}

	public static String printCurrentInventory() {//this should be in util package
		String table = String.format("%-10s %-15s %-10s %-15s %-10s %-15s %-10s %-10s\n", "ID", "Product Name", "Price",
				"Category", "Quantity", "Exp.Date", "Sold", "Revenue")
				+ "-----------------------------------------------------------------------------------------------------\n";

		for (Map.Entry<Integer, Product> entry : ListUtil.getProductList().entrySet()) {

			table += String.format("%-10s %-15s %-10s %-15s %-10s %-15s %-10s %-10s\n", entry.getValue().getSKU(),
					entry.getValue().getName(), entry.getValue().getPrice(), entry.getValue().getCategory(),
					entry.getValue().getQuantity(), entry.getValue().getDate(), entry.getValue().getSold(),
					entry.getValue().getRevenue());

		}
		
		

		return table;
	}
	
	
	public static void displayMenu() {//this should be in util package
		//////display menu()
		System.out.println("Select you main menu!");
		System.out.println("1 = add product");
		System.out.println("2 = remove product");
		System.out.println("3 = get product");
		System.out.println("4 = edit product");
		System.out.println("5 = purchase products");
		System.out.println("6 = generate report");
		
		System.out.println("7 = search product ");

		System.out.println("0 = Logout");
		
	}
	

	
	public static void confirmRemovedProduct( Product product) {//this should be in util package
		System.out.println("Product has been successfully removed" + "\n" + "------------------" + "\n"
				+ "Product Removed" + "\n" + "Product Name: " + product.getName() + "\n" + "Price: "
				+ product.getPrice() + "\n" + " Quantity: " + product.getQuantity());
		
	}
	
	public static void confirmNewProduct(String confirmMessage, int selectedProduct, int addedQuantityInput ) {//**
		System.out.println(confirmMessage+ "\n" + "-------------------------------\n"
				+ "Product update: \n" + ListUtil.getProductList().get(selectedProduct).getSKU() + "\n"
				+ ListUtil.getProductList().get(selectedProduct).getName() + "\n"
				+ ListUtil.getProductList().get(selectedProduct).getPrice() + "\n"
				+ ListUtil.getProductList().get(selectedProduct).getQuantity() + "(" + "+" + addedQuantityInput
				+ ")");
		
	}
	
	public static void confirmNewProduct(String confirmMessage, int selectedProduct ) {//**
		System.out.println(confirmMessage+ "\n" + "-------------------------------\n"
				+ "Product update: \n" + ListUtil.getProductList().get(selectedProduct).getSKU() + "\n"
				+ ListUtil.getProductList().get(selectedProduct).getName() + "\n"
				+ ListUtil.getProductList().get(selectedProduct).getPrice() + "\n"
				+ ListUtil.getProductList().get(selectedProduct).getQuantity() + "\n" +
				printCurrentInventory());
		
	}
	
	public static void displayProductMenuOptions(int selectedProduct) {//this should be in util package
		System.out.println("Product Menu\n" + "-------------------------------\n" + "Product Info: \n"
				+ ListUtil.getProductList().get( selectedProduct).getSKU() + "\n"
				+ ListUtil.getProductList().get(selectedProduct).getName() + "\n"
				+ ListUtil.getProductList().get(selectedProduct).getPrice() + "\n"
				+ ListUtil.getProductList().get(selectedProduct).getQuantity());

		System.out.println("Press 2 to add quantity or press another key to exit menu");
		
	}
	

	public static void displayInputError() {//this should be in util package
		System.out.println("You must enter whole numbers only. No decimals, words, or other info. Try again."
				);
		
	}

	public static void displayExitOptions () {
		System.out.println("Press one to select a another product or press another key to exit");
		System.out.println("------------------" + "\n" + "Input below:");
	}

}
