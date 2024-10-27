package com.inventory.product_inventory_system.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import com.inventory.product_inventory_system.model.Product;
import com.inventory.product_inventory_system.util.error_handling.HandleErrors;
import com.inventory.product_inventory_system.util.error_handling.NoProductsInInventoryException;
import com.inventory.product_inventory_system.util.error_handling.ProductNotFoundException;
import com.inventory.product_inventory_system.util.input_util.InputUtil;
import com.inventory.product_inventory_system.util.inventory_menu.SearchMenu;
import com.inventory.product_inventory_system.util.print_util.PrintingUtil;
import com.inventory.product_inventory_system.util.stock_management.ListUtil;

public class InventoryOperations {
	private static int userInput; // ***

	public static void addNewProduct() {

		do {
			try {
				// 1. Input Product attributes ***
				
				System.out.println("Enter product name: ");
				String productName = InputUtil.scanner.nextLine();
				
				System.out.println("Enter quantity: ");
				int productQuanity = InputUtil.scanner.nextInt();
				InputUtil.scanner.nextLine();
				
				System.out.println("Enter price: ");
				double productPrice = InputUtil.scanner.nextDouble();
				InputUtil.scanner.nextLine();
				String productCategory = InputUtil
						.getCategoryInput("Enter Category: Press 0 for FOOD; Press 1 for MERCHANDISE");

				LocalDate productExpiryDate = InputUtil

						.getDateInput("Enter date in following format" + "\n" + "dd-MM-yyyy");

				Product newProduct = new Product(productName, productQuanity, productPrice, productCategory,
						productExpiryDate);

				// 2. Input product information into text file
				PrintingUtil.printingProductRegistered(newProduct);

				// 3. Add product into main list.
				ListUtil.getProductList().put(newProduct.getSKU(), newProduct);

				PrintingUtil.displayExitOptions();

				userInput = InputUtil.scanner.nextInt();
				InputUtil.scanner.nextLine();
			} catch (InputMismatchException | ArrayIndexOutOfBoundsException | DateTimeParseException e) {

				HandleErrors.handleAddProductError(e);
			}

		} while (userInput == 1);

	}

	public static void removeProductFromList() throws NoProductsInInventoryException, ProductNotFoundException {
		if (ListUtil.getProductList().isEmpty()) {
			throw new NoProductsInInventoryException(
					"No Products have been found." + "\n" + "Please insert new Products");
		}

		do {

			try {

				int selectedProductSKU = InputUtil.selectedProductSKU();
				
				if (!ListUtil.getProductList().containsKey(selectedProductSKU )) {
					throw new ProductNotFoundException(
							"No Products have been found." );
				}


				Product removedProduct = ListUtil.getProductList().remove(selectedProductSKU);

				PrintingUtil.confirmRemovedProduct(removedProduct);

				PrintingUtil.displayExitOptions();

				InputUtil.getUserInput();
			} catch (InputMismatchException e) {

				PrintingUtil.displayInputError();
				InputUtil.scanner.nextLine();
				InputUtil.scanner.nextInt();

			}

		} while (InputUtil.getUserInput() == 1);
	}

	public static void viewProductDetails() throws NoProductsInInventoryException, ProductNotFoundException {

		if (ListUtil.getProductList().isEmpty()) {
			throw new NoProductsInInventoryException(
					"No Products have been found." + "\n" + "Please insert new Products");
		}

		do {

			try {
				System.out.println("Select Product SKU to view from list");
				System.out.println(PrintingUtil.printCurrentInventory());
				System.out.println("------------------" + "\n" + "Input Product ID below:");

				int productSelectedSKU = InputUtil.selectedProductSKU();

				if (!ListUtil.getProductList().containsKey(productSelectedSKU)) {
					throw new ProductNotFoundException("No Products have been found in inventory");
				}

				InputUtil.inputNewProductQuantity(productSelectedSKU);

			} catch (InputMismatchException e) {
				PrintingUtil.displayInputError();

			}

		} while (userInput == 1);

	}

