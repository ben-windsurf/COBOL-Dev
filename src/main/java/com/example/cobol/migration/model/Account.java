package com.example.cobol.migration.model;

import java.time.LocalDateTime;

public class Account {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private boolean enabled;
    private LocalDateTime createDate;
    private LocalDateTime modDate;
    
    public Account() {}
    
    public Account(int id, String firstName, String lastName, String phone, 
                   String address, boolean enabled, LocalDateTime createDate, 
                   LocalDateTime modDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.enabled = enabled;
        this.createDate = createDate;
        this.modDate = modDate;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    
    public LocalDateTime getCreateDate() { return createDate; }
    public void setCreateDate(LocalDateTime createDate) { this.createDate = createDate; }
    
    public LocalDateTime getModDate() { return modDate; }
    public void setModDate(LocalDateTime modDate) { this.modDate = modDate; }
    
    @Override
    public String toString() {
        return String.format("Account{id=%d, firstName='%s', lastName='%s', phone='%s', address='%s', enabled=%s}",
                id, firstName, lastName, phone, address, enabled);
    }
}
