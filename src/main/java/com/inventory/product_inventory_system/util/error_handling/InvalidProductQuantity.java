package com.inventory.product_inventory_system.util.error_handling;

public class InvalidProductQuantity extends Exception {
    public InvalidProductQuantity(String message) {
        super(message);
    }
}
