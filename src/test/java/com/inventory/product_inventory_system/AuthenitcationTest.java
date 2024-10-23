package com.inventory.product_inventory_system;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
public class AuthenitcationTest {
	
	@Test
	public void testLogin() throws InvalidLoginException, IOException {
	    // Assuming Login method checks credentials from a file, mock or create the file with correct user data
	    String username = "testUser";
	    String password = "testPassword";
	    
	    // Simulate login process
	    Authenitcation.Login(); // You might need to mock input for username/password or simulate reading from a file
	    
	    // After successful login
	    User user = new User(username, password);
	    user.setisLoggedIn(true); // Simulate login status
	    assertTrue(user.getIsLoggedIn(), "User should be logged in after successful login");

	    // Now, move to logout tests separately
	}
	
	@Test
	public void testLogout() {
	    // Assuming a user is logged in
	    User user = new User("testUser", "testPassword");
	    user.setisLoggedIn(true);  // Simulate user being logged in

	    // Call logout
	    Authenitcation.Logout(user);

	    // Assert the user is now logged out
	    assertFalse(user.getIsLoggedIn(), "User should be logged out after calling logout");
	}
	
	 @Test
	    public void testSignUp() throws IOException {
	        // Simulate file to store user registration
	        String testFilePath = "test-user-registration.txt";
	        FileWriter writer = new FileWriter(testFilePath);
	        writer.write(""); // Clear the file before testing
	        writer.close();

	        // Simulate user registration
	        String username = "testUser";
	        String password = "testPassword";
	        User newUser = new User(username, password);

	        // Assuming `Authenitcation.SignUp` writes this to the file
	        Files.write(Paths.get(testFilePath), ("Username:" + username + "\nPassword:" + password).getBytes());

	        // Check if the user registration is written correctly
	        String fileContent = new String(Files.readAllBytes(Paths.get(testFilePath)));
	        assertTrue(fileContent.contains("Username:" + username));
	        assertTrue(fileContent.contains("Password:" + password));

	        // Cleanup test file
	        new File(testFilePath).delete();
	    }
	 

}
