package com.example.cobol.migration.programs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class SubAppTest {
    private SubApp subApp;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    
    @BeforeEach
    void setUp() {
        subApp = new SubApp();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }
    
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
    
    @Test
    @DisplayName("Test SubApp can be instantiated")
    void testSubAppInstantiation() {
        assertNotNull(subApp);
    }
    
    @Test
    @DisplayName("Test SubApp execute method by content")
    void testExecuteByContent() {
        String[] result = subApp.execute("test1", "test2", false);
        
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals("replace1", result[0]);
        assertEquals("replace2", result[1]);
    }
    
    @Test
    @DisplayName("Test SubApp execute method by reference")
    void testExecuteByReference() {
        String[] result = subApp.execute("test1", "test2", true);
        
        assertNotNull(result);
        assertEquals(2, result.length);
        assertEquals("replace1", result[0]);
        assertEquals("replace2", result[1]);
    }
    
    @Test
    @DisplayName("Test SubApp working storage persistence")
    void testWorkingStoragePersistence() {
        subApp.execute("first", "second", false);
        
        String output = outputStream.toString();
        assertTrue(output.contains("ws-test-item-1: first"));
        assertTrue(output.contains("ws-test-item-2: second"));
        
        outputStream.reset();
        
        subApp.execute("third", "fourth", false);
        output = outputStream.toString();
        assertTrue(output.contains("ws-test-item-1: first"));
        assertTrue(output.contains("ws-test-item-2: second"));
    }
    
    @Test
    @DisplayName("Test SubApp cancel resets working storage")
    void testCancelResetsWorkingStorage() {
        subApp.execute("first", "second", false);
        subApp.cancel();
        
        outputStream.reset();
        
        subApp.execute("third", "fourth", false);
        String output = outputStream.toString();
        assertTrue(output.contains("ws-test-item-1: "));
        assertTrue(output.contains("ws-test-item-2: "));
    }
    
    @Test
    @DisplayName("Test local storage is always fresh")
    void testLocalStorageAlwaysFresh() {
        subApp.execute("first", "second", false);
        
        String output = outputStream.toString();
        assertTrue(output.contains("local-storage values at start:"));
        assertTrue(output.contains("ls-test-item-1: "));
        assertTrue(output.contains("ls-test-item-2: "));
    }
}
