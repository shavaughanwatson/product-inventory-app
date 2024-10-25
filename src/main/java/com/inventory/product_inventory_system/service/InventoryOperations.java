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
import com.inventory.product_inventory_system.util.error_handling.NoProductsInInventoryException;
import com.inventory.product_inventory_system.util.error_handling.ProductNotFoundException;
import com.inventory.product_inventory_system.util.input_util.InputUtil;
import com.inventory.product_inventory_system.util.stock_management.ListUtil;

public class InventoryOperations {

	public static void addProduct(Scanner scanner) {

		do {
			try {

				String productName = InputUtil.getInputName("Enter name of product");

				int productQuanity = InputUtil.getInputQuantity("Enter quantity");

				double productPrice = InputUtil.getInputPrice("Enter price");

				String productCategory = InputUtil
						.getCategoryInput("Enter Category: Press 0 for FOOD; Press 1 for MERCHANDISE");

				LocalDate productExpiryDate = InputUtil
						.getDateInput("Enter date in following format" + "\n" + "dd-MM-yyyy");

				Product product = new Product(productName, productQuanity, productPrice, productCategory,
						productExpiryDate);

				// RegisterProduct
				PrintingReports.printingProductRegistered(product);

				ListUtil.getProductList().put(product.getSKU(), product);

			} catch (InputMismatchException e) {

				System.out.println("You must enter whole numbers only. No decimals, words, or other info. Try again."
						+ "\n" + "Press 1 to product select a another product; or press another key to exit" + "\n"
						+ "Input below:\n" + "-------------------------------");

			} catch (ArrayIndexOutOfBoundsException e) {

				System.out.println("Invalid Option Please try again");
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date format. Please use dd-MM-yyyy.");
			}
		} while (InputUtil.getUserInput() == 1);

	}

	public static void removeProduct(Scanner scanner) throws NoProductsInInventoryException, ProductNotFoundException {
		if (ListUtil.getProductList().size() == 0) {
			throw new NoProductsInInventoryException(
					"No Products have been found." + "\n" + "Please insert new Products");
		}

		do {

			try {

				int productIndex = InputUtil.getProductIndex(PrintingReports.printCurrentInventory() + "\n"
						+ "Select Product SKU to remove Product from list" + "\n" + "------------------" + "\n"
						+ "Input Product ID below:");

				if (!ListUtil.getProductList().containsKey(productIndex)) {
					throw new ProductNotFoundException("No Products have been found in inventory");
				}

				Product removedProduct = ListUtil.getProductList().remove(productIndex);

				System.out.println("Product has been successfully removed" + "\n" + "------------------" + "\n"
						+ "Product Removed" + "\n" + "Product Name: " + removedProduct.getName() + "\n" + "Price: "
						+ removedProduct.getPrice() + "\n" + " Quantity: " + removedProduct.getQuantity());
				
				System.out.println("-------------------------------" + "\n"
						+ "Press 1 to product select a another product; or press another key to exit" + "\n"
						+ "Input below:");
				userInput = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("You must enter whole numbers only. No decimals, words, or other info. Try again."
						+ "\n" + "Press 1 to product select a another product; or press another key to exit" + "\n"
						+ "Input below:\n" + "-------------------------------");
				scanner.nextLine();
				userInput = scanner.nextInt();

			}

		} while (userInput == 1);
	}

	public static void getProduct(Scanner scanner) throws NoProductsInInventoryException, ProductNotFoundException {
		if (ListUtil.getProductList().size() == 0) {
			throw new NoProductsInInventoryException(
					"No Products have been found." + "\n" + "Please insert new Products");
		}

		int addedQuantityInput;

		do {

			try {
				System.out.println("Select Product SKU to view from list");
				System.out.println(PrintingReports.printCurrentInventory());
				System.out.println("------------------" + "\n" + "Input Product ID below:");

				int indexInput = InputUtil.getUserInput();

				if (!ListUtil.getProductList().containsKey(indexInput)) {
					throw new ProductNotFoundException("No Products have been found in inventory");
				}

				System.out.println(" Edit Product Menu\n" + "-------------------------------\n" + "Product Info: \n"
						+ ListUtil.getProductList().get(indexInput).getSKU() + "\n"
						+ ListUtil.getProductList().get(indexInput).getName() + "\n"
						+ ListUtil.getProductList().get(indexInput).getPrice() + "\n"
						+ ListUtil.getProductList().get(indexInput).getQuantity());

				System.out.println("Press 2 to add quantity or press another key to exit menu");
				InputUtil.getUserInput();

				if (InputUtil.getUserInput() == 2) {
					System.out.println("Update Product Quantity\n" + "-------------------------------\n"
							+ "Product Info: \n" + ListUtil.getProductList().get(indexInput).getSKU() + "\n"
							+ ListUtil.getProductList().get(indexInput).getName() + "\n"
							+ ListUtil.getProductList().get(indexInput).getPrice() + "\n"
							+ ListUtil.getProductList().get(indexInput).getQuantity());

					System.out.println("How much would you like to add?");
					addedQuantityInput = scanner.nextInt();
					int updatedQuantity = ListUtil.getProductList().get(indexInput).getQuantity() + addedQuantityInput;
					ListUtil.getProductList().get(indexInput).setQuantity(updatedQuantity);

					System.out.println("Quantity has been added successfully!\n" + "-------------------------------\n"
							+ "Product update: \n" + ListUtil.getProductList().get(indexInput).getSKU() + "\n"
							+ ListUtil.getProductList().get(indexInput).getName() + "\n"
							+ ListUtil.getProductList().get(indexInput).getPrice() + "\n"
							+ ListUtil.getProductList().get(indexInput).getQuantity() + "(" + "+" + addedQuantityInput
							+ ")");
				}

			
				InputUtil.getUserInput("-------------------------------" + "\n"
						+ "Press 1 to product select a another product; or press another key to exit" + "\n"
						+ "Input below:");
			} catch (InputMismatchException e) {
				System.out.println("You must enter whole numbers only. No decimals, words, or other info. Try again."
						+ "\n" + "Press 1 to product select a another product; or press another key to exit" + "\n"
						+ "Input below:\n" + "-------------------------------");
				scanner.nextLine();
				userInput = scanner.nextInt();

			}

		} while (userInput == 1);

	}

