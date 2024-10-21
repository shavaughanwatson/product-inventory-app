package com.inventory.product_inventory_system;

public class NoProductsInInventoryException extends Exception {
	public NoProductsInInventoryException(String message) {
		super(message);
	}
}
