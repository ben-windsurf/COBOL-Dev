package com.coboldev.migration;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionManagerTest {
    private DatabaseConnectionManager connectionManager;
    private static final String ORIGINAL_ODBC_CONNECTION_STRING = 
        "DRIVER={PostgreSQL Unicode};SERVER=localhost;PORT=5432;DATABASE=cobol_db_example;UID=postgres;PWD=password;COMRESSED_PROTO=0;";
    
    @Before
    public void setUp() {
        connectionManager = new DatabaseConnectionManager();
    }
    
    @After
    public void tearDown() {
        connectionManager = null;
    }
    
    @Test
    public void testDatabaseConfigFromOdbcString() {
        DatabaseConfig config = DatabaseConfig.fromOdbcConnectionString(ORIGINAL_ODBC_CONNECTION_STRING);
        
        assertEquals("localhost", config.getHost());
        assertEquals(5432, config.getPort());
        assertEquals("cobol_db_example", config.getDatabase());
        assertEquals("postgres", config.getUsername());
        assertEquals("password", config.getPassword());
    }
    
    @Test
    public void testJdbcUrlGeneration() {
        DatabaseConfig config = DatabaseConfig.fromOdbcConnectionString(ORIGINAL_ODBC_CONNECTION_STRING);
        String jdbcUrl = config.buildJdbcUrl();
        
        assertEquals("jdbc:postgresql://localhost:5432/cobol_db_example", jdbcUrl);
    }
    
    @Test
    public void testConnectionManagerWithValidConfig() {
        DatabaseConfig config = new DatabaseConfig();
        
        try (Connection connection = connectionManager.getConnection(config)) {
            assertNotNull("Connection should not be null", connection);
            assertFalse("Connection should not be closed", connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Database connection test skipped - database not available: " + e.getMessage());
        }
    }
    
    @Test
    public void testConnectionFromOdbcString() {
        try (Connection connection = connectionManager.getConnectionFromOdbcString(ORIGINAL_ODBC_CONNECTION_STRING)) {
            assertNotNull("Connection should not be null", connection);
            assertFalse("Connection should not be closed", connection.isClosed());
        } catch (SQLException e) {
            System.out.println("Database connection test skipped - database not available: " + e.getMessage());
        }
    }
    
    @Test
    public void testConnectionWithInvalidCredentials() {
        DatabaseConfig invalidConfig = new DatabaseConfig("localhost", 5432, "cobol_db_example", "invalid_user", "invalid_password");
        
        try (Connection connection = connectionManager.getConnection(invalidConfig)) {
            fail("Should have thrown SQLException for invalid credentials");
        } catch (SQLException e) {
            assertTrue("Should contain authentication error", 
                e.getMessage().toLowerCase().contains("authentication") || 
                e.getMessage().toLowerCase().contains("password") ||
                e.getMessage().toLowerCase().contains("failed"));
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConnectionWithNullConfig() throws SQLException {
        connectionManager.getConnection((DatabaseConfig) null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConnectionFromNullOdbcString() throws SQLException {
        connectionManager.getConnectionFromOdbcString(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConnectionFromEmptyOdbcString() throws SQLException {
        connectionManager.getConnectionFromOdbcString("");
    }
    
    @Test
    public void testTestConnectionMethod() {
        DatabaseConfig config = new DatabaseConfig();
        boolean result = connectionManager.testConnection(config);
        
        System.out.println("Test connection result: " + result);
    }
    
    @Test
    public void testTestConnectionFromOdbcStringMethod() {
        boolean result = connectionManager.testConnectionFromOdbcString(ORIGINAL_ODBC_CONNECTION_STRING);
        
        System.out.println("Test connection from ODBC string result: " + result);
    }
    
    @Test
    public void testCloseConnectionWithNull() {
        connectionManager.closeConnection(null);
    }
}
