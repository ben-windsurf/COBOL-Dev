package com.example.search;

import java.util.*;

public class SearchExample {
    
    public static class Item {
        private int id1;
        private int id2;
        private int id3;
        private String name;
        private String date;
        
        public Item(int id1, int id2, int id3, String name, String date) {
            this.id1 = id1;
            this.id2 = id2;
            this.id3 = id3;
            this.name = name;
            this.date = date;
        }
        
        public int getId1() { return id1; }
        public int getId2() { return id2; }
        public int getId3() { return id3; }
        public String getName() { return name; }
        public String getDate() { return date; }
        
        @Override
        public String toString() {
            return String.format("Item{id1=%d, id2=%d, id3=%d, name='%s', date='%s'}", 
                id1, id2, id3, name, date);
        }
    }
    
    public static class NoKeyItem {
        private int id;
        private String value;
        
        public NoKeyItem(int id, String value) {
            this.id = id;
            this.value = value;
        }
        
        public int getId() { return id; }
        public String getValue() { return value; }
    }
    
    private List<Item> itemTable;
    private List<NoKeyItem> noKeyItemTable;
    private Scanner scanner;
    
    public SearchExample() {
        scanner = new Scanner(System.in);
        setupTestData();
    }
    
    public static void main(String[] args) {
        SearchExample app = new SearchExample();
        app.run();
    }
    
    public void run() {
        binarySearchExample();
        multipleKeySearchExample();
        sequentialSearchExample();
        
        System.out.println();
    }
    
    private void binarySearchExample() {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching keyed table using binary search.");
        System.out.print("Enter id-1 to search for: ");
        int searchId1 = scanner.nextInt();
        scanner.nextLine();
        
        Item found = Collections.binarySearch(itemTable, new Item(searchId1, 0, 0, "", ""), 
            Comparator.comparingInt(Item::getId1)) >= 0 ? 
            itemTable.stream().filter(item -> item.getId1() == searchId1).findFirst().orElse(null) : null;
        
        if (found != null) {
            displayFoundItem(found);
        } else {
            System.out.println("Item not found.");
        }
    }
    
    private void multipleKeySearchExample() {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching again with all required ids matching.");
        
        System.out.print("Enter id-1 to search for: ");
        int searchId1 = scanner.nextInt();
        
        System.out.print("Enter id-2 to search for: ");
        int searchId2 = scanner.nextInt();
        
        System.out.print("Enter id-3 to search for: ");
        int searchId3 = scanner.nextInt();
        scanner.nextLine();
        
        Item found = itemTable.stream()
            .filter(item -> item.getId1() == searchId1 && 
                           item.getId2() == searchId2 && 
                           item.getId3() == searchId3)
            .findFirst()
            .orElse(null);
        
        if (found != null) {
            displayFoundItem(found);
        } else {
            System.out.println("Item not found.");
        }
    }
    
    private void sequentialSearchExample() {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("Searching not keyed table using sequential search.");
        System.out.print("Enter id: ");
        int searchId = scanner.nextInt();
        scanner.nextLine();
        
        NoKeyItem found = noKeyItemTable.stream()
            .filter(item -> item.getId() == searchId)
            .findFirst()
            .orElse(null);
        
        if (found != null) {
            System.out.println(" Record found:");
            System.out.println("---------------");
            System.out.println("   ws-no-key-id: " + found.getId());
            System.out.println("ws-no-key-value: " + found.getValue());
            System.out.println();
        } else {
            System.out.println("Item not found.");
        }
    }
    
    private void displayFoundItem(Item item) {
        System.out.println(" Record found:");
        System.out.println("----------------");
        System.out.println("Item id-1: " + item.getId1());
        System.out.println("Item id-2: " + item.getId2());
        System.out.println("Item id-3: " + item.getId3());
        System.out.println("Item Name: " + item.getName());
        System.out.println("Item Date: " + item.getDate());
        System.out.println();
    }
    
    private void setupTestData() {
        itemTable = new ArrayList<>();
        itemTable.add(new Item(1, 101, 500, "test item 1", "2021/01/01"));
        itemTable.add(new Item(2, 102, 499, "test item 2", "2021/02/02"));
        itemTable.add(new Item(3, 103, 498, "test item 3", "2021/03/03"));
        
        itemTable.sort(Comparator.comparingInt(Item::getId1));
        
        noKeyItemTable = new ArrayList<>();
        noKeyItemTable.add(new NoKeyItem(2, "Value of id 2."));
        noKeyItemTable.add(new NoKeyItem(3, "Value of id 3."));
        noKeyItemTable.add(new NoKeyItem(1, "Value of id 1."));
    }
}
