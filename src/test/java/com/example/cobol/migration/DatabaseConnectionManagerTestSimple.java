package com.example.cobol.migration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionManagerTestSimple {
    
    @Test
    @DisplayName("Test singleton pattern - same instance returned")
    void testSingletonPattern() {
        assertDoesNotThrow(() -> {
            DatabaseConnectionManager instance1 = DatabaseConnectionManager.getInstance();
            DatabaseConnectionManager instance2 = DatabaseConnectionManager.getInstance();
            assertSame(instance1, instance2);
        });
    }
}
