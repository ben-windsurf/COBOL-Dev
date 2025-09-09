package com.example.cobol.migration.programs;

import com.example.cobol.migration.model.Customer;
import com.example.cobol.migration.model.PersonCustomer;
import com.example.cobol.migration.model.CorporateCustomer;
import java.util.ArrayList;
import java.util.List;

public class RedefinesExample {
    private int numRecords;
    private List<Customer> customers;
    private List<DataTypeExample> dataTypes;
    
    public RedefinesExample() {
        this.numRecords = 3;
        this.customers = new ArrayList<>();
        this.dataTypes = new ArrayList<>();
    }
    
    public static void main(String[] args) {
        RedefinesExample example = new RedefinesExample();
        example.run();
    }
    
    public void run() {
        setupTestData();
        displayCustomerData();
        setupSecondTestData();
        displaySecondTestData();
    }
    
    private void setupTestData() {
        System.out.println();
        System.out.println("1. Person record with first/last name entered.");
        
        PersonCustomer person1 = new PersonCustomer("test-first", "test-last", "123 fake st", "NV", 12345);
        customers.add(person1);
        
        System.out.println("2. Corp record with corp name entered.");
        CorporateCustomer corp = new CorporateCustomer("no-name corp", "567 real st", "NY", 11795);
        customers.add(corp);
        
        System.out.println("3. Person record with corp name entered.");
        PersonCustomer person2 = new PersonCustomer("SET", "CORP VALUE", "890 what st", "MA", 9345);
        customers.add(person2);
    }
    
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
            System.out.println(customer.getState() + ", " + customer.getZipCode());
            System.out.println("------------------------------");
            System.out.println();
        }
    }
    
    private void setupSecondTestData() {
        dataTypes.add(new DataTypeExample(DataTypeExample.DataType.DISPLAY, "ABC123"));
        dataTypes.add(new DataTypeExample(DataTypeExample.DataType.COMP, 12345.63));
    }
    
    private void displaySecondTestData() {
        System.out.println();
        System.out.println("Redefines with different variable types:");
        System.out.println("----------------------------------------");
        System.out.println("Value entered in ws-data-disp-value: ABC123");
        System.out.println("ws-data-disp-value x(10): " + dataTypes.get(0).getDisplayValue());
        System.out.println("ws-data-comp-value comp-2: " + dataTypes.get(0).getComputationalValue());
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("Value entered in ws-data-comp-value: 12345.63");
        System.out.println("ws-data-disp-value x(10): " + dataTypes.get(1).getDisplayValue());
        System.out.println("ws-data-comp-value comp-2: " + dataTypes.get(1).getComputationalValue());
        System.out.println();
    }
    
    public static class DataTypeExample {
        private DataType dataType;
        private String displayValue;
        private double computationalValue;
        
        public DataTypeExample(DataType dataType, String displayValue) {
            this.dataType = dataType;
            this.displayValue = displayValue;
            this.computationalValue = 0.0;
        }
        
        public DataTypeExample(DataType dataType, double computationalValue) {
            this.dataType = dataType;
            this.computationalValue = computationalValue;
            this.displayValue = String.valueOf(computationalValue);
        }
        
        public DataType getDataType() {
            return dataType;
        }
        
        public String getDisplayValue() {
            return displayValue;
        }
        
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
        
        public enum DataType {
            DISPLAY('D'),
            COMP('C');
            
            private final char value;
            
            DataType(char value) {
                this.value = value;
            }
            
            public char getValue() {
                return value;
            }
        }
    }
}
