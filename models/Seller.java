package models;

public class Seller extends User {
    private ProductService productService = new ProductService();

    public Seller(String username, String email, String password, String role) {
        super(username, email, password, role); // Call the User constructor
    }

    // Add a product
    public void addProduct(String name, double price, int quantity) {
        productService.addProduct(name, price, quantity, this.getId());
    }

    // View all products
    public void viewProducts() {
        productService.viewSellerProducts(this.getId());
    }

    // Update a product
    public void updateProduct(int productId, String name, double price, int quantity, int sellerId) {
        // Pass sellerId along with other parameters
        productService.updateProduct(productId, name, price, quantity, sellerId);
    }
    
    // Delete a product
    public void deleteProduct(int productId) {
        productService.deleteProduct(productId);
    }
}


