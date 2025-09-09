package com.example.cobol.migration.model;

/**
 * Abstract base class for customer data.
 * Converted from COBOL REDEFINES pattern to Java inheritance hierarchy.
 */
public abstract class Customer {
    /** Customer street address. */
    private String streetAddress;
    /** Customer state. */
    private String state;
    /** Customer zip code. */
    private int zipCode;

    /**
     * Default constructor.
     */
    public Customer() {
        this.streetAddress = "";
        this.state = "";
        this.zipCode = 0;
    }

    /**
     * Constructor with address fields.
     *
     * @param customerStreetAddress the customer street address
     * @param customerState the customer state
     * @param customerZipCode the customer zip code
     */
    public Customer(final String customerStreetAddress,
                    final String customerState,
                    final int customerZipCode) {
        this.streetAddress = customerStreetAddress != null ? customerStreetAddress : "";
        this.state = customerState != null ? customerState : "";
        this.zipCode = customerZipCode;
    }

    /**
     * Gets the customer type.
     *
     * @return the customer type
     */
    public abstract CustomerType getCustomerType();

    /**
     * Gets the display name for this customer.
     *
     * @return the customer display name
     */
    public abstract String getDisplayName();

    /**
     * Gets the customer street address.
     *
     * @return the customer street address
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets the customer street address.
     *
     * @param customerStreetAddress the customer street address to set
     */
    public void setStreetAddress(final String customerStreetAddress) {
        this.streetAddress = customerStreetAddress != null
                ? customerStreetAddress : "";
    }

    /**
     * Gets the customer state.
     *
     * @return the customer state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the customer state.
     *
     * @param customerState the customer state to set
     */
    public void setState(final String customerState) {
        this.state = customerState != null ? customerState : "";
    }

    /**
     * Gets the customer zip code.
     *
     * @return the customer zip code
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * Sets the customer zip code.
     *
     * @param customerZipCode the customer zip code to set
     */
    public void setZipCode(final int customerZipCode) {
        this.zipCode = customerZipCode;
    }

    /**
     * Enumeration of customer types with COBOL-compatible integer values.
     */
    public enum CustomerType {
        /** Individual person customer. */
        PERSON(1),
        /** Corporate customer. */
        CORP(2);

        /** The integer value for COBOL compatibility. */
        private final int value;

        /**
         * Constructor for CustomerType enum.
         *
         * @param typeValue the integer value for COBOL compatibility
         */
        CustomerType(final int typeValue) {
            this.value = typeValue;
        }

        /**
         * Gets the integer value for COBOL compatibility.
         *
         * @return the integer value
         */
        public int getValue() {
            return value;
        }

        /**
         * Creates a CustomerType from an integer value.
         *
         * @param typeValue the integer value
         * @return the corresponding CustomerType
         * @throws IllegalArgumentException if the value is invalid
         */
        public static CustomerType fromValue(final int typeValue) {
            for (CustomerType type : values()) {
                if (type.value == typeValue) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid customer type: "
                    + typeValue);
        }
    }
}
