package com.inventory.product_inventory_system.util.stock_management;

import java.util.LinkedHashMap;
import java.util.Map;

import com.inventory.product_inventory_system.model.Product;

public class ListUtil {
	private static Map<Integer, Product> productList = new LinkedHashMap<>();

	public static Map<Integer, Product> getProductList() {
		return productList;
	}

	public static void setProductList(Map<Integer, Product> productList) {
		ListUtil.productList = productList;
	}
	
}
