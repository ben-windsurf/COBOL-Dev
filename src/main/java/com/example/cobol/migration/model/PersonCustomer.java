package com.example.cobol.migration.model;

/**
 * PersonCustomer represents an individual customer.
 * Converted from COBOL REDEFINES pattern to Java inheritance.
 */
public final class PersonCustomer extends Customer {
    /** Customer first name. */
    private String firstName;
    /** Customer last name. */
    private String lastName;

    /**
     * Default constructor.
     */
    public PersonCustomer() {
        super();
        this.firstName = "";
        this.lastName = "";
    }

    /**
     * Constructor with all fields.
     *
     * @param personFirstName the customer first name
     * @param personLastName the customer last name
     * @param streetAddress the customer street address
     * @param state the customer state
     * @param zipCode the customer zip code
     */
    public PersonCustomer(final String personFirstName,
                          final String personLastName,
                          final String streetAddress, final String state,
                          final int zipCode) {
        super(streetAddress, state, zipCode);
        this.firstName = personFirstName != null ? personFirstName : "";
        this.lastName = personLastName != null ? personLastName : "";
    }

    /**
     * Gets the customer type.
     *
     * @return the customer type as PERSON
     */
    @Override
    public CustomerType getCustomerType() {
        return CustomerType.PERSON;
    }

    /**
     * Gets the display name for this customer.
     *
     * @return the full name of the person
     */
    @Override
    public String getDisplayName() {
        return firstName + " " + lastName;
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
        this.firstName = customerFirstName != null ? customerFirstName : "";
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
        this.lastName = customerLastName != null ? customerLastName : "";
    }

    /**
     * Gets the full name of the person.
     *
     * @return the concatenated first and last name, trimmed
     */
    public String getFullName() {
        return getDisplayName().trim();
    }
}
