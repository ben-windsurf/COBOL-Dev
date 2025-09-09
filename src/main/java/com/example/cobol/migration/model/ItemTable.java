package com.example.cobol.migration.model;

import java.util.ArrayList;
import java.util.List;

public class ItemTable {
    private List<Item> items;
    private int maxSize;
    
    public ItemTable(int maxSize) {
        this.maxSize = maxSize;
        this.items = new ArrayList<>(maxSize);
    }
    
    public void addItem(Item item) {
        if (items.size() < maxSize) {
            items.add(item);
        } else {
            throw new IllegalStateException("Table is full. Maximum size: " + maxSize);
        }
    }
    
    public Item getItem(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + items.size());
        }
        return items.get(index);
    }
    
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
    
    public int size() {
        return items.size();
    }
    
    public int getMaxSize() {
        return maxSize;
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public boolean isFull() {
        return items.size() >= maxSize;
    }
    
    public void clear() {
        items.clear();
    }
    
    public static class Item {
        private String key;
        private String value;
        
        public Item() {
            this.key = "";
            this.value = "";
        }
        
        public Item(String key, String value) {
            this.key = key != null ? key : "";
            this.value = value != null ? value : "";
        }
        
        public String getKey() {
            return key;
        }
        
        public void setKey(String key) {
            this.key = key != null ? key : "";
        }
        
        public String getValue() {
            return value;
        }
        
        public void setValue(String value) {
            this.value = value != null ? value : "";
        }
        
        @Override
        public String toString() {
            return "Item{key='" + key + "', value='" + value + "'}";
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Item item = (Item) obj;
            return key.equals(item.key) && value.equals(item.value);
        }
        
        @Override
        public int hashCode() {
            return key.hashCode() * 31 + value.hashCode();
        }
    }
}
