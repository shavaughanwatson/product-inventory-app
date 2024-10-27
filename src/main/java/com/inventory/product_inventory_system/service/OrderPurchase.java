package com.inventory.product_inventory_system.service;




import com.inventory.product_inventory_system.model.Product;
import com.inventory.product_inventory_system.util.error_handling.NoProductsInInventoryException;
import com.inventory.product_inventory_system.util.error_handling.ProductNotFoundException;
import com.inventory.product_inventory_system.util.input_util.InputUtil;

import com.inventory.product_inventory_system.util.stock_management.ListUtil;

public class OrderPurchase {
	
  
	public static void  purchaseProduct() throws NoProductsInInventoryException,  ProductNotFoundException {
		if( ListUtil.getProductList().isEmpty()) {
			throw new NoProductsInInventoryException("No Products have been found." + "\n" + "Please insert new Products");
		}
		
		int selectedProductSKU = InputUtil.selectedProductSKU();
		
		if (!ListUtil.getProductList().containsKey(selectedProductSKU )) {
			throw new ProductNotFoundException(
					"No Products have been found." );
		}

        Product  selectedProduct = ListUtil.getProductList().get(selectedProductSKU);
        InputUtil.inputPurchasedProduct(selectedProduct);
        

	}
}
