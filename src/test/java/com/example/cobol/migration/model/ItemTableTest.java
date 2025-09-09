package com.example.cobol.migration.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTableTest {
    private ItemTable itemTable;
    
    @BeforeEach
    void setUp() {
        itemTable = new ItemTable(3);
    }
    
    @Test
    @DisplayName("Test ItemTable creation and basic operations")
    void testItemTableCreation() {
        assertEquals(3, itemTable.getMaxSize());
        assertEquals(0, itemTable.size());
        assertTrue(itemTable.isEmpty());
        assertFalse(itemTable.isFull());
    }
    
    @Test
    @DisplayName("Test adding items to table")
    void testAddItems() {
        ItemTable.Item item1 = new ItemTable.Item("key1", "value1");
        ItemTable.Item item2 = new ItemTable.Item("key2", "value2");
        
        itemTable.addItem(item1);
        assertEquals(1, itemTable.size());
        assertFalse(itemTable.isEmpty());
        assertFalse(itemTable.isFull());
        
        itemTable.addItem(item2);
        assertEquals(2, itemTable.size());
        
        ItemTable.Item item3 = new ItemTable.Item("key3", "value3");
        itemTable.addItem(item3);
        assertEquals(3, itemTable.size());
        assertTrue(itemTable.isFull());
    }
    
    @Test
    @DisplayName("Test table overflow")
    void testTableOverflow() {
        for (int i = 0; i < 3; i++) {
            itemTable.addItem(new ItemTable.Item("key" + i, "value" + i));
        }
        
        assertTrue(itemTable.isFull());
        
        assertThrows(IllegalStateException.class, () -> {
            itemTable.addItem(new ItemTable.Item("overflow", "overflow"));
        });
    }
    
    @Test
    @DisplayName("Test getting items by index")
    void testGetItems() {
        ItemTable.Item item1 = new ItemTable.Item("key1", "value1");
        ItemTable.Item item2 = new ItemTable.Item("key2", "value2");
        
        itemTable.addItem(item1);
        itemTable.addItem(item2);
        
        assertEquals(item1, itemTable.getItem(0));
        assertEquals(item2, itemTable.getItem(1));
        
        assertThrows(IndexOutOfBoundsException.class, () -> {
            itemTable.getItem(2);
        });
        
        assertThrows(IndexOutOfBoundsException.class, () -> {
            itemTable.getItem(-1);
        });
    }
    
    @Test
    @DisplayName("Test Item class functionality")
    void testItemClass() {
        ItemTable.Item item1 = new ItemTable.Item("key1", "value1");
        ItemTable.Item item2 = new ItemTable.Item("key1", "value1");
        ItemTable.Item item3 = new ItemTable.Item("key2", "value2");
        
        assertEquals("key1", item1.getKey());
        assertEquals("value1", item1.getValue());
        
        assertEquals(item1, item2);
        assertNotEquals(item1, item3);
        
        assertEquals(item1.hashCode(), item2.hashCode());
        assertNotEquals(item1.hashCode(), item3.hashCode());
        
        assertEquals("Item{key='key1', value='value1'}", item1.toString());
    }
    
    @Test
    @DisplayName("Test Item null safety")
    void testItemNullSafety() {
        ItemTable.Item item1 = new ItemTable.Item(null, null);
        assertEquals("", item1.getKey());
        assertEquals("", item1.getValue());
        
        ItemTable.Item item2 = new ItemTable.Item();
        assertEquals("", item2.getKey());
        assertEquals("", item2.getValue());
        
        item2.setKey(null);
        item2.setValue(null);
        assertEquals("", item2.getKey());
        assertEquals("", item2.getValue());
    }
    
    @Test
    @DisplayName("Test table clear operation")
    void testClearTable() {
        itemTable.addItem(new ItemTable.Item("key1", "value1"));
        itemTable.addItem(new ItemTable.Item("key2", "value2"));
        
        assertEquals(2, itemTable.size());
        assertFalse(itemTable.isEmpty());
        
        itemTable.clear();
        
        assertEquals(0, itemTable.size());
        assertTrue(itemTable.isEmpty());
        assertFalse(itemTable.isFull());
    }
}
