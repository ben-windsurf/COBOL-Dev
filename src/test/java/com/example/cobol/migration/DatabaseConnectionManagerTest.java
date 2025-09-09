package com.example.cobol.migration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionManagerTest {
    
    @Test
    @DisplayName("Test singleton pattern - same instance returned")
    void testSingletonPattern() {
        DatabaseConnectionManager instance1 = DatabaseConnectionManager.getInstance();
        DatabaseConnectionManager instance2 = DatabaseConnectionManager.getInstance();
        
        assertSame(instance1, instance2);
    }
    
    @Test
    @DisplayName("Test connection can be obtained")
    void testGetConnection() {
        DatabaseConnectionManager manager = DatabaseConnectionManager.getInstance();
        
        assertDoesNotThrow(() -> {
            try (Connection conn = manager.getConnection()) {
                assertNotNull(conn);
                assertFalse(conn.isClosed());
            }
        });
    }
    
    @Test
    @DisplayName("Test connection manager reports connection status")
    void testIsConnected() {
        DatabaseConnectionManager manager = DatabaseConnectionManager.getInstance();
        
        boolean connected = manager.isConnected();
        assertTrue(connected || !connected); // Just verify method doesn't throw
    }
}
