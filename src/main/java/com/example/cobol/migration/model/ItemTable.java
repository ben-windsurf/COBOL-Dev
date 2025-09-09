package com.example.cobol.migration.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ItemTable represents a COBOL OCCURS table structure in Java.
 * Converted from COBOL fixed-size arrays to Java Collections.
 */
public final class ItemTable {
    /** List of items in the table. */
    private final List<Item> items;
    /** Maximum size of the table. */
    private final int maxSize;

    /** Hash multiplier constant. */
    private static final int HASH_MULTIPLIER = 31;

    /**
     * Constructor with specified maximum size.
     *
     * @param tableMaxSize the maximum number of items in the table
     */
    public ItemTable(final int tableMaxSize) {
        this.maxSize = tableMaxSize;
        this.items = new ArrayList<>(tableMaxSize);
    }

    /**
     * Adds an item to the table if space is available.
     *
     * @param item the item to add
     * @throws IllegalStateException if the table is full
     */
    public void addItem(final Item item) {
        if (items.size() < maxSize) {
            items.add(item);
        } else {
            throw new IllegalStateException("Table is full. Maximum size: "
                    + maxSize);
        }
    }

    /**
     * Gets an item at the specified index.
     *
     * @param index the index of the item to retrieve
     * @return the item at the specified index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public Item getItem(final int index) {
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
                    + items.size());
        }
        return items.get(index);
    }

    /**
     * Gets a copy of all items in the table.
     *
     * @return a new list containing all items
     */
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Gets the current number of items in the table.
     *
     * @return the number of items
     */
    public int size() {
        return items.size();
    }

    /**
     * Gets the maximum size of the table.
     *
     * @return the maximum size
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Checks if the table is empty.
     *
     * @return true if the table contains no items
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Checks if the table is full.
     *
     * @return true if the table has reached maximum capacity
     */
    public boolean isFull() {
        return items.size() >= maxSize;
    }

    /**
     * Removes all items from the table.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Item represents a key-value pair in the table.
     * Equivalent to COBOL table entry with multiple fields.
     */
    public static final class Item {
        /** Item key. */
        private String key;
        /** Item value. */
        private String value;

        /**
         * Default constructor.
         */
        public Item() {
            this.key = "";
            this.value = "";
        }

        /**
         * Constructor for Item.
         *
         * @param itemKey the item key
         * @param itemValue the item value
         */
        public Item(final String itemKey, final String itemValue) {
            this.key = itemKey != null ? itemKey : "";
            this.value = itemValue != null ? itemValue : "";
        }

        /**
         * Gets the item key.
         *
         * @return the item key
         */
        public String getKey() {
            return key;
        }

        /**
         * Sets the item key.
         *
         * @param itemKey the item key to set
         */
        public void setKey(final String itemKey) {
            this.key = itemKey != null ? itemKey : "";
        }

        /**
         * Gets the item value.
         *
         * @return the item value
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the item value.
         *
         * @param itemValue the item value to set
         */
        public void setValue(final String itemValue) {
            this.value = itemValue != null ? itemValue : "";
        }

        /**
         * Returns a string representation of the item.
         *
         * @return formatted string with item details
         */
        @Override
        public String toString() {
            return "Item{key='" + key + "', value='" + value + "'}";
        }

        /**
         * Checks equality with another object.
         *
         * @param obj the object to compare with
         * @return true if objects are equal
         */
        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Item item = (Item) obj;
            return Objects.equals(key, item.key)
                    && Objects.equals(value, item.value);
        }

        /**
         * Generates hash code for the item.
         *
         * @return the hash code
         */
        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
