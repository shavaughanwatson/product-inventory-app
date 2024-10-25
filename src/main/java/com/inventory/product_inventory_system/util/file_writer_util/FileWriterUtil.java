package com.inventory.product_inventory_system.util.file_writer_util;

import java.io.FileWriter;
import java.io.IOException;


public class FileWriterUtil {
	
	public static void fileWrite(String textFileName, String textFileContent) { 
		
		try {
			
			//repeated code - make into method or own class/interface
			FileWriter myWriter = new FileWriter(textFileName);
			 myWriter.write(textFileContent); //make report more into a table with java string
			 myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		      
		} catch (IOException e) {
		
			 System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}
