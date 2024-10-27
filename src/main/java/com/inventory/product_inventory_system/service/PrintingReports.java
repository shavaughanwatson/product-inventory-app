package com.inventory.product_inventory_system.service;




import com.inventory.product_inventory_system.util.error_handling.NoProductsInInventoryException;
import com.inventory.product_inventory_system.util.file_writer_util.FileWriterUtil;
import com.inventory.product_inventory_system.util.print_util.PrintingUtil;
import com.inventory.product_inventory_system.util.stock_management.ListUtil;

public class PrintingReports {

	public static void printingGeneralReports() throws NoProductsInInventoryException {

		// make error custom exception if no product is in the array
		if (ListUtil.getProductList().size() == 0) {
			throw new NoProductsInInventoryException(
					"No Products have been found." + "\n" + "Please insert new Products");
		}

		FileWriterUtil.fileWrite("product-data.txt", PrintingUtil.printCurrentInventory());

	}

	
	


}
