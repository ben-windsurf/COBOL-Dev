package com.cobol.migration.connection;

import com.cobol.migration.config.DatabaseConfig;
import com.cobol.migration.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private final DatabaseConfig config;
    
    public DatabaseConnectionManager(DatabaseConfig config) {
        this.config = config;
    }
    
    public Connection getConnection() throws DatabaseException {
        try {
            return DriverManager.getConnection(
                config.getJdbcUrl(),
                config.getUsername(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new DatabaseException(
                "Failed to establish database connection: " + e.getMessage(),
                e.getSQLState(),
                e.getErrorCode(),
                e
            );
        }
    }
    
    public void closeConnection(Connection connection) throws DatabaseException {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DatabaseException(
                    "Failed to close database connection: " + e.getMessage(),
                    e.getSQLState(),
                    e.getErrorCode(),
                    e
                );
            }
        }
    }
    
    public boolean testConnection() {
        try (Connection connection = getConnection()) {
            return connection != null && !connection.isClosed();
        } catch (Exception e) {
            return false;
        }
    }
}
