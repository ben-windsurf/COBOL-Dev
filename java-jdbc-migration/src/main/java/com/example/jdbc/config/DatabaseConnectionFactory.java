package com.example.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionFactory {
    
    private static final String DEFAULT_SERVER = "localhost";
    private static final int DEFAULT_PORT = 5432;
    private static final String DEFAULT_DATABASE = "cobol_db_example";
    private static final String DEFAULT_USERNAME = "postgres";
    
    private static String getDefaultPassword() {
        String password = System.getenv("DB_PASSWORD");
        if (password == null || password.trim().isEmpty()) {
            password = System.getProperty("db.password");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalStateException("Database password must be provided via DB_PASSWORD environment variable or db.password system property");
        }
        return password;
    }
    
    private final String server;
    private final int port;
    private final String database;
    private final String username;
    private final String password;
    
    public DatabaseConnectionFactory() {
        this(DEFAULT_SERVER, DEFAULT_PORT, DEFAULT_DATABASE, DEFAULT_USERNAME, getDefaultPassword());
    }
    
    public DatabaseConnectionFactory(String server, int port, String database, String username, String password) {
        this.server = server;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }
    
    public Connection createConnection() throws SQLException {
        String url = String.format("jdbc:postgresql://%s:%d/%s", server, port, database);
        
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        
        try {
            Connection connection = DriverManager.getConnection(url, props);
            connection.setAutoCommit(true);
            return connection;
        } catch (SQLException e) {
            throw new SQLException("Failed to connect to database: " + e.getMessage(), e);
        }
    }
    
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Warning: Failed to close database connection: " + e.getMessage());
            }
        }
    }
    
    public boolean testConnection() {
        try (Connection connection = createConnection()) {
            return connection.isValid(5);
        } catch (SQLException e) {
            System.err.println("Database connection test failed: " + e.getMessage());
            return false;
        }
    }
}
