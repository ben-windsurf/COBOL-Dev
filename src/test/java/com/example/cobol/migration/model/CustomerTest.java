package com.example.cobol.migration.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    
    @Test
    @DisplayName("Test PersonCustomer creation and properties")
    void testPersonCustomerCreation() {
        PersonCustomer person = new PersonCustomer("test-first", "test-last", "123 fake st", "NV", 12345);
        
        assertEquals(Customer.CustomerType.PERSON, person.getCustomerType());
        assertEquals("test-first", person.getFirstName());
        assertEquals("test-last", person.getLastName());
        assertEquals("test-first test-last", person.getDisplayName());
        assertEquals("test-first test-last", person.getFullName());
        assertEquals("123 fake st", person.getStreetAddress());
        assertEquals("NV", person.getState());
        assertEquals(12345, person.getZipCode());
    }
    
    @Test
    @DisplayName("Test CorporateCustomer creation and properties")
    void testCorporateCustomerCreation() {
        CorporateCustomer corp = new CorporateCustomer("no-name corp", "567 real st", "NY", 11795);
        
        assertEquals(Customer.CustomerType.CORP, corp.getCustomerType());
        assertEquals("no-name corp", corp.getCorporateName());
        assertEquals("no-name corp", corp.getDisplayName());
        assertEquals("567 real st", corp.getStreetAddress());
        assertEquals("NY", corp.getState());
        assertEquals(11795, corp.getZipCode());
    }
    
    @Test
    @DisplayName("Test CustomerType enum conversion")
    void testCustomerTypeEnum() {
        assertEquals(1, Customer.CustomerType.PERSON.getValue());
        assertEquals(2, Customer.CustomerType.CORP.getValue());
        
        assertEquals(Customer.CustomerType.PERSON, Customer.CustomerType.fromValue(1));
        assertEquals(Customer.CustomerType.CORP, Customer.CustomerType.fromValue(2));
        
        assertThrows(IllegalArgumentException.class, () -> Customer.CustomerType.fromValue(99));
    }
    
    @Test
    @DisplayName("Test null safety in constructors")
    void testNullSafety() {
        PersonCustomer person = new PersonCustomer(null, null, null, null, 0);
        assertEquals("", person.getFirstName());
        assertEquals("", person.getLastName());
        assertEquals("", person.getStreetAddress());
        assertEquals("", person.getState());
        
        CorporateCustomer corp = new CorporateCustomer(null, null, null, 0);
        assertEquals("", corp.getCorporateName());
        assertEquals("", corp.getStreetAddress());
        assertEquals("", corp.getState());
    }
    
    @Test
    @DisplayName("Test default constructors")
    void testDefaultConstructors() {
        PersonCustomer person = new PersonCustomer();
        assertEquals("", person.getFirstName());
        assertEquals("", person.getLastName());
        assertEquals(" ", person.getDisplayName());
        assertEquals(Customer.CustomerType.PERSON, person.getCustomerType());
        
        CorporateCustomer corp = new CorporateCustomer();
        assertEquals("", corp.getCorporateName());
        assertEquals("", corp.getDisplayName());
        assertEquals(Customer.CustomerType.CORP, corp.getCustomerType());
    }
    
    @Test
    @DisplayName("Test polymorphic behavior")
    void testPolymorphicBehavior() {
        Customer person = new PersonCustomer("John", "Doe", "123 Main St", "CA", 90210);
        Customer corp = new CorporateCustomer("ACME Corp", "456 Business Ave", "TX", 75001);
        
        assertEquals("John Doe", person.getDisplayName());
        assertEquals("ACME Corp", corp.getDisplayName());
        assertEquals(Customer.CustomerType.PERSON, person.getCustomerType());
        assertEquals(Customer.CustomerType.CORP, corp.getCustomerType());
    }
}
