package com.example.cobol.migration.programs;

import com.example.cobol.migration.model.Customer;
import com.example.cobol.migration.model.PersonCustomer;
import com.example.cobol.migration.model.CorporateCustomer;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates COBOL REDEFINES clause conversion to Java.
 * Converted from COBOL redefines.cbl program showing how memory overlays
 * are handled using inheritance and union-like patterns in Java.
 */
public final class RedefinesExample {
    /** Number of records to process. */
    private int numRecords;
    /** List of customer records. */
    private List<Customer> customers;
    /** List of data type examples. */
    private List<DataTypeExample> dataTypes;

    /**
     * Default constructor.
     */
    public RedefinesExample() {
        final int defaultRecordCount = 3;
        this.numRecords = defaultRecordCount;
        this.customers = new ArrayList<>();
        this.dataTypes = new ArrayList<>();
    }

    /**
     * Main method to start the redefines example.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        RedefinesExample example = new RedefinesExample();
        example.run();
    }

    /**
     * Main execution method that demonstrates REDEFINES functionality.
     */
    public void run() {
        setupTestData();
        displayCustomerData();
        setupSecondTestData();
        displaySecondTestData();
    }

    /**
     * Set up test customer data for demonstration.
     */
    private void setupTestData() {
        System.out.println();
        System.out.println("1. Person record with first/last name entered.");

        final int zipCode1 = 12345;
        PersonCustomer person1 = new PersonCustomer("test-first", "test-last",
                "123 fake st", "NV", zipCode1);
        customers.add(person1);

        System.out.println("2. Corp record with corp name entered.");
        final int zipCode2 = 11795;
        CorporateCustomer corp = new CorporateCustomer("no-name corp",
                "567 real st", "NY", zipCode2);
        customers.add(corp);

        System.out.println("3. Person record with corp name entered.");
        final int zipCode3 = 9345;
        PersonCustomer person2 = new PersonCustomer("SET", "CORP VALUE",
                "890 what st", "MA", zipCode3);
        customers.add(person2);
    }

    /**
     * Display customer data for verification.
     */
    private void displayCustomerData() {
        System.out.println();
        System.out.println("Displaying fake customer data:");
        System.out.println("------------------------------");
        System.out.println();

        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);

            if (customer.getCustomerType() == Customer.CustomerType.PERSON) {
                PersonCustomer person = (PersonCustomer) customer;
                System.out.println("Customer Type: PERSON");
                System.out.println("First Name: " + person.getFirstName());
                System.out.println("Last Name: " + person.getLastName());
            } else {
                CorporateCustomer corp = (CorporateCustomer) customer;
                System.out.println("Customer Type: CORP");
                System.out.println("Company name: " + corp.getCorporateName());
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
     * Set up second test data for data type demonstration.
     */
    private void setupSecondTestData() {
        dataTypes.add(new DataTypeExample(DataTypeExample.DataType.DISPLAY,
                "ABC123"));
        final double testCompValue = 12345.63;
        dataTypes.add(new DataTypeExample(DataTypeExample.DataType.COMP,
                testCompValue));
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
     * Inner class demonstrating COBOL data type conversion.
     * Shows how COBOL DISPLAY and COMP data types are handled in Java.
     */
    public static class DataTypeExample {
        /** Data type identifier. */
        private DataType dataType;
        /** Display value representation. */
        private String displayValue;
        /** Computational value representation. */
        private double computationalValue;

        /**
         * Constructor for display type data.
         *
         * @param dtype data type
         * @param dispValue display value
         */
        public DataTypeExample(final DataType dtype, final String dispValue) {
            this.dataType = dtype;
            this.displayValue = dispValue;
            this.computationalValue = 0.0;
        }

        /**
         * Constructor for computational type data.
         *
         * @param dtype data type
         * @param compValue computational value
         */
        public DataTypeExample(final DataType dtype, final double compValue) {
            this.dataType = dtype;
            this.computationalValue = compValue;
            this.displayValue = String.valueOf(compValue);
        }

        /**
         * Get the data type of this example.
         *
         * @return the data type
         */
        public DataType getDataType() {
            return dataType;
        }

        /**
         * Get the display value representation.
         *
         * @return the display value as string
         */
        public String getDisplayValue() {
            return displayValue;
        }

        /**
         * Get the computational value representation.
         *
         * @return the computational value as double
         */
        public double getComputationalValue() {
            if (dataType == DataType.DISPLAY) {
                try {
                    return Double.parseDouble(displayValue);
                } catch (NumberFormatException e) {
                    return 0.0;
                }
            }
            return computationalValue;
        }

        /**
         * Enum representing COBOL data types.
         * DISPLAY corresponds to character data, COMP to computational data.
         */
        public enum DataType {
            /** Display data type. */
            DISPLAY('D'),
            /** Computational data type. */
            COMP('C');

            /** Character value of the data type. */
            private final char value;

            /**
             * Constructor for data type enum.
             *
             * @param val character value
             */
            DataType(final char val) {
                this.value = val;
            }

            /**
             * Get the character value of this data type.
             *
             * @return the character representation
             */
            public char getValue() {
                return value;
            }
        }
    }
}
