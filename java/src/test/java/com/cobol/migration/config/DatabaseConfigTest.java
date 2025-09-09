package com.cobol.migration.config;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseConfigTest {
    
    @Test
    void testDefaultConfiguration() {
        DatabaseConfig config = new DatabaseConfig();
        
        assertEquals("localhost", config.getHost());
        assertEquals(5432, config.getPort());
        assertEquals("cobol_db_example", config.getDatabase());
        assertEquals("postgres", config.getUsername());
        assertEquals("password", config.getPassword());
        assertEquals("jdbc:postgresql://localhost:5432/cobol_db_example", config.getJdbcUrl());
    }
    
    @Test
    void testCustomConfiguration() {
        DatabaseConfig config = new DatabaseConfig("testhost", 5433, "testdb", "testuser", "testpass");
        
        assertEquals("testhost", config.getHost());
        assertEquals(5433, config.getPort());
        assertEquals("testdb", config.getDatabase());
        assertEquals("testuser", config.getUsername());
        assertEquals("testpass", config.getPassword());
        assertEquals("jdbc:postgresql://testhost:5433/testdb", config.getJdbcUrl());
    }
}
