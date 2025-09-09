package com.example.cobol.migration.model;

public abstract class Customer {
    private String streetAddress;
    private String state;
    private int zipCode;
    
    public Customer() {
        this.streetAddress = "";
        this.state = "";
        this.zipCode = 0;
    }
    
    public Customer(String streetAddress, String state, int zipCode) {
        this.streetAddress = streetAddress != null ? streetAddress : "";
        this.state = state != null ? state : "";
        this.zipCode = zipCode;
    }
    
    public abstract CustomerType getCustomerType();
    
    public abstract String getDisplayName();
    
    public String getStreetAddress() {
        return streetAddress;
    }
    
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress != null ? streetAddress : "";
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state != null ? state : "";
    }
    
    public int getZipCode() {
        return zipCode;
    }
    
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    
    public enum CustomerType {
        PERSON(1),
        CORP(2);
        
        private final int value;
        
        CustomerType(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
        
        public static CustomerType fromValue(int value) {
            for (CustomerType type : values()) {
                if (type.value == value) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid customer type: " + value);
        }
    }
}
