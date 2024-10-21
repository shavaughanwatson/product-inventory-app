package com.inventory.product_inventory_system;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class InventoryOperations {
	private static Map<Integer, Product> productList = new LinkedHashMap<>();
	private static Scanner scanner;

	// this will be it's own class
	private static String inputName;
	private static int inputQuantity;
	private static double inputPrice;
	private static String inputQuery;
	private static int userInput;

	public static void addProduct()  {

		do {
			try {
				
			
				// initialize scanner inputting product name and quantity
				scanner = new Scanner(System.in);
				System.out.println("Enter name of product");
				inputName = scanner.nextLine();

				System.out.println("Enter quantity"); // will have to make a limit of how much products can be inputted-
				inputQuantity = scanner.nextInt();

				System.out.println("Enter price");
				inputPrice = scanner.nextDouble();

				Product product = new Product(inputName, inputQuantity, inputPrice);
				
				// RegisterProduct
				PrintingReports.printingProductRegistered(product);

				getProductList().put(product.getSKU(), product);
				
		

			} catch (InputMismatchException e ) {
				System.out.println("You must enter whole numbers only. No decimals, words, or other info. Try again."
						+ "\n" + "Press 1 to product select a another product; or press another key to exit" + "\n"
						+ "Input below:\n" + "-------------------------------");
				scanner.nextLine();
				userInput = scanner.nextInt();
			}
		} while (userInput == 1);

	}

	public static void removeProduct() throws NoProductsInInventoryException, ProductNotFoundException {
		if( productList.size() == 0 ) {
			throw new NoProductsInInventoryException("No Products have been found." + "\n" + "Please insert new Products");
		}

		do {

			try {
				// make error custom exception if no product is in the array
				// get access to product in inventory array
				
			

				System.out.println("Select Product SKU to remove Product from list");
				System.out.println(PrintingReports.printCurrentInventory());
				System.out.println("------------------" + "\n" + "Input Product ID below:");
				// initialize scanner inputting product name and quantity

				scanner = new Scanner(System.in);

				int productIndex = scanner.nextInt();
				
				if(!productList.containsKey(productIndex)) {
					throw new ProductNotFoundException("No Products have been found in inventory");
				}
				
				Product removedProduct = getProductList().remove(productIndex);

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

	public static void getProduct() throws NoProductsInInventoryException, ProductNotFoundException {
		if( productList.size() == 0 ) {
			throw new NoProductsInInventoryException("No Products have been found." + "\n" + "Please insert new Products");
		}
		

		int addedQuantityInput;

		do {

			try {
				System.out.println("Select Product SKU to view from list");
				System.out.println(PrintingReports.printCurrentInventory());
				System.out.println("------------------" + "\n" + "Input Product ID below:");

				scanner = new Scanner(System.in);
				int indexInput = scanner.nextInt();
				
				if(!productList.containsKey(indexInput)) {
					throw new ProductNotFoundException("No Products have been found in inventory");
				}
				
				System.out.println(" Edit Product Menu\n" + "-------------------------------\n" + "Product Info: \n"
						+ productList.get(indexInput).getSKU() + "\n" + productList.get(indexInput).getName() + "\n"
						+ productList.get(indexInput).getPrice() + "\n" + productList.get(indexInput).getQuantity());

				System.out.println("Press 2 to add quantity or press another key to exit menu");
				userInput = scanner.nextInt();

				if (userInput == 2) {
					System.out.println("Update Product Quantity\n" + "-------------------------------\n"
							+ "Product Info: \n" + productList.get(indexInput).getSKU() + "\n"
							+ productList.get(indexInput).getName() + "\n" + productList.get(indexInput).getPrice()
							+ "\n" + productList.get(indexInput).getQuantity());
					System.out.println("How much would you like to add?");
					addedQuantityInput = scanner.nextInt();
					int updatedQuantity = productList.get(indexInput).getQuantity() + addedQuantityInput;
					productList.get(indexInput).setQuantity(updatedQuantity);

					System.out.println("Quantity has been added successfully!\n" + "-------------------------------\n"
							+ "Product update: \n" + productList.get(indexInput).getSKU() + "\n"
							+ productList.get(indexInput).getName() + "\n" + productList.get(indexInput).getPrice()
							+ "\n" + productList.get(indexInput).getQuantity() + "(" + "+" + addedQuantityInput + ")");
				}

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

	public static void editProduct() throws NoProductsInInventoryException, ProductNotFoundException {

		// make error custom exception if no product is in the array
		if( productList.size() == 0 ) {
			throw new NoProductsInInventoryException("No Products have been found." + "\n" + "Please insert new Products");
		}

		int indexInput;

		do {

			try {
				System.out.println("Select Product SKU to Edit Product from list");
				System.out.println(PrintingReports.printCurrentInventory());
				System.out.println("------------------" + "\n" + "Input Product ID below:");

				scanner = new Scanner(System.in);
				indexInput = scanner.nextInt();
				if(!productList.containsKey(indexInput)) {
					throw new ProductNotFoundException("No Products have been found in inventory");
				}
				scanner.nextLine();
		
				System.out.println(productList.get(indexInput));
				System.out.println("Edit Product Name");
				System.out.println("------------------" + "\n" + "Input Product Name below:");
				inputName = scanner.nextLine();

				productList.get(indexInput).setName(inputName); // get object value and can performed methods on it

				System.out.println("Edit Product Price");
				System.out.println("------------------" + "\n" + "Input Product Price below:");

				inputPrice = scanner.nextDouble();
				productList.get(indexInput).setPrice(inputPrice);

				System.out.println("Product Info has been updated!\n" + "-------------------------------\n"
						+ "Product update: \n" + productList.get(indexInput).getSKU() + "\n"
						+ productList.get(indexInput).getName() + "\n" + productList.get(indexInput).getPrice() + "\n"
						+ productList.get(indexInput).getQuantity());
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

	public static Map<Integer, Product> getProductList() {
		return productList;
	}

	public static void SearchProducts() throws NoProductsInInventoryException {
		// make error custom exception if no product is in the array
		if( productList.size() == 0 ) {
			throw new NoProductsInInventoryException("No Products have been found." + "\n" + "Please insert new Products");
		}
		do {

			try {
				System.out.println("Search for by name");
				System.out.println("------------------" + "\n" + "Input Query below:");
				scanner = new Scanner(System.in);
				inputQuery = scanner.nextLine();

				System.out
						.println(
								InventoryOperations.getProductList().entrySet().stream()
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

	public static void SearchSKU() throws NoProductsInInventoryException, ProductNotFoundException {
		if( productList.size() == 0 ) {
			throw new NoProductsInInventoryException("No Products have been found." + "\n" + "Please insert new Products");
		}
		
		do {
			try {
				List<Product> productValues = new ArrayList<>(productList.values());

				System.out.println("Search for SKU 1 to search");
				System.out.println("------------------" + "\n" + "Input name of Product below:");
				scanner = new Scanner(System.in);
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
