package com.inventory.product_inventory_system.util.inventory_menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.inventory.product_inventory_system.authentication.Authenitcation;
import com.inventory.product_inventory_system.model.User;
import com.inventory.product_inventory_system.service.InventoryOperations;
import com.inventory.product_inventory_system.service.OrderPurchase;
import com.inventory.product_inventory_system.service.PrintingReports;
import com.inventory.product_inventory_system.util.error_handling.NoProductsInInventoryException;
import com.inventory.product_inventory_system.util.error_handling.ProductNotFoundException;
import com.inventory.product_inventory_system.util.input_util.InputUtil;
import com.inventory.product_inventory_system.util.print_util.PrintingUtil;
import com.inventory.product_inventory_system.util.stock_management.ListUtil;

public class InventoryMenu {

	public static void showInventoryMenu(User user) {

		do {
			try {

				System.out.println("Welcome to the Inventory Product Management System!");
				if (ListUtil.getProductList().isEmpty()) {
					System.out.println("------------------------------------------------------------------\n"
							+ "No products have been imported yet\n"
							+ "------------------------------------------------------------------\n");
				} else {
					System.out.println(PrintingUtil.printCurrentInventory());
				}

				PrintingUtil.displayMenu();

				InputUtil.getUserInput("Input below" + "/n" + "---------------------------");
				InputUtil.scanner.nextLine(); // Consume the newline character

				if (InputUtil.getUserInput() == 1) {
					InventoryOperations.addNewProduct();

				} else if (InputUtil.getUserInput() == 2) {

					try {
						InventoryOperations.removeProductFromList();
					} catch (NoProductsInInventoryException | ProductNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else if (InputUtil.getUserInput() == 3) {
					try {
						InventoryOperations.viewProductDetails();
					} catch (NoProductsInInventoryException | ProductNotFoundException e) {

						e.printStackTrace();
					}

				} else if (InputUtil.getUserInput() == 4) {
					try {
						InventoryOperations.editProduct();
					} catch (NoProductsInInventoryException | ProductNotFoundException e) {

						e.printStackTrace();
					}
				} else if (InputUtil.getUserInput()  == 5) {
					try {
						OrderPurchase.purchaseProduct();
					} catch (NoProductsInInventoryException | ProductNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else if (InputUtil.getUserInput()  == 6) {

					try {
						PrintingReports.printingGeneralReports();
					} catch (NoProductsInInventoryException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else if (InputUtil.getUserInput()  == 7) {
					InventoryOperations.SearchProducts();



				} else if (InputUtil.getUserInput() == 0) {

					Authenitcation.Logout(user); // ??

				} else {
					System.out.println("Invalid choose another option");
				}

			} catch (InputMismatchException e) {
				System.out.println("You must enter whole numbers only. No decimals, words, or other info. Try again."
						+ "\n" + "Press any number to go back to the main menu" + "\n" + "Input below:\n"
						+ "-------------------------------");
				InputUtil.scanner.nextLine();
				InputUtil.getUserInput();

			}

		} while (user.getIsLoggedIn() == true);

	}

}

