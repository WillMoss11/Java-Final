package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import database.DBConnection;

public class ProductService {

    private ProductDAO productDAO;

    // Constructor
    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    // Add a new product
    public void addProduct(String name, double price, int quantity, int sellerId) {
        if (sellerId == 0) {
            System.out.println("Error: Invalid seller ID.");
            return; // Prevent adding product if seller ID is invalid
        }
    
        // Add product to the database
        productDAO.addProduct(name, price, quantity, sellerId);
    }
    
    // View all products (Admin or Buyer)
    public List<Product> viewAllProducts() {
        return productDAO.getAllProducts();
    }

    // View products by a specific seller (Seller)
    public List<Product> viewSellerProducts(int sellerId) {
        return productDAO.getProductsBySellerId(sellerId);
    }

    // Update an existing product and return a boolean indicating success or failure
    public boolean updateProduct(int productId, String name, double price, int quantity, int sellerId) {
        Product product = new Product(productId, name, price, quantity, sellerId); // now includes sellerId
        return productDAO.updateProduct(product); // Return the result of the update
    }

 public boolean deleteProduct(int productId) {
        String query = "DELETE FROM products WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, productId);
            int rowsAffected = stmt.executeUpdate();

            // Return true if a product was deleted
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if something went wrong
    }

    public List<Product> listAllProducts() {
        return productDAO.getAllProducts(); // Calls the DAO method to get all products from the database
    }
}