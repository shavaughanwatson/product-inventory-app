package com.inventory.product_inventory_system;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.inventory.product_inventory_system.model.Product;
import com.inventory.product_inventory_system.service.InventoryOperations;
import com.inventory.product_inventory_system.service.OrderPurchase;
import com.inventory.product_inventory_system.util.error_handling.NoProductsInInventoryException;
import com.inventory.product_inventory_system.util.stock_management.ListUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderPurchaseTest {

    private int sku;  // To store the SKU of the created product



    @Test
    public void testPurchaseProduct_Successful() {
        // Arrange: Add a product to the inventory
        Product product = new Product("Laptop", 10, 999.99, "MERCHANDISE", LocalDate.of(2025, 12, 31));
        int sku = product.getSKU(); // Retrieve the SKU directly from the added product
        ListUtil.getProductList().put(sku, product);
        
     // Debugging: Confirm the product is in the inventory list
        assertTrue(ListUtil.getProductList().containsKey(sku), "The product should be in the inventory.");
    	
        // Simulate user inputs: select product with SKU (use the stored SKU), purchase 3 items, exit
        String input = sku + "\n3\n0\n";  // Use the actual SKU for the product
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Call the purchaseProduct method
        assertDoesNotThrow(() -> OrderPurchase.purchaseProduct());

        // Verify the product quantity and sold count have been updated
        Product purchasedProduct = ListUtil.getProductList().get(sku);
        assertEquals(7, purchasedProduct.getQuantity(), "The quantity should be updated to 7.");
        assertEquals(3, purchasedProduct.getSold(), "The sold quantity should be 3.");
    }

    @Test
    public void testPurchaseProduct_NoProductsInInventory() {
        // Clear the product list to simulate no products available
        ListUtil.getProductList().clear();

        assertThrows(NoProductsInInventoryException.class, () -> {
            OrderPurchase.purchaseProduct();
        });
    }

 

    // Additional tests for edge cases, etc.
}
