package com.example.jdbc.model;

import java.time.LocalDateTime;

public class Account {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String isEnabled;
    private LocalDateTime createDt;
    private LocalDateTime modDt;

    public Account() {}

    public Account(int id, String firstName, String lastName, String phone, 
                   String address, String isEnabled, LocalDateTime createDt, LocalDateTime modDt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.isEnabled = isEnabled;
        this.createDt = createDt;
        this.modDt = modDt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public LocalDateTime getCreateDt() {
        return createDt;
    }

    public void setCreateDt(LocalDateTime createDt) {
        this.createDt = createDt;
    }

    public LocalDateTime getModDt() {
        return modDt;
    }

    public void setModDt(LocalDateTime modDt) {
        this.modDt = modDt;
    }

    public boolean isAccountEnabled() {
        return "Y".equals(isEnabled);
    }

    public boolean isAccountDisabled() {
        return "N".equals(isEnabled);
    }

    @Override
    public String toString() {
        return String.format("Account{id=%d, firstName='%s', lastName='%s', phone='%s', address='%s', isEnabled='%s'}",
                id, firstName, lastName, phone, address, isEnabled);
    }
}
