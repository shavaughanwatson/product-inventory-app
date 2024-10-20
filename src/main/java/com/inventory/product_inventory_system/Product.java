package com.inventory.product_inventory_system;

public class Product {
	private String name;
	private int quantity;
	private double price;
	private long sku;
	private int sold;
	private double revenue;
	
	
	public Product(String name, int quantity, double price ) {
		this.name = name;
		this.quantity = quantity;
		this.setPrice(price);
		this.sku = (long) (Math.random() * 10000);
		this.sold = 0;
		this.revenue = sold * price;
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
	
	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString() {
		String productInfo = "Name:" + name + " Quantity:" + quantity + " Price:" + price + " SKU:" + sku + " Sold:" + sold + " Revenue:" + revenue;
		return productInfo;
	}


	public int getSold() {
		return sold;
	}



	public void setSold(int sold) {
		this.sold = sold;
	}


	public double getRevenue() {
		return revenue;
	}


	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}




}
