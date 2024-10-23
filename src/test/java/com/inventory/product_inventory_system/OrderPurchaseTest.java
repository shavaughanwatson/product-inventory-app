package com.inventory.product_inventory_system;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderPurchaseTest {

    private int sku;  // To store the SKU of the created product

    @BeforeEach
    public void setUp() {
        // Clear previous products for fresh tests
        InventoryOperations.getProductList().clear();
      
        Product product = new Product("Dell Laptop", 10, 999.99, "MERCHANDISE", LocalDate.of(2025, 12, 31));
        this.sku = product.getSKU();  // Store the SKU of the created product
        InventoryOperations.getProductList().put(1, product);  // Use the SKU as the key
    }

    @Test
    public void testPurchaseProduct_Successful() {
        // Simulate user inputs: select product with SKU (use the stored SKU), purchase 3 items, exit
        String input = sku + "\n3\n0\n";  // Use the actual SKU for the product
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Call the purchaseProduct method
        assertDoesNotThrow(() -> OrderPurchase.purchaseProduct());

        // Verify the product quantity and sold count have been updated
        Product purchasedProduct = InventoryOperations.getProductList().get(sku);
        assertEquals(7, purchasedProduct.getQuantity(), "The quantity should be updated to 7.");
        assertEquals(3, purchasedProduct.getSold(), "The sold quantity should be 3.");
    }

    @Test
    public void testPurchaseProduct_NoProductsInInventory() {
        // Clear the product list to simulate no products available
        InventoryOperations.getProductList().clear();

        assertThrows(NoProductsInInventoryException.class, () -> {
            OrderPurchase.purchaseProduct();
        });
    }

    @Test
    public void testPurchaseProduct_InvalidQuantity() {
        // Simulate user inputs: select product with SKU (use the stored SKU), purchase 15 items (invalid), exit
        String input = sku + "\n15\n0\n";  // Use the actual SKU for the product
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Call the purchaseProduct method
        assertDoesNotThrow(() -> OrderPurchase.purchaseProduct());
     
        // Verify that the quantity remains the same since the purchase is invalid
        Product purchasedProduct = InventoryOperations.getProductList().get(sku);
        assertEquals(10, purchasedProduct.getQuantity(), "The quantity should remain at 10 since purchase was invalid.");
        assertEquals(0, purchasedProduct.getSold(), "The sold quantity should still be 0.");
    }

    // Additional tests for edge cases, etc.
}
