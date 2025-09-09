package com.cobol.migration.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    
    @Test
    void testAccountCreation() {
        LocalDateTime now = LocalDateTime.now();
        Account account = new Account(1, "John", "Doe", "1234567890", "123 Main St", "Y", now, now);
        
        assertEquals(1, account.getId());
        assertEquals("John", account.getFirstName());
        assertEquals("Doe", account.getLastName());
        assertEquals("1234567890", account.getPhone());
        assertEquals("123 Main St", account.getAddress());
        assertEquals("Y", account.getIsEnabled());
        assertTrue(account.isEnabled());
        assertEquals(now, account.getCreateDt());
        assertEquals(now, account.getModDt());
    }
    
    @Test
    void testDisabledAccount() {
        Account account = new Account();
        account.setIsEnabled("N");
        
        assertEquals("N", account.getIsEnabled());
        assertFalse(account.isEnabled());
    }
    
    @Test
    void testToString() {
        Account account = new Account(1, "John", "Doe", "1234567890", "123 Main St", "Y", null, null);
        String expected = "Account{id=1, firstName='John', lastName='Doe', phone='1234567890', address='123 Main St', isEnabled='Y'}";
        assertEquals(expected, account.toString());
    }
}
