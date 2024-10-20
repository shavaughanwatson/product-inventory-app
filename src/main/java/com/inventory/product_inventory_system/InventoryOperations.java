package com.inventory.product_inventory_system;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;

public class InventoryOperations {
	private static Map<Integer, Product> productList = new LinkedHashMap<>();
	private static Scanner scanner; 
	private static int id = 0;
	private static String inputName;
	private static int inputQuantity;
	private static double inputPrice;
	private static String inputQuery;
	private static int userInput;
	
	
	public static void addProduct () {
		
		try {
			
			//initialize scanner inputting product name and quantity
			scanner = new Scanner(System.in);
			System.out.println("Enter name of product");
			inputName = scanner.nextLine();
			
			System.out.println("Enter quantity");
			inputQuantity = scanner.nextInt();
			
			System.out.println("Enter price");
			inputPrice = scanner.nextDouble();
			
			
			Product product = new Product(inputName, inputQuantity, inputPrice);
			//RegisterProduct
			FileWriter myWriter = new FileWriter("productRegistered.txt");
			 String productInfo = product.toString();
			 myWriter.write(productInfo);
			 myWriter.close();
			
			
			getProductList().put(id++, product);
			System.out.println(productList);
			//scanner.close();
			

		      System.out.println("Successfully wrote to the file.");
		      
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 System.out.println("An error occurred.");
			e.printStackTrace();
		}
		

	}
	
	public static void removeProduct( ) {
		
		System.out.println("remove a product from list");
		System.out.println(getProductList());
		//initialize scanner inputting product name and quantity
		scanner = new Scanner(System.in);

		int productIndex = scanner.nextInt();
		getProductList().remove(productIndex);

		System.out.println(productList);
		
	}


	public static void getProduct() {
		
		
		int addedQuantityInput;
		
		
		do  { 
			System.out.println("Select a product");
			System.out.println(productList);
			scanner = new Scanner(System.in);
			
			int indexInput = scanner.nextInt();
			System.out.println(productList.get(indexInput));
		
			System.out.println("Press 1 to product select a another product; Press 2 to add quantity or press another key to exit");
			userInput = scanner.nextInt();
			
			if(userInput == 2) {
				System.out.println(productList.get(indexInput));
				System.out.println("How much would you like to add?");
				addedQuantityInput = scanner.nextInt();
				productList.get(indexInput).setQuantity(productList.get(indexInput).getQuantity() + addedQuantityInput);
				System.out.println("You have successfully added" + addedQuantityInput + productList.get(indexInput).getName());
			}
			
			
			System.out.println("Press 1 to product select a another product; or press another key to exit");
			userInput = scanner.nextInt();
			
		} while (userInput == 1);
		
	}
	

	
	
	public static void editProduct() {
		
	
	
		int indexInput;
		
		do  { 
			System.out.println("Select a product you would like to edit");
			System.out.println(productList);
			scanner = new Scanner(System.in);
			indexInput = scanner.nextInt();
			
			scanner.nextLine();
			System.out.println(productList.get(indexInput));
			System.out.println("Edit Product Name");
			inputName = scanner.nextLine();
			
			productList.get(indexInput).setName(inputName); // get object value and can performed methods on it
			
			System.out.println("Edit Product Price");
			inputPrice = scanner.nextDouble();
			productList.get(indexInput).setPrice(inputPrice);
			
			System.out.println("Edit Product Quantity");
			inputQuantity = scanner.nextInt();
			productList.get(indexInput).setQuantity(inputQuantity);
			System.out.println(productList);
			System.out.println("Press one to select a another product or press another key to exit");
			userInput = scanner.nextInt();
		} while (userInput == 1);
		
	}

	public static Map<Integer, Product> getProductList() {
		return productList;
	}


	public static void SearchProducts() {
		do  { 
			System.out.println("Search for Products 1 to seach");
			scanner = new Scanner(System.in);
			inputQuery = scanner.nextLine();
			
			System.out.println(InventoryOperations.getProductList()
																.entrySet()
																.stream()
																.filter( entry -> entry.getValue().getName().contains(inputQuery) || entry.getValue().getName().contains(inputQuery) )
																.toList().toString());			
			System.out.println("Press one to select a another product or press another key to exit");
			userInput = scanner.nextInt();
		} while (userInput == 1);
		
	}

	
	public static void SearchSKU() {
		do  { 
			System.out.println("Search for SKU 1 to seach");
			scanner = new Scanner(System.in);
			List<Product> productValues = new ArrayList<>(productList.values());
			System.out.println(productValues);
			
			inputQuery = scanner.nextLine();
			
		   

			  Optional<Product> foundProduct = productValues.stream().filter(product -> product.getName().equalsIgnoreCase(inputQuery)).findFirst();// optional
			
			  if(foundProduct.isPresent()) {
				  long foundedSKU = foundProduct.get().getSKU();
				  System.out.println("SKU for " + inputQuery + ": " + foundedSKU);
			  } else {
		            System.out.println("Product not found.");
		        }
			
		             
		
		
			System.out.println("Press one to select a another product or press another key to exit");
			userInput = scanner.nextInt();
		} while (userInput == 1);
		
	}

	
}
