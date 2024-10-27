package com.inventory.product_inventory_system;




import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


import java.io.ByteArrayInputStream;
import java.time.LocalDate;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.inventory.product_inventory_system.model.Product;
import com.inventory.product_inventory_system.service.InventoryOperations;

import com.inventory.product_inventory_system.util.stock_management.ListUtil;

public class InventoryOperationsTest {


	 	@BeforeEach
	    public void setUp() {
	        // Clear the product list before each test
	        ListUtil.getProductList().clear(); // Directly clearing the productList
	    }

	 	@Test
	 	public void testAddNewProduct_Successful() {
	 	    // Arrange: Clear the product list and set up simulated user input
	 	    ListUtil.getProductList().clear();

	 	    // Define the simulated input values
	 	    String input = "Laptop\n10\n999.99\n1\n31-12-2025\n0\n"; // Product name, quantity, price, category, expiry date, exit option
	 	    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
	 	    System.setIn(in);

	 	    // Act: Call the addNewProduct method
	 	    assertDoesNotThrow(() -> InventoryOperations.addNewProduct());

	 	    // Assert: Check if the product was added to the list with correct attributes
	 	    assertFalse(ListUtil.getProductList().isEmpty(), "The product list should contain the newly added product.");

	 	    Product addedProduct = ListUtil.getProductList().values().iterator().next(); // Get the first product added

	 	    // Verify product attributes
	 	    assertEquals("Laptop", addedProduct.getName(), "Product name should match.");
	 	    assertEquals(10, addedProduct.getQuantity(), "Product quantity should match.");
	 	    assertEquals(999.99, addedProduct.getPrice(), 0.01, "Product price should match.");
	 	    assertEquals("MERCHANDISE", addedProduct.getCategory(), "Product category should match.");
	 	    assertEquals(LocalDate.of(2025, 12, 31), addedProduct.getDate(), "Product expiry date should match.");
	 	}

	 	

}
