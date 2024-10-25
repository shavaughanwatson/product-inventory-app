package com.inventory.product_inventory_system.model;


import java.time.LocalDate;

public class Product {
	private String name;
	private int quantity;
	private double price;
	private int sku;
	private int sold;
	private double revenue;
	private LocalDate date;
	private String category;
	
	
	
	public Product(String name, int quantity, double price, String category, LocalDate date ) {
		//need id property
		
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.category = category;
		this.sku = (int) (Math.random() * 10000);
		this.sold = 0;
		this.revenue = sold * price;
		this.date = date;
	}
	

	public String getName() {
		return this.name;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public int getSKU() {
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
		String productInfo = "Name:" + name + " Quantity:" + quantity +  " Price:" + price +  " Sold:" + sold +  " Revenue:" + revenue;
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


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	


	




}
