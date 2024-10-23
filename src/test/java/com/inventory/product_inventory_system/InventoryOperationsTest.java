package com.inventory.product_inventory_system;



import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InventoryOperationsTest {


	 	@BeforeEach
	    public void setUp() {
	        // Clear the product list before each test
	        InventoryOperations.getProductList().clear();; // Directly clearing the productList
	    }

	    @Test
	    public void testAddProduct() {
	        // Simulate user input
	        String simulatedInput = "Dell Laptop\n10\n999.99\n1\n31-12-2025\n";
	        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
	        System.setIn(in);

	        // Call the method to add a product
	        InventoryOperations.addProduct();

	        // Verify the product was added
	        Product addedProduct = InventoryOperations.getProductList().values().iterator().next();

	        assertNotNull(addedProduct, "Product should be added to the list");
	        assertEquals("Dell Laptop", addedProduct.getName(), "Product name should match");
	        assertEquals(10, addedProduct.getQuantity(), "Product quantity should match");
	        assertEquals(999.99, addedProduct.getPrice(), "Product price should match");
	        assertEquals("MERCHANDISE", addedProduct.getCategory(), "Product category should match");
	        assertEquals(LocalDate.of(2025, 12, 31), addedProduct.getDate(), "Product expiration date should match");
	    }
	    
	    @Test
	    public void testRemoveProduct() throws NoProductsInInventoryException, ProductNotFoundException {
	        // Arrange: Add a product to the list first
	        Product product = new Product("Dell Laptop", 10, 999.99, "MERCHANDISE", LocalDate.of(2025, 12, 31));
	        InventoryOperations.getProductList().put(product.getSKU(), product);
	        
	        // Simulate user input for removing the product (entering the SKU), followed by the input to exit the loop (not 1)
	        String simulatedInput = product.getSKU() + "\n2\n";  // First input: SKU to remove, Second input: to exit the loop
	        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
	        System.setIn(in);

	        // Act: Call the removeProduct method
	        InventoryOperations.removeProduct();

	        // Assert: Check that the product is no longer in the product list
	        assertFalse(InventoryOperations.getProductList().containsKey(product.getSKU()), 
	            "Product should be removed from the list.");
	    }
	    
	    @Test
	    public void testRemoveProductThrowsExceptionWhenInventoryEmpty() {
	        // Arrange: Ensure the product list is empty

	        // Simulate user input (any input since there are no products)
	        String simulatedInput = "12345\n";
	        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
	        System.setIn(in);

	        // Act & Assert: Verify that the correct exception is thrown
	        Exception exception = assertThrows(NoProductsInInventoryException.class, () -> {
	            InventoryOperations.removeProduct();
	        });
	        assertEquals("No Products have been found.\nPlease insert new Products", exception.getMessage());
	    }

	    @Test
	    public void testRemoveProductThrowsExceptionWhenProductNotFound() throws NoProductsInInventoryException {
	        // Arrange: Add a product to the list
	        Product product = new Product("Dell Laptop", 10, 999.99, "MERCHANDISE", LocalDate.of(2025, 12, 31));
	        InventoryOperations.getProductList().put(product.getSKU(), product);
	        
	        // Simulate input for a non-existing SKU
	        String simulatedInput = "99999\n"; // SKU that doesn't exist
	        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
	        System.setIn(in);

	        // Act & Assert: Verify that the correct exception is thrown
	        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
	            InventoryOperations.removeProduct();
	        });
	        assertEquals("No Products have been found in inventory", exception.getMessage());
	    }

	    @Test
	    public void testGetProduct_NoProductsInInventoryException() {
	        // Test that NoProductsInInventoryException is thrown when productList is empty
	        assertThrows(NoProductsInInventoryException.class, () -> {
	            InventoryOperations.getProduct();
	        });
	    }

	    @Test
	    public void testGetProduct_ProductNotFoundException() {
	        // Add a product to the list to avoid NoProductsInInventoryException
	        Product product = new Product("Dell Laptop", 10, 999.99, "MERCHANDISE", LocalDate.of(2025, 12, 31));
	        InventoryOperations.getProductList().put(product.getSKU(), product);

	        // Simulate a scanner input for a nonexistent product ID
	        assertThrows(ProductNotFoundException.class, () -> {
	            // Assume user input is an invalid product ID (e.g., 2, which is not in the list)
	            System.setIn(new java.io.ByteArrayInputStream("2\n".getBytes()));
	            InventoryOperations.getProduct();
	        });
	    }

	    @Test
	    public void testGetProduct_SuccessfulRetrievalAndUpdate() {
	        // Initialize the product list
	    

	        // Add a product with a specific SKU
	        Product product = new Product("Dell Laptop", 10, 999.99, "MERCHANDISE", LocalDate.of(2025, 12, 31));
	        int productSKU = product.getSKU();  // Assuming SKU is an integer
	        InventoryOperations.getProductList().put(productSKU, product);

	        // Simulate user inputs for:
	        // 1st input: SKU of the product (use productSKU)
	        // 2nd input: 2 (add quantity option)
	        // 3rd input: 3 (add 3 units to the product)
	        // 4th input: 0 (exit the menu after adding quantity)
	        String input = productSKU + "\n2\n3\n0\n";
	        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));//

	        // Call the getProduct method (no exception should be thrown)
	        assertDoesNotThrow(() -> {
	            InventoryOperations.getProduct();
	        });

	        // Check if the product quantity was updated correctly (original was 10 + 3)
	        assertEquals(13, InventoryOperations.getProductList().get(productSKU).getQuantity(), "The quantity should be updated to 13.");
	    }

}
