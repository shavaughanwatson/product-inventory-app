package com.inventory.product_inventory_system.util.inventory_menu;


import com.inventory.product_inventory_system.service.InventoryOperations;

import com.inventory.product_inventory_system.util.error_handling.NoProductsInInventoryException;
import com.inventory.product_inventory_system.util.error_handling.ProductNotFoundException;
import com.inventory.product_inventory_system.util.input_util.InputUtil;

public class SearchMenu {
	

	public static void openSearchMenu () {
		
		System.out.println("Select you Search! Press the follwowing commands or press 4 to exit");
		System.out.println("1 = Search by name");
		System.out.println("2 = Search by SKU");
		System.out.println("3 = Search by categories");
		int option = InputUtil.scanner.nextInt();
		do {
			
			switch (option) {
			case 1:
				try {
					InventoryOperations.searchByName();
				} catch (NoProductsInInventoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 2:
				
	
				try {
					InventoryOperations.searchBySKU();
				} catch (NoProductsInInventoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ProductNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				break;
			case 3:
				try {
					InventoryOperations.searchByCategories();
				} catch (NoProductsInInventoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			default:
				System.out.println("Invalid option. Press any key to restart or press 0 to exit program");
				
			}
			
		} while ( option == 7);
				
		}
	
}