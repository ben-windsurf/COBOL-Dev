package com.example.cobol.migration.programs;

import java.util.ArrayList;
import java.util.List;

/**
 * OCCURS Example demonstrating COBOL OCCURS clause conversion to Java.
 * Converted from COBOL occurs patterns to Java List operations.
 */
public final class OccursExample {
    /** Number of records to process. */
    private int numRecords;
    /** List of customer records. */
    private List<CustomerRecord> customers;
    /** List of data type records. */
    private List<DataTypeRecord> dataTypes;

    /**
     * Default constructor.
     */
    public OccursExample() {
        final int defaultRecordCount = 3;
        this.numRecords = defaultRecordCount;
        this.customers = new ArrayList<>();
        final int dataTypeCapacity = 2;
        this.dataTypes = new ArrayList<>(dataTypeCapacity);
    }

    /**
     * Main method to demonstrate OCCURS functionality.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        OccursExample example = new OccursExample();
        example.run();
    }

    /**
     * Run the OCCURS demonstration.
     */
    public void run() {
        setupTestData();
        displayCustomerData();
        setupSecondTestData();
        displaySecondTestData();
    }

    /**
     * Set up test customer data.
     */
    private void setupTestData() {
        System.out.println();
        System.out.println("1. Person record with first/last name entered.");

        CustomerRecord person1 = new CustomerRecord();
        final int personType = 1;
        person1.setCustomerType(personType);
        person1.setFirstName("test-first");
        person1.setLastName("test-last");
        person1.setStreetAddress("123 fake st");
        person1.setState("NV");
        final int personZip = 12345;
        person1.setZipCode(personZip);
        customers.add(person1);

        System.out.println("2. Corp record with corp name entered.");
        CustomerRecord corp = new CustomerRecord();
        final int corpType = 2;
        corp.setCustomerType(corpType);
        corp.setCorpName("no-name corp");
        corp.setStreetAddress("567 real st");
        corp.setState("NY");
        final int corpZip = 11795;
        corp.setZipCode(corpZip);
        customers.add(corp);

        System.out.println("3. Person record with corp name entered.");
        CustomerRecord person2 = new CustomerRecord();
        person2.setCustomerType(personType);
        person2.setCorpName("SET CORP VALUE");
        person2.setStreetAddress("890 what st");
        person2.setState("MA");
        final int person2Zip = 9345;
        person2.setZipCode(person2Zip);
        customers.add(person2);
    }

    /**
     * Display customer data.
     */
    private void displayCustomerData() {
        System.out.println();
        System.out.println("Displaying fake customer data:");
        System.out.println("------------------------------");
        System.out.println();

        for (int i = 0; i < Math.min(customers.size(), numRecords); i++) {
            CustomerRecord customer = customers.get(i);

            if (customer.isPersonType()) {
                System.out.println("Customer Type: PERSON");
                System.out.println("First Name: " + customer.getFirstName());
                System.out.println("Last Name: " + customer.getLastName());
            } else {
                System.out.println("Customer Type: CORP");
                System.out.println("Company name: " + customer.getCorpName());
            }

            System.out.println("Address: ");
            System.out.println(customer.getStreetAddress());
            System.out.println(customer.getState() + ", "
                    + customer.getZipCode());
            System.out.println("------------------------------");
            System.out.println();
        }
    }

    /**
     * Set up second test data for data types.
     */
    private void setupSecondTestData() {
        DataTypeRecord record1 = new DataTypeRecord();
        record1.setDataType('D');
        record1.setDisplayValue("ABC123");
        dataTypes.add(record1);

        DataTypeRecord record2 = new DataTypeRecord();
        record2.setDataType('C');
        final double testCompValue = 12345.63;
        record2.setComputationalValue(testCompValue);
        dataTypes.add(record2);
    }

    /**
     * Display second test data results.
     */
    private void displaySecondTestData() {
        System.out.println();
        System.out.println("Redefines with different variable types:");
        System.out.println("----------------------------------------");
        System.out.println("Value entered in ws-data-disp-value: ABC123");
        final int firstRecord = 0;
        System.out.println("ws-data-disp-value x(10): "
                + dataTypes.get(firstRecord).getDisplayValue());
        System.out.println("ws-data-comp-value comp-2: "
                + dataTypes.get(firstRecord).getComputationalValue());
        System.out.println();
        System.out.println("----------------------------------------");
        final double testCompValue = 12345.63;
        System.out.println("Value entered in ws-data-comp-value: "
                + testCompValue);
        final int secondRecord = 1;
        System.out.println("ws-data-disp-value x(10): "
                + dataTypes.get(secondRecord).getDisplayValue());
        System.out.println("ws-data-comp-value comp-2: "
                + dataTypes.get(secondRecord).getComputationalValue());
        System.out.println();
    }

