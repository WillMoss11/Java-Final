package models;

import org.mindrot.jbcrypt.BCrypt;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    // Register a new user (buyer, seller, or admin)
    public boolean registerUser(User user) {
        try {
            // Hash the password before saving the user
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);  // Set the hashed password to the user object

            // Save the user to the database via UserDAO
            userDAO.createUser(user);
            return true;  // Successfully saved
        } catch (Exception e) {
            e.printStackTrace();
            return false;  // If any error occurs, return false
        }
    }

    // Login user (compare the entered password with stored hashed password)
    public User loginUser(String username, String password) {
        User user = userDAO.findByUsername(username);  // Retrieve user from the database
    
        if (user != null) {
            System.out.println("Checking password for user: " + username);
            
            // Check if the entered password matches the stored hashed password
            if (BCrypt.checkpw(password, user.getPassword())) {
                System.out.println("Password matched for user: " + username);
    
                // Use the user's role to create the correct subclass object
                switch (user.getRole().toLowerCase()) {
                    case "buyer":
                        return new Buyer(user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
                    case "seller":
                        return new Seller(user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
                    case "admin":
                        return new Admin(user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
                    default:
                        System.out.println("Invalid role.");
                        return null;
                }
            } else {
                System.out.println("Password mismatch for user: " + username);
            }
        } else {
            System.out.println("No user found with username: " + username);
        }
        return null;  // Invalid username or password
    }

    // Get a user by username (used for login and checking credentials)
    public User getUserByUsername(String username) {
        for (User user : userDAO.getAllUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // Return null if no matching user is found
    }

    // List all users (for admin)
    public void listAllUsers() {
        userDAO.getAllUsers().forEach(user -> {
            System.out.println(user.getUsername() + " (" + user.getRole() + ")");
        });
    }

    // Update user details
    public void updateUser(User user) {
        try {
            userDAO.updateUser(user); // Calls the UserDAO to update the user in the database
            System.out.println("User updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to update user.");
        }
    }
}




