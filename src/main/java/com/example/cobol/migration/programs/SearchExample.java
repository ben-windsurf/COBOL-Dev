package com.example.cobol.migration.programs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Search Example demonstrating COBOL SEARCH clause conversion to Java.
 * Converted from COBOL search patterns to Java Stream operations.
 */
public final class SearchExample {
    /** List of keyed items for binary search. */
    private List<KeyedItem> keyedItems;
    /** List of non-keyed items for sequential search. */
    private List<NoKeyItem> noKeyItems;

    /**
     * Default constructor.
     */
    public SearchExample() {
        this.keyedItems = new ArrayList<>();
        this.noKeyItems = new ArrayList<>();
    }

    /**
     * Main method to start the search example.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        SearchExample example = new SearchExample();
        example.run();
    }

    /**
     * Run the search demonstration.
     */
    public void run() {
        setupTestData();

        System.out.println();
        System.out.println("================================"
                + "==================");
        System.out.println("Searching keyed table using binary search.");
        final int searchId1 = 1;
        System.out.println("Demo: Searching for id-1 = " + searchId1);

        searchBinaryById1(1);

        System.out.println();
        System.out.println("================================"
                + "==================");
        System.out.println("Searching again with all required ids matching.");
        final int searchId2Value = 102;
        final int searchId3Value = 499;
        System.out.println("Demo: Searching for id-1=2, id-2=" + searchId2Value
                + ", id-3=" + searchId3Value);

        searchBinaryByAllIds(2, searchId2Value, searchId3Value);

        System.out.println();
        System.out.println("================================"
                + "==================");
        System.out.println("Searching not keyed table using sequential " 
                + "search.");
        final int sequentialSearchId = 2;
        System.out.println("Demo: Searching for id = "
                + sequentialSearchId);

        searchSequentialById(sequentialSearchId);

        System.out.println();
    }

    /**
     * Search keyed items by first ID using binary search.
     *
     * @param searchId ID to search for
     */
    private void searchBinaryById1(final int searchId) {
        Optional<KeyedItem> found = keyedItems.stream()
            .filter(item -> item.getItemId1() == searchId)
            .findFirst();

        if (found.isPresent()) {
            displayFoundItem(found.get());
        } else {
            System.out.println("Item not found.");
        }
    }

    /**
     * Search keyed items by all IDs using binary search.
     *
     * @param searchId1 first ID to search for
     * @param searchId2 second ID to search for
     * @param searchId3 third ID to search for
     */
    private void searchBinaryByAllIds(final int searchId1, final int searchId2,
            final int searchId3) {
        Optional<KeyedItem> found = keyedItems.stream()
            .filter(item -> item.getItemId1() == searchId1
                           && item.getItemId2() == searchId2
                           && item.getItemId3() == searchId3)
            .findFirst();

        if (found.isPresent()) {
            displayFoundItem(found.get());
        } else {
            System.out.println("Item not found.");
        }
    }

    /**
     * Search non-keyed items by ID using sequential search.
     *
     * @param searchId ID to search for
     */
    private void searchSequentialById(final int searchId) {
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

    /**
     * Display found keyed item details.
     *
     * @param item keyed item to display
     */
    private void displayFoundItem(final KeyedItem item) {
        System.out.println(" Record found:");
        System.out.println("----------------");
        System.out.println("Item id-1: " + item.getItemId1());
        System.out.println("Item id-2: " + item.getItemId2());
        System.out.println("Item id-3: " + item.getItemId3());
        System.out.println("Item Name: " + item.getItemName());
        System.out.println("Item Date: " + item.getItemDate());
        System.out.println();
    }

    /**
     * Set up test data for search demonstrations.
     */
    private void setupTestData() {
        final int item1Id1 = 1;
        final int item1Id2 = 101;
        final int item1Id3 = 500;
        final int item2Id1 = 2;
        final int item2Id2 = 102;
        final int item2Id3 = 499;
        final int item3Id1 = 3;
        final int item3Id2 = 103;
        final int item3Id3 = 498;

        keyedItems.add(new KeyedItem(item1Id1, item1Id2, item1Id3,
                "test item 1", "2021/01/01"));
        keyedItems.add(new KeyedItem(item2Id1, item2Id2, item2Id3,
                "test item 2", "2021/02/02"));
        keyedItems.add(new KeyedItem(item3Id1, item3Id2, item3Id3,
                "test item 3", "2021/03/03"));

        Collections.sort(keyedItems,
                Comparator.comparing(KeyedItem::getItemId1)
                .thenComparing(KeyedItem::getItemId2)
                .thenComparing(Comparator.comparing(KeyedItem::getItemId3)
                        .reversed()));

        final int noKeyId1 = 2;
        final int noKeyId2 = 3;
        final int noKeyId3 = 1;

        noKeyItems.add(new NoKeyItem(noKeyId1, "Value of id 2."));
        noKeyItems.add(new NoKeyItem(noKeyId2, "Value of id 3."));
        noKeyItems.add(new NoKeyItem(noKeyId3, "Value of id 1."));
    }

    /**
     * Keyed item data class for binary search operations.
     */
    public static class KeyedItem {
        /** First item ID. */
        private int itemId1;
        /** Second item ID. */
        private int itemId2;
        /** Third item ID. */
        private int itemId3;
        /** Item name. */
        private String itemName;
        /** Item date. */
        private String itemDate;

        /**
         * Constructor for keyed item.
         *
         * @param id1 first item ID
         * @param id2 second item ID
         * @param id3 third item ID
         * @param name item name
         * @param date item date
         */
        public KeyedItem(final int id1, final int id2, final int id3,
                final String name, final String date) {
            this.itemId1 = id1;
            this.itemId2 = id2;
            this.itemId3 = id3;
            this.itemName = name != null ? name : "";
            this.itemDate = date != null ? date : "";
        }

        /**
         * Get first item ID.
         *
         * @return first item ID
         */
        public int getItemId1() {
            return itemId1;
        }

        /**
         * Get second item ID.
         *
         * @return second item ID
         */
        public int getItemId2() {
            return itemId2;
        }

        /**
         * Get third item ID.
         *
         * @return third item ID
         */
        public int getItemId3() {
            return itemId3;
        }

        /**
         * Get item name.
         *
         * @return item name
         */
        public String getItemName() {
            return itemName;
        }

        /**
         * Get item date.
         *
         * @return item date
         */
        public String getItemDate() {
            return itemDate;
        }
    }

    /**
     * Non-keyed item data class for sequential search operations.
     */
    public static class NoKeyItem {
        /** Non-keyed item ID. */
        private int noKeyId;
        /** Non-keyed item value. */
        private String noKeyValue;

        /**
         * Constructor for non-keyed item.
         *
         * @param keyId item ID
         * @param keyValue item value
         */
        public NoKeyItem(final int keyId, final String keyValue) {
            this.noKeyId = keyId;
            this.noKeyValue = keyValue != null ? keyValue : "";
        }

        /**
         * Get non-keyed item ID.
         *
         * @return non-keyed item ID
         */
        public int getNoKeyId() {
            return noKeyId;
        }

        /**
         * Get non-keyed item value.
         *
         * @return non-keyed item value
         */
        public String getNoKeyValue() {
            return noKeyValue;
        }
    }
}
