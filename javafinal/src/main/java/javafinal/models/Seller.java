package models;

public class Seller extends User {
    private ProductService productService = new ProductService();

    public Seller(String username, String email, String password, String role) {
        super(username, email, password, role); // Call the User constructor
    }

    // Add a product
    public void addProduct(String name, double price, int quantity) {
        int sellerId = this.getId(); // Ensure this is the seller's ID (already set upon login)
        if (sellerId == 0) {
            System.out.println("Error: Seller ID is not valid.");
            return;
        }
        productService.addProduct(name, price, quantity, sellerId);
    }
    
    // View all products
    public void viewProducts() {
        productService.viewSellerProducts(this.getId());
    }

    // Update a product
    public void updateProduct(int productId, String name, double price, int quantity, int sellerId) {
        boolean success = productService.updateProduct(productId, name, price, quantity, sellerId);
        if (success) {
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Failed to update the product.");
        }
    }

    // Delete a product
    public void deleteProduct(int productId) {
        boolean success = productService.deleteProduct(productId);  // This now returns a boolean
        if (success) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Failed to delete the product.");
        }
    }
}



