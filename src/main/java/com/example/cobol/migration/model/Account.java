package com.example.cobol.migration.model;

import java.time.LocalDateTime;

/**
 * Account model class representing customer account data.
 * Converted from COBOL data structures to Java POJO.
 */
public final class Account {
    /** Account unique identifier. */
    private int id;
    /** Customer first name. */
    private String firstName;
    /** Customer last name. */
    private String lastName;
    /** Customer phone number. */
    private String phone;
    /** Customer address. */
    private String address;
    /** Account enabled status. */
    private boolean enabled;
    /** Account creation date. */
    private LocalDateTime createDate;
    /** Account modification date. */
    private LocalDateTime modDate;

    /**
     * Default constructor.
     */
    public Account() {
    }

    /**
     * Constructor with basic account information.
     *
     * @param accountId the account ID
     * @param accountFirstName the customer first name
     * @param accountLastName the customer last name
     * @param accountPhone the customer phone number
     * @param accountAddress the customer address
     * @param accountEnabled the account enabled status
     */
    public Account(final int accountId, final String accountFirstName,
                   final String accountLastName, final String accountPhone,
                   final String accountAddress, final boolean accountEnabled) {
        this.id = accountId;
        this.firstName = accountFirstName;
        this.lastName = accountLastName;
        this.phone = accountPhone;
        this.address = accountAddress;
        this.enabled = accountEnabled;
        this.createDate = LocalDateTime.now();
        this.modDate = LocalDateTime.now();
    }

    /**
     * Constructor with all fields including dates.
     *
     * @param accountId the account ID
     * @param accountFirstName the customer first name
     * @param accountLastName the customer last name
     * @param accountPhone the customer phone number
     * @param accountAddress the customer address
     * @param accountEnabled the account enabled status
     * @param accountCreateDate the account creation date
     */
    public Account(final int accountId, final String accountFirstName,
                   final String accountLastName, final String accountPhone,
                   final String accountAddress, final boolean accountEnabled,
                   final LocalDateTime accountCreateDate) {
        this.id = accountId;
        this.firstName = accountFirstName;
        this.lastName = accountLastName;
        this.phone = accountPhone;
        this.address = accountAddress;
        this.enabled = accountEnabled;
        this.createDate = accountCreateDate;
        this.modDate = LocalDateTime.now();
    }

    /**
     * Gets the account ID.
     *
     * @return the account ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the account ID.
     *
     * @param accountId the account ID to set
     */
    public void setId(final int accountId) {
        this.id = accountId;
    }

    /**
     * Gets the customer first name.
     *
     * @return the customer first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the customer first name.
     *
     * @param customerFirstName the customer first name to set
     */
    public void setFirstName(final String customerFirstName) {
        this.firstName = customerFirstName;
    }

    /**
     * Gets the customer last name.
     *
     * @return the customer last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the customer last name.
     *
     * @param customerLastName the customer last name to set
     */
    public void setLastName(final String customerLastName) {
        this.lastName = customerLastName;
    }

    /**
     * Gets the customer phone number.
     *
     * @return the customer phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the customer phone number.
     *
     * @param customerPhone the customer phone number to set
     */
    public void setPhone(final String customerPhone) {
        this.phone = customerPhone;
    }

    /**
     * Gets the customer address.
     *
     * @return the customer address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the customer address.
     *
     * @param customerAddress the customer address to set
     */
    public void setAddress(final String customerAddress) {
        this.address = customerAddress;
    }

    /**
     * Gets the account enabled status.
     *
     * @return true if account is enabled, false otherwise
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the account enabled status.
     *
     * @param accountEnabled the account enabled status to set
     */
    public void setEnabled(final boolean accountEnabled) {
        this.enabled = accountEnabled;
    }

    /**
     * Gets the account creation date.
     *
     * @return the account creation date
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Sets the account creation date.
     *
     * @param accountCreateDate the account creation date to set
     */
    public void setCreateDate(final LocalDateTime accountCreateDate) {
        this.createDate = accountCreateDate;
    }

    /**
     * Gets the account modification date.
     *
     * @return the account modification date
     */
    public LocalDateTime getModDate() {
        return modDate;
    }

    /**
     * Sets the account modification date.
     *
     * @param accountModDate the account modification date to set
     */
    public void setModDate(final LocalDateTime accountModDate) {
        this.modDate = accountModDate;
    }

    /**
     * Returns a string representation of the account.
     *
     * @return formatted string with account details
     */
    @Override
    public String toString() {
        return String.format(
                "Account{id=%d, firstName='%s', lastName='%s', "
                + "phone='%s', address='%s', enabled=%s}",
                id, firstName, lastName, phone, address, enabled);
    }
}
