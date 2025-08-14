package PROJECT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/airline_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Password";   //Replace with your password

    private static Connection connection = null;

    // Private constructor to prevent instantiation
    private DatabaseConnection() {}

    // Method to get database connection
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Force console output to flush immediately
                System.out.println("Attempting to connect to database...");
                System.out.flush();

                // Establish connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                // Verify connection is valid
                if (connection != null && !connection.isClosed()) {
                    System.out.println("DATABASE CONNECTION SUCCESSFUL!");
                    System.out.flush();
                } else {
                    System.err.println("CONNECTION FAILED: Connection object is null or closed");
                    System.err.flush();
                }

            } catch (ClassNotFoundException e) {
                System.err.println("DATABASE CONNECTION FAILED: MySQL JDBC Driver not found.");
                System.err.flush();
            } catch (SQLException e) {
                System.err.println("DATABASE CONNECTION FAILED: " + e.getMessage());
                System.err.flush();
            }
        } else {
            try {
                // Check if existing connection is still valid
                if (connection.isClosed()) {
                    System.out.println("Connection was closed, creating new connection...");
                    System.out.flush();
                    connection = null;
                    return getConnection(); // Recursive call to create new connection
                }
            } catch (SQLException e) {
                System.err.println("Database connection error: " + e.getMessage());
                System.err.flush();
                connection = null;
                return getConnection(); // Try to create new connection
            }
        }
        return connection;
    }

    // Method to close database connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println("Database connection closed successfully.");
                    System.out.flush();
                }
                connection = null;
            } catch (SQLException e) {
                System.err.println("Error while closing connection: " + e.getMessage());
                System.err.flush();
            }
        }
    }

    // Method to execute SELECT queries
    public static ResultSet executeQuery(String query, Object... params) throws SQLException {
        Connection conn = getConnection();
        if (conn == null) {
            throw new SQLException("Unable to establish database connection");
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            // Set parameters
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            return stmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
            System.err.flush();
            throw e;
        }
    }

    // Method to execute INSERT, UPDATE, DELETE queries
    public static int executeUpdate(String query, Object... params) throws SQLException {
        Connection conn = getConnection();
        if (conn == null) {
            throw new SQLException("Unable to establish database connection");
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            // Set parameters
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            return stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error executing update: " + e.getMessage());
            System.err.flush();
            throw e;
        }
    }

    // Method to test database connection
    public static boolean testConnection() {
        try {
            Connection conn = getConnection();
            if (conn != null && !conn.isClosed()) {
                // Test with a simple query
                ResultSet rs = executeQuery("SELECT 1 as test");
                if (rs.next()) {
                    System.out.println("Connection test PASSED!");
                    System.out.flush();
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Connection test FAILED: " + e.getMessage());
            System.err.flush();
        }

        System.err.println("Connection test FAILED!");
        System.err.flush();
        return false;
    }

    // Method to check if database exists
    public static boolean checkDatabaseExists() {
        try {
            // Connect to MySQL server without specifying database
            String serverUrl = "jdbc:mysql://localhost:3306/?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            Connection serverConn = DriverManager.getConnection(serverUrl, USER, PASSWORD);

            PreparedStatement stmt = serverConn.prepareStatement("SHOW DATABASES LIKE 'airline_db'");
            ResultSet rs = stmt.executeQuery();

            boolean exists = rs.next();
            serverConn.close();

            if (exists) {
                System.out.println("Database 'airline_db' exists!");
            } else {
                System.out.println("Database 'airline_db' does NOT exist!");
            }
            System.out.flush();

            return exists;

        } catch (SQLException e) {
            System.err.println("Error checking database: " + e.getMessage());
            System.err.flush();
            return false;
        }
    }
}
