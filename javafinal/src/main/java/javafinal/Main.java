package app;

import java.util.Scanner;
import models.User;
import models.UserService;
import models.ProductService;
import models.Buyer;
import models.Seller;
import models.Admin;
import org.mindrot.jbcrypt.BCrypt;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        ProductService productService = new ProductService();

        // Main Menu
        while (true) {
            System.out.println("Welcome to the E-Commerce platform!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1: // Register
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter role (buyer/seller/admin): ");
                    String role = scanner.nextLine();

                    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                    // Create User object based on role
                    User newUser;
                    if (role.equalsIgnoreCase("buyer")) {
                        newUser = new Buyer(username, email, hashedPassword, role);
                    } else if (role.equalsIgnoreCase("seller")) {
                        newUser = new Seller(username, email, hashedPassword, role);
                    } else {
                        newUser = new Admin(username, email, hashedPassword, role);
                    }

                    // Register the user
                    if (userService.registerUser(newUser)) {
                        System.out.println("Registration successful!");
                    } else {
                        System.out.println("Registration failed!");
                    }
                    break;

                case 2: // Login
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();

                    // Login the user
                    User loggedInUser = userService.loginUser(loginUsername, Bcrypt.hashpw(loginPassword));
                    if (loggedInUser != null) {
                        System.out.println("Login successful: " + loggedInUser.getUsername());
                        // Show the role-based menu
                        showRoleMenu(loggedInUser, scanner, userService, productService); // Pass userService
                    } else {
                        System.out.println("Login failed!");
                    }
                    break;

                case 3: // Exit
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    // Show menu based on user role (buyer, seller, admin)
    private static void showRoleMenu(User loggedInUser, Scanner scanner, UserService userService, ProductService productService) {
        if (loggedInUser instanceof Buyer) {
            // Buyer menu
            while (true) {
                System.out.println("\nBuyer Menu:");
                System.out.println("1. Browse products");
                System.out.println("2. Logout");
                System.out.print("Choose an option: ");
                int buyerChoice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (buyerChoice) {
                    case 1:
                        // Browse products
                        productService.listAllProducts();
                        break;

                    case 2:
                        System.out.println("Logging out...");
                        return;

                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            }
        } else if (loggedInUser instanceof Seller) {
            // Seller menu
            Seller seller = (Seller) loggedInUser;
            while (true) {
                System.out.println("\nSeller Menu:");
                System.out.println("1. List all my products");
                System.out.println("2. Add new product");
                System.out.println("3. Update product");
                System.out.println("4. Logout");
                System.out.print("Choose an option: ");
                int sellerChoice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (sellerChoice) {
                    case 1:
                        // List seller's products
                        seller.viewProducts();
                        break;

                    case 2:
                        // Add new product
                        System.out.print("Enter product name: ");
                        String productName = scanner.nextLine();
                        System.out.print("Enter product price: ");
                        double productPrice = scanner.nextDouble();
                        System.out.print("Enter product quantity: ");
                        int productQuantity = scanner.nextInt();
                        scanner.nextLine();  // Consume newline

                        // Corrected: Pass individual parameters to addProduct method
                        productService.addProduct(productName, productPrice, productQuantity, seller.getId());
                        break;

                    case 3:
                        // Update existing product
                        System.out.print("Enter product ID to update: ");
                        int productIdToUpdate = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        System.out.print("Enter new product name: ");
                        String updatedName = scanner.nextLine();
                        System.out.print("Enter new product price: ");
                        double updatedPrice = scanner.nextDouble();
                        System.out.print("Enter new product quantity: ");
                        int updatedQuantity = scanner.nextInt();
                        scanner.nextLine();  // Consume newline

                        seller.updateProduct(productIdToUpdate, updatedName, updatedPrice, updatedQuantity, seller.getId());
                        break;

                    case 4:
                        System.out.println("Logging out...");
                        return;

                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            }
        } else if (loggedInUser instanceof Admin) {
            // Admin menu
            Admin admin = (Admin) loggedInUser;
            while (true) {
                System.out.println("\nAdmin Menu:");
                System.out.println("1. List all products");
                System.out.println("2. List all users");
                System.out.println("3. Logout");
                System.out.print("Choose an option: ");
                int adminChoice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (adminChoice) {
                    case 1:
                        admin.listAllProducts();
                        break;

                    case 2:
                        // List all users (fix userService issue)
                        userService.listAllUsers();
                        break;

                    case 3:
                        System.out.println("Logging out...");
                        return;

                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            }
        }
    }
}






