package models;

public class Buyer extends User {

    // Constructor that calls the User class constructor
    public Buyer(String username, String email, String password, String role) {
        super(username, email, password, role); // Call the User constructor
    }

    // Method for browsing products (functionality can be extended)
    public void browseProducts() {
        System.out.println("Browsing products...");
    }

    // Method for searching a product by name
    public void searchProduct(String productName) {
        System.out.println("Searching for product: " + productName);
    }

    // Method for viewing product details by ID
    public void viewProductDetails(int productId) {
        System.out.println("Viewing details for product ID: " + productId);
    }
}


