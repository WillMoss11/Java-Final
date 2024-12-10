package models;

public class Buyer extends User {

    public Buyer(String username, String email, String password, String role) {
        super(username, email, password, role); // Call the User constructor
    }

    public void browseProducts() {
        System.out.println("Browsing products...");
    }

    public void searchProduct(String productName) {
        System.out.println("Searching for product: " + productName);
    }

    public void viewProductDetails(int productId) {
        System.out.println("Viewing details for product ID: " + productId);
    }
}

