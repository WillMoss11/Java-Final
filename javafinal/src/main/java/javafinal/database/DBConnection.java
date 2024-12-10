package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/your_database_name"; // Replace with your database URL
    private static final String USER = "your_db_username"; // Replace with your database username
    private static final String PASSWORD = "your_db_password"; // Replace with your database password

    private static Connection connection;

    // Get the database connection
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load the PostgreSQL driver
                Class.forName("org.postgresql.Driver");

                // Create the connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
