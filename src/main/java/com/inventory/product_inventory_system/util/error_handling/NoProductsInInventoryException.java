package com.inventory.product_inventory_system.util.error_handling;

public class NoProductsInInventoryException extends Exception {
	public NoProductsInInventoryException(String message) {
		super(message);
	}
}
