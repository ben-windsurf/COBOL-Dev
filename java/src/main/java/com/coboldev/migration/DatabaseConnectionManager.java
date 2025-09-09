package com.coboldev.migration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";
    
    static {
        try {
            Class.forName(POSTGRESQL_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC driver not found", e);
        }
    }
    
    public Connection getConnection(DatabaseConfig config) throws SQLException {
        if (config == null) {
            throw new IllegalArgumentException("Database configuration cannot be null");
        }
        
        String jdbcUrl = config.buildJdbcUrl();
        Properties props = new Properties();
        props.setProperty("user", config.getUsername());
        props.setProperty("password", config.getPassword());
        
        try {
            return DriverManager.getConnection(jdbcUrl, props);
        } catch (SQLException e) {
            throw new SQLException("Failed to establish database connection to " + jdbcUrl, e);
        }
    }
    
    public Connection getConnection(String host, int port, String database, String username, String password) 
            throws SQLException {
        DatabaseConfig config = new DatabaseConfig(host, port, database, username, password);
        return getConnection(config);
    }
    
    public Connection getConnectionFromOdbcString(String odbcConnectionString) throws SQLException {
        if (odbcConnectionString == null || odbcConnectionString.trim().isEmpty()) {
            throw new IllegalArgumentException("ODBC connection string cannot be null or empty");
        }
        
        DatabaseConfig config = DatabaseConfig.fromOdbcConnectionString(odbcConnectionString);
        return getConnection(config);
    }
    
    public Connection getDefaultConnection() throws SQLException {
        DatabaseConfig config = new DatabaseConfig();
        return getConnection(config);
    }
    
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            }
        }
    }
    
    public boolean testConnection(DatabaseConfig config) {
        try (Connection connection = getConnection(config)) {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean testConnectionFromOdbcString(String odbcConnectionString) {
        try (Connection connection = getConnectionFromOdbcString(odbcConnectionString)) {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
