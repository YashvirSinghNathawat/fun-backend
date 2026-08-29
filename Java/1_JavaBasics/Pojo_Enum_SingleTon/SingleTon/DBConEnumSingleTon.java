package SingleTon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Enum Singleton for Database Connection Management
 * Thread-safe, serialization-safe, reflection-safe
 */
public enum DBConnection {
    INSTANCE;
    
    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";
    
    /**
     * Constructor - called once when enum is loaded
     * Initializes database connection
     */
    DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("✓ Database connection established");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }
    
    /**
     * Get the singleton database connection
     */
    public Connection getConnection() {
        if (connection != null) {
            try {
                if (connection.isClosed()) {
                    reconnect();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    
    /**
     * Reconnect if connection is closed
     */
    private void reconnect() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("✓ Connection re-established");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Execute SELECT query
     */
    public ResultSet executeQuery(String sql) {
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Execute INSERT, UPDATE, DELETE
     */
    public int executeUpdate(String sql) {
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    /**
     * Close the database connection
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✓ Database connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Check if connection is active
     */
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
