package com.inventory.product_inventory_system.app;
import com.inventory.product_inventory_system.authentication.Authenitcation;
import com.inventory.product_inventory_system.util.error_handling.InvalidLoginException;
import com.inventory.product_inventory_system.util.input_util.InputUtil;

public class App {
	public static void main(String[] args) {

	

		int option = -1;// why???
		

		do {
			System.out.println("Welcome to the Inventory System");
			System.out.println("1. Sign Up");
			System.out.println("2. Login");
			System.out.print("Choose an option: ");
			// Get user input for the menu
			if (InputUtil.scanner.hasNextInt()) {
				option = InputUtil.scanner.nextInt(); // why??
				InputUtil.scanner.nextLine(); // Consume newline
				switch (option) {
				case 1:
					Authenitcation.SignUp();

					break;
				case 2:
					
					try {
						Authenitcation.Login();
					} catch (InvalidLoginException e) {
						
						e.printStackTrace();
					}
					
				
					break;
				default:
					System.out.println("Invalid option. Press any key to restart or press 0 to exit program");
					option = InputUtil.scanner.nextInt();
				}

			} else {
				System.out.println("Invalid input. Please enter a valid option (1, 2, or 0).");
				InputUtil.scanner.nextLine(); // Consume invalid input
			}

		} while (option != 0);
		InputUtil.scanner.close();
	}

}




