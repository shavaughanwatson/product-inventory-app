package com.inventory.product_inventory_system.util.error_handling;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

public class HandleErrors {
	
	public static void handleAddProductError(Exception e) {
	    if (e instanceof InputMismatchException) {
	        System.out.println("Invalid input. Enter whole numbers only.");
	    } else if (e instanceof ArrayIndexOutOfBoundsException) {
	        System.out.println("Invalid option, please try again.");
	    } else if (e instanceof DateTimeParseException) {
	        System.out.println("Invalid date format. Please use dd-MM-yyyy.");
	    }
	}
	
	
}
