package models;

import java.util.List;

public class ProductService {

    private ProductDAO productDAO;

    // Constructor
    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    // Add a new product
    public void addProduct(String name, double price, int quantity, int sellerId) {
        Product product = new Product(name, price, quantity, sellerId);
        productDAO.addProduct(product);
    }

    // View all products (Admin or Buyer)
    public List<Product> viewAllProducts() {
        return productDAO.getAllProducts();
    }

    // View products by a specific seller (Seller)
    public List<Product> viewSellerProducts(int sellerId) {
        return productDAO.getProductsBySellerId(sellerId);
    }

    // Update an existing product
    public void updateProduct(int productId, String name, double price, int quantity, int sellerId) {
        Product product = new Product(productId, name, price, quantity, sellerId); // now includes sellerId
        productDAO.updateProduct(product);
    }
    
    // Delete a product by ID (Seller)
    public void deleteProduct(int productId) {
        productDAO.deleteProduct(productId);
    }

    public List<Product> listAllProducts() {
        return productDAO.getAllProducts(); // Calls the DAO method to get all products from the database
    }
}
