package database;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DBConnection {
 
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres"; // Replace with your database URL
    private static final String USER = "postgres"; // Replace with your database username
    private static final String PASSWORD = "LucWill53"; // Replace with your database password
 
    // Declare the connection as a static variable to maintain a constant connection
    private static Connection connection;
 
    // Get the database connection (make sure it's persistent)
    public static Connection getConnection() {
        try {
            // If connection is null or closed, open a new one
            if (connection == null || connection.isClosed()) {
                // Load the PostgreSQL driver
                Class.forName("org.postgresql.Driver");
 
                // Create a new connection and keep it open
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
 
    // Close the connection safely (only when you're completely done with it)
    public static void closeConnection() {
        if (connection != null) {
            try {
                // Only close the connection if it's open
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}