    /**
     * Customer record data class.
     */
    public static class CustomerRecord {
        /** Customer type identifier. */
        private int customerType;
        /** First name for person customers. */
        private String firstName;
        /** Last name for person customers. */
        private String lastName;
        /** Corporation name for corporate customers. */
        private String corpName;
        /** Street address. */
        private String streetAddress;
        /** State abbreviation. */
        private String state;
        /** ZIP code. */
        private int zipCode;

        /**
         * Default constructor.
         */
        public CustomerRecord() {
            this.customerType = 0;
            this.firstName = "";
            this.lastName = "";
            this.corpName = "";
            this.streetAddress = "";
            this.state = "";
            this.zipCode = 0;
        }

        /**
         * Check if customer is person type.
         *
         * @return true if person type
         */
        public boolean isPersonType() {
            final int personTypeId = 1;
            return customerType == personTypeId;
        }

        /**
         * Check if customer is corporate type.
         *
         * @return true if corporate type
         */
        public boolean isCorpType() {
            final int corpTypeId = 2;
            return customerType == corpTypeId;
        }

        /**
         * Get customer type.
         *
         * @return customer type identifier
         */
        public int getCustomerType() {
            return customerType;
        }

        /**
         * Set customer type.
         *
         * @param custType customer type identifier
         */
        public void setCustomerType(final int custType) {
            this.customerType = custType;
        }

        /**
         * Get first name.
         *
         * @return first name
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * Set first name.
         *
         * @param fname first name
         */
        public void setFirstName(final String fname) {
            this.firstName = fname != null ? fname : "";
        }

        /**
         * Get last name.
         *
         * @return last name
         */
        public String getLastName() {
            return lastName;
        }

        /**
         * Set last name.
         *
         * @param lname last name
         */
        public void setLastName(final String lname) {
            this.lastName = lname != null ? lname : "";
        }

        /**
         * Get corporation name.
         *
         * @return corporation name
         */
        public String getCorpName() {
            return corpName;
        }

        /**
         * Set corporation name.
         *
         * @param cname corporation name
         */
        public void setCorpName(final String cname) {
            this.corpName = cname != null ? cname : "";
        }

        /**
         * Get street address.
         *
         * @return street address
         */
        public String getStreetAddress() {
            return streetAddress;
        }

        /**
         * Set street address.
         *
         * @param address street address
         */
        public void setStreetAddress(final String address) {
            this.streetAddress = address != null ? address : "";
        }

        /**
         * Get state.
         *
         * @return state abbreviation
         */
        public String getState() {
            return state;
        }

        /**
         * Set state.
         *
         * @param stateCode state abbreviation
         */
        public void setState(final String stateCode) {
            this.state = stateCode != null ? stateCode : "";
        }

        /**
         * Get ZIP code.
         *
         * @return ZIP code
         */
        public int getZipCode() {
            return zipCode;
        }

        /**
         * Set ZIP code.
         *
         * @param zip ZIP code
         */
        public void setZipCode(final int zip) {
            this.zipCode = zip;
        }
    }

    /**
     * Data type record class for REDEFINES demonstration.
     */
    public static class DataTypeRecord {
        /** Data type identifier. */
        private char dataType;
        /** Display value. */
        private String displayValue;
        /** Computational value. */
        private double computationalValue;

        /**
         * Default constructor.
         */
        public DataTypeRecord() {
            this.dataType = 'D';
            this.displayValue = "";
            this.computationalValue = 0.0;
        }

        /**
         * Check if data type is display type.
         *
         * @return true if display type
         */
        public boolean isDisplayType() {
            return dataType == 'D';
        }

        /**
         * Check if data type is computational type.
         *
         * @return true if computational type
         */
        public boolean isCompType() {
            return dataType == 'C';
        }

        /**
         * Get data type.
         *
         * @return data type identifier
         */
        public char getDataType() {
            return dataType;
        }

        /**
         * Set data type.
         *
         * @param dtype data type identifier
         */
        public void setDataType(final char dtype) {
            this.dataType = dtype;
        }

        /**
         * Get display value.
         *
         * @return display value
         */
        public String getDisplayValue() {
            return displayValue;
        }

        /**
         * Set display value.
         *
         * @param dvalue display value
         */
        public void setDisplayValue(final String dvalue) {
            this.displayValue = dvalue != null ? dvalue : "";
        }

        /**
         * Get computational value.
         *
         * @return computational value
         */
        public double getComputationalValue() {
            if (isDisplayType()) {
                try {
                    return Double.parseDouble(displayValue);
                } catch (NumberFormatException e) {
                    return 0.0;
                }
            }
            return computationalValue;
        }

        /**
         * Set computational value.
         *
         * @param cvalue computational value
         */
        public void setComputationalValue(final double cvalue) {
            this.computationalValue = cvalue;
            if (isCompType()) {
                this.displayValue = String.valueOf(cvalue);
            }
        }
    }
}
