package com.inventory.product_inventory_system.util.error_handling;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
