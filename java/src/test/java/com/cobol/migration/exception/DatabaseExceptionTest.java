package com.cobol.migration.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseExceptionTest {
    
    @Test
    void testBasicException() {
        DatabaseException exception = new DatabaseException("Test message");
        
        assertEquals("Test message", exception.getMessage());
        assertNull(exception.getSqlState());
        assertEquals(0, exception.getSqlCode());
    }
    
    @Test
    void testExceptionWithSqlInfo() {
        DatabaseException exception = new DatabaseException("Test message", "23000", 1062);
        
        assertEquals("Test message", exception.getMessage());
        assertEquals("23000", exception.getSqlState());
        assertEquals(1062, exception.getSqlCode());
    }
    
    @Test
    void testSuccessState() {
        DatabaseException exception = new DatabaseException("Success", "00000", 0);
        assertTrue(exception.isSuccess());
        assertFalse(exception.isNoData());
        assertFalse(exception.isDuplicate());
    }
    
    @Test
    void testNoDataState() {
        DatabaseException exception = new DatabaseException("No data", "02000", 100);
        assertFalse(exception.isSuccess());
        assertTrue(exception.isNoData());
        assertFalse(exception.isDuplicate());
    }
    
    @Test
    void testDuplicateState() {
        DatabaseException exception = new DatabaseException("Duplicate", "23505", 1062);
        assertFalse(exception.isSuccess());
        assertFalse(exception.isNoData());
        assertTrue(exception.isDuplicate());
    }
}
