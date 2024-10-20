package com.inventory.product_inventory_system;


import java.util.Map;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {

		//Scanner and userInput should be class OR interface -- need more research on both
		Scanner scanner = new Scanner(System.in); //have to be within the scope
		int userInput; //

		do {
			// Choose Sign Up or Login UP
			System.out.println("Let's get started! n/ Press any # number to sign up or press 0 to exit ");
			
			userInput = scanner.nextInt();
			// Press 1 to sign up; Press 2 to login

			if (userInput != 0) {// if user input 1 -- sign up function
				scanner.nextLine(); // Consume the newline character
				
				////Login should be in Authentication Class- should it be class or interface
				int id = 0;
				System.out.println("Enter username");
				String usernameInput = scanner.nextLine();

				System.out.println("Enter password");
				String passwordInput = scanner.nextLine();
				User user = new User(usernameInput, passwordInput);
				System.out.println(user);
				
				String userRegisterInfo = user.toString();
				
				
				Authenitcation.getUsers().put(id++, user); // why doesn't it add to current list if static variable 
				System.out.println(Authenitcation.getUsers());

				user.setisLoggedIn(true);

				
				while (user.getIsLoggedIn() == true) { //user has to be outside the scope
					System.out.println("Welcome to the Inventory Product Management System!");
					if (InventoryOperations.getProductList().size() == 0) {
						System.out.println( "------------------------------------------------------------------\n"+"No products have been imported yet\n" +  "------------------------------------------------------------------\n");
					} else {
						
						String table = String.format("%-10s %-15s %-10s %-10s %-10s %-10s\n", "SKU", "Product Name", "Price", "Quantity", "Sold", "Revenue") +
			                    "------------------------------------------------------------------\n";
						
						for (Map.Entry<Integer, Product> entry: InventoryOperations.getProductList().entrySet()) {
							
							table += String.format("%-10s %-15s %-10s %-10s %-10s %-10s\n", entry.getValue().getSKU(), entry.getValue().getName(), entry.getValue().getPrice(), entry.getValue().getQuantity(),entry.getValue().getSold(),entry.getValue().getRevenue());
							
						}
						
						System.out.println(table);
					      
					}
					
					//menu should be switched to switch statment inside it's own function
					System.out.println("Select you main menu!");
					System.out.println("1 = add product");
					System.out.println("2 = remove product");
					System.out.println("3 = get product");
					System.out.println("4 = edit product");
					System.out.println("5 = purchase products");
					System.out.println("6 = generate report");
					System.out.println("7 = sign up");

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
						System.out.println("Which report you would like to print out Press 1 for general; Press 2 for low stock");
						userInput = scanner.nextInt();
						if(userInput == 1) {
						PrintingReports.printingGeneralReports();
						}
						
						if(userInput == 2) {
							PrintingReports.printingLowStockReports();
						}
						
					} else if (userInput == 7) {
						InventoryOperations.SearchProducts();
						
					} else if (userInput == 8) {
						InventoryOperations.SearchSKU();
						
					
						
					
					} else if (userInput == 9) {
						
						user.setisLoggedIn(false);
						
						
					} else {
						System.out.println("Invalid choose another option");
					}
					
					
				};
	
			} else {
				System.out.println("GoodBye");
			}

		} while (userInput != 0); // userLoggedIn == true

		scanner.close();
	}
}
