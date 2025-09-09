package com.example.cobol.migration.programs;

import com.example.cobol.migration.model.ItemTable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SearchExample {
    private List<KeyedItem> keyedItems;
    private List<NoKeyItem> noKeyItems;
    
    public SearchExample() {
        this.keyedItems = new ArrayList<>();
        this.noKeyItems = new ArrayList<>();
    }
    
    public static void main(String[] args) {
        SearchExample example = new SearchExample();
        example.run();
    }
    
    public void run() {
        setupTestData();
        
        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching keyed table using binary search.");
        System.out.println("Demo: Searching for id-1 = 1");
        
        searchBinaryById1(1);
        
        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching again with all required ids matching.");
        System.out.println("Demo: Searching for id-1=2, id-2=102, id-3=499");
        
        searchBinaryByAllIds(2, 102, 499);
        
        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching not keyed table using sequential search.");
        System.out.println("Demo: Searching for id = 3");
        
        searchSequentialById(3);
        
        System.out.println();
    }
    
    private void searchBinaryById1(int searchId) {
        Optional<KeyedItem> found = keyedItems.stream()
            .filter(item -> item.getItemId1() == searchId)
            .findFirst();
            
        if (found.isPresent()) {
            displayFoundItem(found.get());
        } else {
            System.out.println("Item not found.");
        }
    }
    
    private void searchBinaryByAllIds(int searchId1, int searchId2, int searchId3) {
        Optional<KeyedItem> found = keyedItems.stream()
            .filter(item -> item.getItemId1() == searchId1 && 
                           item.getItemId2() == searchId2 && 
                           item.getItemId3() == searchId3)
            .findFirst();
            
        if (found.isPresent()) {
            displayFoundItem(found.get());
        } else {
            System.out.println("Item not found.");
        }
    }
    
    private void searchSequentialById(int searchId) {
        Optional<NoKeyItem> found = noKeyItems.stream()
            .filter(item -> item.getNoKeyId() == searchId)
            .findFirst();
            
        if (found.isPresent()) {
            NoKeyItem item = found.get();
            System.out.println(" Record found:");
            System.out.println("---------------");
            System.out.println("   ws-no-key-id: " + item.getNoKeyId());
            System.out.println("ws-no-key-value: " + item.getNoKeyValue());
            System.out.println();
        } else {
            System.out.println("Item not found.");
        }
    }
    
    private void displayFoundItem(KeyedItem item) {
        System.out.println(" Record found:");
        System.out.println("----------------");
        System.out.println("Item id-1: " + item.getItemId1());
        System.out.println("Item id-2: " + item.getItemId2());
        System.out.println("Item id-3: " + item.getItemId3());
        System.out.println("Item Name: " + item.getItemName());
        System.out.println("Item Date: " + item.getItemDate());
        System.out.println();
    }
    
    private void setupTestData() {
        keyedItems.add(new KeyedItem(1, 101, 500, "test item 1", "2021/01/01"));
        keyedItems.add(new KeyedItem(2, 102, 499, "test item 2", "2021/02/02"));
        keyedItems.add(new KeyedItem(3, 103, 498, "test item 3", "2021/03/03"));
        
        Collections.sort(keyedItems, Comparator.comparing(KeyedItem::getItemId1)
            .thenComparing(KeyedItem::getItemId2)
            .thenComparing(Comparator.comparing(KeyedItem::getItemId3).reversed()));
        
        noKeyItems.add(new NoKeyItem(2, "Value of id 2."));
        noKeyItems.add(new NoKeyItem(3, "Value of id 3."));
        noKeyItems.add(new NoKeyItem(1, "Value of id 1."));
    }
    
    public static class KeyedItem {
        private int itemId1;
        private int itemId2;
        private int itemId3;
        private String itemName;
        private String itemDate;
        
        public KeyedItem(int itemId1, int itemId2, int itemId3, String itemName, String itemDate) {
            this.itemId1 = itemId1;
            this.itemId2 = itemId2;
            this.itemId3 = itemId3;
            this.itemName = itemName != null ? itemName : "";
            this.itemDate = itemDate != null ? itemDate : "";
        }
        
        public int getItemId1() {
            return itemId1;
        }
        
        public int getItemId2() {
            return itemId2;
        }
        
        public int getItemId3() {
            return itemId3;
        }
        
        public String getItemName() {
            return itemName;
        }
        
        public String getItemDate() {
            return itemDate;
        }
    }
    
    public static class NoKeyItem {
        private int noKeyId;
        private String noKeyValue;
        
        public NoKeyItem(int noKeyId, String noKeyValue) {
            this.noKeyId = noKeyId;
            this.noKeyValue = noKeyValue != null ? noKeyValue : "";
        }
        
        public int getNoKeyId() {
            return noKeyId;
        }
        
        public String getNoKeyValue() {
            return noKeyValue;
        }
    }
}
