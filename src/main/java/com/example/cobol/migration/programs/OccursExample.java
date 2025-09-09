package com.example.cobol.migration.programs;

import com.example.cobol.migration.model.ItemTable;
import java.util.ArrayList;
import java.util.List;

public class OccursExample {
    private int numRecords;
    private List<CustomerRecord> customers;
    private List<DataTypeRecord> dataTypes;
    
    public OccursExample() {
        this.numRecords = 3;
        this.customers = new ArrayList<>();
        this.dataTypes = new ArrayList<>(2);
    }
    
    public static void main(String[] args) {
        OccursExample example = new OccursExample();
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
        
        CustomerRecord person1 = new CustomerRecord();
        person1.setCustomerType(1);
        person1.setFirstName("test-first");
        person1.setLastName("test-last");
        person1.setStreetAddress("123 fake st");
        person1.setState("NV");
        person1.setZipCode(12345);
        customers.add(person1);
        
        System.out.println("2. Corp record with corp name entered.");
        CustomerRecord corp = new CustomerRecord();
        corp.setCustomerType(2);
        corp.setCorpName("no-name corp");
        corp.setStreetAddress("567 real st");
        corp.setState("NY");
        corp.setZipCode(11795);
        customers.add(corp);
        
        System.out.println("3. Person record with corp name entered.");
        CustomerRecord person2 = new CustomerRecord();
        person2.setCustomerType(1);
        person2.setCorpName("SET CORP VALUE");
        person2.setStreetAddress("890 what st");
        person2.setState("MA");
        person2.setZipCode(9345);
        customers.add(person2);
    }
    
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
            System.out.println(customer.getState() + ", " + customer.getZipCode());
            System.out.println("------------------------------");
            System.out.println();
        }
    }
    
    private void setupSecondTestData() {
        DataTypeRecord record1 = new DataTypeRecord();
        record1.setDataType('D');
        record1.setDisplayValue("ABC123");
        dataTypes.add(record1);
        
        DataTypeRecord record2 = new DataTypeRecord();
        record2.setDataType('C');
        record2.setComputationalValue(12345.63);
        dataTypes.add(record2);
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
    
    public static class CustomerRecord {
        private int customerType;
        private String firstName;
        private String lastName;
        private String corpName;
        private String streetAddress;
        private String state;
        private int zipCode;
        
        public CustomerRecord() {
            this.customerType = 0;
            this.firstName = "";
            this.lastName = "";
            this.corpName = "";
            this.streetAddress = "";
            this.state = "";
            this.zipCode = 0;
        }
        
        public boolean isPersonType() {
            return customerType == 1;
        }
        
        public boolean isCorpType() {
            return customerType == 2;
        }
        
        public int getCustomerType() {
            return customerType;
        }
        
        public void setCustomerType(int customerType) {
            this.customerType = customerType;
        }
        
        public String getFirstName() {
            return firstName;
        }
        
        public void setFirstName(String firstName) {
            this.firstName = firstName != null ? firstName : "";
        }
        
        public String getLastName() {
            return lastName;
        }
        
        public void setLastName(String lastName) {
            this.lastName = lastName != null ? lastName : "";
        }
        
        public String getCorpName() {
            return corpName;
        }
        
        public void setCorpName(String corpName) {
            this.corpName = corpName != null ? corpName : "";
        }
        
        public String getStreetAddress() {
            return streetAddress;
        }
        
        public void setStreetAddress(String streetAddress) {
            this.streetAddress = streetAddress != null ? streetAddress : "";
        }
        
        public String getState() {
            return state;
        }
        
        public void setState(String state) {
            this.state = state != null ? state : "";
        }
        
        public int getZipCode() {
            return zipCode;
        }
        
        public void setZipCode(int zipCode) {
            this.zipCode = zipCode;
        }
    }
    
    public static class DataTypeRecord {
        private char dataType;
        private String displayValue;
        private double computationalValue;
        
        public DataTypeRecord() {
            this.dataType = 'D';
            this.displayValue = "";
            this.computationalValue = 0.0;
        }
        
        public boolean isDisplayType() {
            return dataType == 'D';
        }
        
        public boolean isCompType() {
            return dataType == 'C';
        }
        
        public char getDataType() {
            return dataType;
        }
        
        public void setDataType(char dataType) {
            this.dataType = dataType;
        }
        
        public String getDisplayValue() {
            return displayValue;
        }
        
        public void setDisplayValue(String displayValue) {
            this.displayValue = displayValue != null ? displayValue : "";
        }
        
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
        
        public void setComputationalValue(double computationalValue) {
            this.computationalValue = computationalValue;
            if (isCompType()) {
                this.displayValue = String.valueOf(computationalValue);
            }
        }
    }
}
