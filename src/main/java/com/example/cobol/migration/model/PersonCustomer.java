package com.example.cobol.migration.model;

public class PersonCustomer extends Customer {
    private String firstName;
    private String lastName;
    
    public PersonCustomer() {
        super();
        this.firstName = "";
        this.lastName = "";
    }
    
    public PersonCustomer(String firstName, String lastName, String streetAddress, String state, int zipCode) {
        super(streetAddress, state, zipCode);
        this.firstName = firstName != null ? firstName : "";
        this.lastName = lastName != null ? lastName : "";
    }
    
    @Override
    public CustomerType getCustomerType() {
        return CustomerType.PERSON;
    }
    
    @Override
    public String getDisplayName() {
        return firstName + " " + lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName != null ? firstName : "";
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName != null ? lastName : "";
    }
    
    public String getFullName() {
        return getDisplayName().trim();
    }
}