	public static void editProduct(Scanner scanner) throws NoProductsInInventoryException, ProductNotFoundException {

		// make error custom exception if no product is in the array
		if (ListUtil.getProductList().size() == 0) {
			throw new NoProductsInInventoryException(
					"No Products have been found." + "\n" + "Please insert new Products");
		}

		do {

			try {
				System.out.println("Select Product SKU to Edit Product from list");
				System.out.println(PrintingReports.printCurrentInventory());
				System.out.println("------------------" + "\n" + "Input Product ID below:");

				indexInput = scanner.nextInt();
				if (!ListUtil.getProductList().containsKey(indexInput)) {
					throw new ProductNotFoundException("No Products have been found in inventory");
				}
				scanner.nextLine();

				System.out.println(ListUtil.getProductList().get(indexInput));
				System.out.println("Edit Product Name");
				System.out.println("------------------" + "\n" + "Input Product Name below:");
				inputName = scanner.nextLine();

				ListUtil.getProductList().get(indexInput).setName(inputName); // get object value and can performed
																				// methods on it

				System.out.println("Edit Product Price");
				System.out.println("------------------" + "\n" + "Input Product Price below:");

				inputPrice = scanner.nextDouble();
				ListUtil.getProductList().get(indexInput).setPrice(inputPrice);

				System.out.println("Product Info has been updated!\n" + "-------------------------------\n"
						+ "Product update: \n" + ListUtil.getProductList().get(indexInput).getSKU() + "\n"
						+ ListUtil.getProductList().get(indexInput).getName() + "\n"
						+ ListUtil.getProductList().get(indexInput).getPrice() + "\n"
						+ ListUtil.getProductList().get(indexInput).getQuantity());
				System.out.println("Press one to select a another product or press another key to exit");
				System.out.println("------------------" + "\n" + "Input below:");
				userInput = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("You must enter whole numbers only. No decimals, words, or other info. Try again."
						+ "\n" + "Press 1 to product select a another product; or press another key to exit" + "\n"
						+ "Input below:\n" + "-------------------------------");
				scanner.nextLine();
				userInput = scanner.nextInt();
			}

		} while (userInput == 1);

	}

	public static void SearchProducts(Scanner scanner) throws NoProductsInInventoryException {
		// make error custom exception if no product is in the array
		if (ListUtil.getProductList().size() == 0) {
			throw new NoProductsInInventoryException(
					"No Products have been found." + "\n" + "Please insert new Products");
		}
		do {

			try {
				System.out.println("Search for by name");
				System.out.println("------------------" + "\n" + "Input Query below:");
				scanner = new Scanner(System.in);
				inputQuery = scanner.nextLine();

				System.out
						.println(
								ListUtil.getProductList().entrySet().stream()
										.filter(entry -> entry.getValue().getName().contains(inputQuery)
												|| entry.getValue().getName().contains(inputQuery))
										.toList().toString());

				System.out.println("Press one to select a another product or press another key to exit");
				System.out.println("------------------" + "\n" + "Input below:");
				userInput = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("You must enter whole numbers only. No decimals, words, or other info. Try again."
						+ "\n" + "Press 1 to product select a another product; or press another key to exit" + "\n"
						+ "Input below:\n" + "-------------------------------");
				scanner.nextLine();
				userInput = scanner.nextInt();
			}

		} while (userInput == 1);

	}

	public static void SearchByCategorieProducts(Scanner scanner) throws NoProductsInInventoryException {
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
				userInput = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("You must enter whole numbers only. No decimals, words, or other info. Try again."
						+ "\n" + "Press 1 to product select a another product; or press another key to exit" + "\n"
						+ "Input below:\n" + "-------------------------------");
				scanner.nextLine();
				userInput = scanner.nextInt();
			}

		} while (userInput == 1);

	}

	public static void SearchSKU(Scanner scanner) throws NoProductsInInventoryException, ProductNotFoundException {
		if (ListUtil.getProductList().size() == 0) {
			throw new NoProductsInInventoryException(
					"No Products have been found." + "\n" + "Please insert new Products");
		}

		do {
			try {
				List<Product> productValues = new ArrayList<>(ListUtil.getProductList().values());

				System.out.println("Search for SKU 1 to search");
				System.out.println("------------------" + "\n" + "Input name of Product below:");

				inputQuery = scanner.nextLine();

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
				userInput = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("You must enter whole numbers only. No decimals, words, or other info. Try again."
						+ "\n" + "Press 1 to product select a another product; or press another key to exit" + "\n"
						+ "Input below:\n" + "-------------------------------");
				scanner.nextLine();
				userInput = scanner.nextInt();
			}

		} while (userInput == 1);

	}

}
