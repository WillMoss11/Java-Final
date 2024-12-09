package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String URL = "jdbc:postgresql://localhost:5432/ecommerce";  // Your DB URL
    private static final String USER = "postgres";  // Your DB username
    private static final String PASSWORD = "password";  // Your DB password

    // Establish and return a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

