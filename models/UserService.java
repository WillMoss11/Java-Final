package models;


public class UserService {

    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    // Register a new user (buyer, seller, or admin)
    public boolean registerUser(User user) {
        try {
            userDAO.createUser(user); // Calls the UserDAO to save the user
            return true; // Successfully saved
        } catch (Exception e) {
            e.printStackTrace();
            return false; // If any error occurs, return false
        }
    }

    // Login a user (verify username and password)
    public User loginUser(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user; // Successful login
        }
        return null; // Invalid username or password
    }

    // Get a user by username (used for login and checking credentials)
    public User getUserByUsername(String username) {
        // In this case, we should iterate over all users and find the one with matching username
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
}


