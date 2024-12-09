package models;

import java.util.List;

public class Admin extends User {
    private ProductService productService = new ProductService();

    public Admin(String username, String email, String password, String role) {
        super(username, email, password, role); // Call the User constructor
    }

    public void listAllProducts() {
        // Fetch the list of all products from the ProductService
        List<Product> products = productService.listAllProducts();
    
        // Check if there are any products
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            // Display the list of products
            System.out.println("Product ID | Name | Price | Quantity | Seller ID");
            for (Product product : products) {
                System.out.println(product.getId() + " | " + product.getName() + " | " 
                    + product.getPrice() + " | " + product.getQuantity() + " | " + product.getSellerId());
            }
        }
    }
}


