package com.inventory.product_inventory_system;

public class Product {
	private String name;
	private int quantity;
	private long sku;
	
	public Product(String name, int quantity ) {
		this.name = name;
		this.quantity = quantity;
		this.sku = (int) Math.random() * 1000;
	}
	

	public String getName() {
		return this.name;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public long getSKU() {
		return this.sku;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setQuantity( int quantity) {
		this.quantity = quantity;
	}
	
	public String toString() {
		String productInfo = "Name:" + name + " Quantity:" + quantity + " SKU:" + sku;
		return productInfo;
	}

}
