package com.example.cobol.migration.model;

/**
 * CorporateCustomer represents a corporate customer.
 * Converted from COBOL REDEFINES pattern to Java inheritance.
 */
public final class CorporateCustomer extends Customer {
    /** Corporate customer name. */
    private String corporateName;

    /**
     * Default constructor.
     */
    public CorporateCustomer() {
        super();
        this.corporateName = "";
    }

    /**
     * Constructor with all fields.
     *
     * @param companyName the corporate customer name
     * @param streetAddress the customer street address
     * @param state the customer state
     * @param zipCode the customer zip code
     */
    public CorporateCustomer(final String companyName,
                             final String streetAddress, final String state,
                             final int zipCode) {
        super(streetAddress, state, zipCode);
        this.corporateName = companyName != null ? companyName : "";
    }

    /**
     * Gets the customer type.
     *
     * @return the customer type as CORP
     */
    @Override
    public CustomerType getCustomerType() {
        return CustomerType.CORP;
    }

    /**
     * Gets the display name for this customer.
     *
     * @return the corporate name
     */
    @Override
    public String getDisplayName() {
        return corporateName;
    }

    /**
     * Gets the corporate customer name.
     *
     * @return the corporate customer name
     */
    public String getCorporateName() {
        return corporateName;
    }

    /**
     * Sets the corporate customer name.
     *
     * @param customerCorporateName the corporate customer name to set
     */
    public void setCorporateName(final String customerCorporateName) {
        this.corporateName = customerCorporateName != null
                ? customerCorporateName : "";
    }
}