	public static void editProduct() throws NoProductsInInventoryException, ProductNotFoundException {

		if (ListUtil.getProductList().size() == 0) {
			throw new NoProductsInInventoryException(
					"No Products have been found." + "\n" + "Please insert new Products");
		}

		do {

			try {
				System.out.println("Select Product SKU to Edit Product from list");
				System.out.println(PrintingUtil.printCurrentInventory());

				System.out.println("------------------" + "\n" + "Input Product ID below:");

				int selectedProductSKU = InputUtil.selectedProductSKU();

				if (!ListUtil.getProductList().containsKey(selectedProductSKU )) {
					throw new ProductNotFoundException(
							"No Products have been found." );
				}
				
				InputUtil.inputNewProductInfo(selectedProductSKU);

				System.out.println("Press one to select a another product or press another key to exit");
				System.out.println("------------------" + "\n" + "Input below:");
				
				userInput = InputUtil.scanner.nextInt();
				InputUtil.scanner.nextLine();
			} catch (InputMismatchException e) {
				PrintingUtil.displayInputError();

			}

		} while (userInput == 1);

	}



	public static void searchByName() throws NoProductsInInventoryException  {
		// make error custom exception if no product is in the array
		if (ListUtil.getProductList().isEmpty()) {
			throw new NoProductsInInventoryException(
					"No Products have been found." + "\n" + "Please insert new Products");
		}
		do {
			InputUtil.scanner.nextLine();
			try {
				System.out.println("Search for by name");
				System.out.println("------------------" + "\n" + "Input Query below:");

				String query = InputUtil.scanner.nextLine();
				System.out
						.println(
								ListUtil.getProductList().entrySet().stream()
										.filter(entry -> entry.getValue().getName().contains(query)
												|| entry.getValue().getName().contains(query))
										.toList().toString());

				System.out.println("Press one to select a another product or press another key to exit");
				System.out.println("------------------" + "\n" + "Input below:");
				userInput = InputUtil.scanner.nextInt();
			} catch (InputMismatchException e) {
				PrintingUtil.displayInputError();
				InputUtil.scanner.nextLine();
				userInput = InputUtil.scanner.nextInt();
			}

		} while (userInput == 1);

		
	}

	
	public static void searchByCategories() throws NoProductsInInventoryException { 
		// make error custom exception if no product is in the array
		if (ListUtil.getProductList().size() == 0) {
			throw new NoProductsInInventoryException(
					"No Products have been found." + "\n" + "Please insert new Products");
		}
		do {

			try {

				String category = InputUtil
						.getCategoryInput("Enter Category: Press 0 for FOOD; Press 1 for MERCHANDISE");

				ListUtil.getProductList().entrySet().stream()
						.filter(entry -> entry.getValue().getCategory().contains(category))
						.forEach(entry -> System.out.println(entry.getValue())); // turn this into a array;

				System.out.println("Press one to select a another product or press another key to exit");
				System.out.println("------------------" + "\n" + "Input below:");
				userInput = InputUtil.scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("You must enter whole numbers only. No decimals, words, or other info. Try again."
						+ "\n" + "Press 1 to product select a another product; or press another key to exit" + "\n"
						+ "Input below:\n" + "-------------------------------");
				InputUtil.scanner.nextLine();
				userInput = InputUtil.scanner.nextInt();
			}

		} while (userInput == 1);

		
	}

	public static void searchBySKU() throws NoProductsInInventoryException, ProductNotFoundException {
		if (ListUtil.getProductList().size() == 0) {
			throw new NoProductsInInventoryException(
					"No Products have been found." + "\n" + "Please insert new Products");
		}

		do {
			try {
				List<Product> productValues = new ArrayList<>(ListUtil.getProductList().values());

				System.out.println("Search for SKU 1 to search");
				System.out.println("------------------" + "\n" + "Input name of Product below:");

				String inputQuery = InputUtil.scanner.nextLine();

				Optional<Product> foundProduct = productValues.stream()
						.filter(product -> product.getName().equalsIgnoreCase(inputQuery)).findFirst();// optional

				if (foundProduct.isPresent()) {
					long foundedSKU = foundProduct.get().getSKU();
					System.out.println("SKU for " + inputQuery + ": " + foundedSKU);
				} else {
					throw new ProductNotFoundException("No Products have been found in inventory");
				}

				System.out.println("Press one to select a another product or press another key to exit");
				System.out.println("------------------" + "\n" + "Input below:");
				userInput = InputUtil.scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("You must enter whole numbers only. No decimals, words, or other info. Try again."
						+ "\n" + "Press 1 to product select a another product; or press another key to exit" + "\n"
						+ "Input below:\n" + "-------------------------------");
				InputUtil.scanner.nextLine();
				userInput = InputUtil.scanner.nextInt();
			}

		} while (userInput == 1);

		
	}

	public static void SearchProducts() {
		
		SearchMenu.openSearchMenu();
		
	}

}
