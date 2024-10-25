package com.inventory.product_inventory_system.util.error_handling;

public class InvalidLoginException extends Exception {
    public InvalidLoginException(String message) {
        super(message);
    }
}
