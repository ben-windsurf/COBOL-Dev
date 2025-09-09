package com.example.cobol.migration.model;

public class CorporateCustomer extends Customer {
    private String corporateName;
    
    public CorporateCustomer() {
        super();
        this.corporateName = "";
    }
    
    public CorporateCustomer(String corporateName, String streetAddress, String state, int zipCode) {
        super(streetAddress, state, zipCode);
        this.corporateName = corporateName != null ? corporateName : "";
    }
    
    @Override
    public CustomerType getCustomerType() {
        return CustomerType.CORP;
    }
    
    @Override
    public String getDisplayName() {
        return corporateName;
    }
    
    public String getCorporateName() {
        return corporateName;
    }
    
    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName != null ? corporateName : "";
    }
}
