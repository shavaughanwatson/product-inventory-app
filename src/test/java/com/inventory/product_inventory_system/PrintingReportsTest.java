package com.inventory.product_inventory_system;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.inventory.product_inventory_system.model.Product;
import com.inventory.product_inventory_system.model.User;
import com.inventory.product_inventory_system.service.InventoryOperations;
import com.inventory.product_inventory_system.service.PrintingReports;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;

public class PrintingReportsTest {

    // Test for printingGeneralReports
    @Test
    public void testPrintingGeneralReports() {
        // Assuming InventoryOperations has a method to add a product for testing
    	Product testProduct = new Product("Dell Laptop", 10, 999.99, "MERCHANDISE", LocalDate.of(2025, 12, 31));
        InventoryOperations.getProductList().put(testProduct.getSKU(), testProduct);
        
        // This method should not throw an exception when products exist
        assertDoesNotThrow(() -> {
            PrintingReports.printingGeneralReports();
        });

        // Check if the file was created and contains expected content
        try (BufferedReader reader = new BufferedReader(new FileReader("product-data.txt"))) {
            String line = reader.readLine();
            assertNotNull(line); // Check that the first line is not null
            // Add more assertions as needed for specific content
        } catch (IOException e) {
            fail("IOException occurred while reading the report file.");
        }
    }

    // Test for printingProductRegistered
    @Test
    public void testPrintingProductRegistered() {
        Product testProduct = new Product("Dell Laptop", 10, 999.99, "MERCHANDISE", LocalDate.of(2025, 12, 31));
        
        // This method should write to a file
        assertDoesNotThrow(() -> {
            PrintingReports.printingProductRegistered(testProduct);
        });

        // Check if the file was created and contains expected content
        try (BufferedReader reader = new BufferedReader(new FileReader("product-registration.txt"))) {
            String line = reader.readLine();
            assertNotNull(line); // Check that the first line is not null
            // Add more assertions as needed for specific content
        } catch (IOException e) {
            fail("IOException occurred while reading the product registration file.");
        }
    }

    // Test for printingUserRegistered
    @Test
    public void testPrintingUserRegistered() {
        User testUser = new User("testUser", "password123");
        
        // This method should write to a file
        assertDoesNotThrow(() -> {
            PrintingReports.printingUserRegistered(testUser);
        });

        // Check if the file was created and contains expected content
        try (BufferedReader reader = new BufferedReader(new FileReader("user-registration.txt"))) {
            String line = reader.readLine();
            assertNotNull(line); // Check that the first line is not null
            // Add more assertions as needed for specific content
        } catch (IOException e) {
            fail("IOException occurred while reading the user registration file.");
        }
    }

    // Test for printCurrentInventory
    @Test
    public void testPrintCurrentInventory() {
        // Assuming InventoryOperations has a method to add a product for testing
    	Product testProduct = new Product("Dell Laptop", 10, 999.99, "MERCHANDISE", LocalDate.of(2025, 12, 31));
        InventoryOperations.getProductList().put(testProduct.getSKU(), testProduct);

        String inventoryOutput = PrintingReports.printCurrentInventory();
        assertNotNull(inventoryOutput);
        assertTrue(inventoryOutput.contains("Dell Laptop")); // Check for test product name in output
    }

    // Clean up after tests, remove test files if necessary
    @AfterEach
    public void cleanUp() {
        new File("product-data.txt").delete();
        new File("product-registration.txt").delete();
        new File("user-registration.txt").delete();
    }
}
