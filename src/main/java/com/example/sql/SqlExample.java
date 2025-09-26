package com.example.sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SqlExample {
    
    public static class Account {
        private int id;
        private String firstName;
        private String lastName;
        private String phone;
        private String address;
        private boolean isEnabled;
        private String createDt;
        private String modDt;
        
        public Account() {}
        
        public Account(int id, String firstName, String lastName, String phone, 
                      String address, boolean isEnabled, String createDt, String modDt) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.address = address;
            this.isEnabled = isEnabled;
            this.createDt = createDt;
            this.modDt = modDt;
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
        
        public boolean isEnabled() { return isEnabled; }
        public void setEnabled(boolean enabled) { isEnabled = enabled; }
        
        public String getCreateDt() { return createDt; }
        public void setCreateDt(String createDt) { this.createDt = createDt; }
        
        public String getModDt() { return modDt; }
        public void setModDt(String modDt) { this.modDt = modDt; }
    }
    
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/cobol_db_example";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "password";
    
    private Connection connection;
    private Scanner scanner;
    
    public SqlExample() {
        scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args) {
        SqlExample app = new SqlExample();
        app.run();
    }
    
    public void run() {
        System.out.println();
        System.out.println("COBOL SQL DB Example Program");
        System.out.println("----------------------------");
        System.out.println();
        
        try {
            connectToDatabase();
            
            boolean running = true;
            while (running) {
                System.out.println();
                System.out.println("1) Display all accounts");
                System.out.println("2) Display disabled accounts");
                System.out.println("3) Query accounts");
                System.out.println("4) Exit");
                System.out.print("Selection: ");
                
                String choice = scanner.nextLine();
                
                switch (choice) {
                    case "1":
                        displayAllAccounts();
                        break;
                    case "2":
                        displayDisabledAccounts();
                        break;
                    case "3":
                        queryAccounts();
                        break;
                    case "4":
                        running = false;
                        break;
                    default:
                        System.out.println("Please make a selection between 1-4");
                }
            }
            
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            disconnectFromDatabase();
            System.out.println("Disconnected.");
            System.out.println();
        }
    }
    
    private void connectToDatabase() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        System.out.println("Connected to database successfully.");
    }
    
    private void disconnectFromDatabase() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
    
    private void displayAllAccounts() throws SQLException {
        String sql = "SELECT ID, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, IS_ENABLED, CREATE_DT, MOD_DT " +
                    "FROM ACCOUNTS ORDER BY ID";
        
        List<Account> accounts = executeQuery(sql);
        displayAccountResults(accounts);
    }
    
    private void displayDisabledAccounts() throws SQLException {
        String sql = "SELECT ID, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, IS_ENABLED, CREATE_DT, MOD_DT " +
                    "FROM ACCOUNTS WHERE IS_ENABLED = 'N' ORDER BY ID";
        
        List<Account> accounts = executeQuery(sql);
        displayAccountResults(accounts);
    }
    
    private void queryAccounts() throws SQLException {
        boolean searchAgain = true;
        
        while (searchAgain) {
            System.out.println();
            System.out.print("Enter search value: ");
            String searchString = scanner.nextLine();
            
            String searchValue = "%" + searchString.trim() + "%";
            System.out.println("Search value: " + searchValue);
            System.out.println("Length: " + searchValue.length());
            
            String sql = "SELECT ID, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, IS_ENABLED, CREATE_DT, MOD_DT " +
                        "FROM ACCOUNTS WHERE FIRST_NAME LIKE ? OR LAST_NAME LIKE ? OR PHONE LIKE ? OR ADDRESS LIKE ? " +
                        "ORDER BY ID";
            
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, searchValue);
                stmt.setString(2, searchValue);
                stmt.setString(3, searchValue);
                stmt.setString(4, searchValue);
                
                List<Account> accounts = executeQuery(stmt);
                displayAccountResults(accounts);
            }
            
            System.out.println();
            System.out.print("Search again? (Y/[N]) ");
            String response = scanner.nextLine().trim().toUpperCase();
            searchAgain = "Y".equals(response);
        }
    }
    
    private List<Account> executeQuery(String sql) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            return executeQuery(stmt);
        }
    }
    
    private List<Account> executeQuery(PreparedStatement stmt) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Account account = new Account(
                    rs.getInt("ID"),
                    rs.getString("FIRST_NAME"),
                    rs.getString("LAST_NAME"),
                    rs.getString("PHONE"),
                    rs.getString("ADDRESS"),
                    "Y".equals(rs.getString("IS_ENABLED")),
                    rs.getString("CREATE_DT"),
                    rs.getString("MOD_DT")
                );
                accounts.add(account);
            }
        }
        
        return accounts;
    }
    
    private void displayAccountResults(List<Account> accounts) {
        System.out.println();
        System.out.println("ACCOUNTS:");
        System.out.println();
        System.out.println(" ID   | First    | Last     | Phone      | Address                | Enabled ");
        System.out.println("------|----------|----------|------------|------------------------|----------");
        
        for (Account account : accounts) {
            System.out.printf("%5d | %-8s | %-8s | %-10s | %-22s | %s%n",
                account.getId(),
                account.getFirstName(),
                account.getLastName(),
                account.getPhone(),
                account.getAddress(),
                account.isEnabled() ? "Y" : "N"
            );
        }
    }
}
