package com.example.jdbc;

import com.example.jdbc.config.DatabaseConnectionFactory;
import com.example.jdbc.model.Account;
import com.example.jdbc.service.AccountService;
import com.example.jdbc.util.SqlErrorHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SqlExampleApplication {
    
    private final DatabaseConnectionFactory connectionFactory;
    private final AccountService accountService;
    private final Scanner scanner;
    
    public SqlExampleApplication() {
        this.connectionFactory = new DatabaseConnectionFactory();
        this.accountService = new AccountService(connectionFactory);
        this.scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args) {
        SqlExampleApplication app = new SqlExampleApplication();
        app.run();
    }
    
    public void run() {
        System.out.println();
        System.out.println("COBOL SQL DB Example Program");
        System.out.println("----------------------------");
        System.out.println();
        
        if (!connectionFactory.testConnection()) {
            System.err.println("Failed to connect to database. Please check your database configuration.");
            return;
        }
        
        System.out.println("Connected to database successfully.");
        
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("1) Display all accounts");
            System.out.println("2) Display disabled accounts");
            System.out.println("3) Query accounts");
            System.out.println("4) Exit");
            System.out.print("Selection: ");
            
            String choice = scanner.nextLine().trim();
            
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
                    break;
            }
        }
        
        System.out.println("Disconnected.");
        System.out.println();
    }
    
    private void displayAllAccounts() {
        try {
            List<Account> accounts = accountService.getAllAccounts();
            displayAccountResults(accounts);
        } catch (SQLException e) {
            SqlErrorHandler.handleSqlException(e);
        }
    }
    
    private void displayDisabledAccounts() {
        try {
            List<Account> accounts = accountService.getDisabledAccounts();
            displayAccountResults(accounts);
        } catch (SQLException e) {
            SqlErrorHandler.handleSqlException(e);
        }
    }
    
    private void queryAccounts() {
        boolean searchAgain = true;
        
        while (searchAgain) {
            System.out.println();
            System.out.print("Enter search value: ");
            String searchString = scanner.nextLine();
            
            System.out.println("Search value: %" + searchString.trim() + "%");
            System.out.println("Length: " + (searchString.trim().length() + 2));
            
            try {
                List<Account> accounts = accountService.queryAccounts(searchString);
                displayAccountResults(accounts);
            } catch (SQLException e) {
                SqlErrorHandler.handleSqlException(e);
            }
            
            System.out.println();
            System.out.print("Search again? (Y/[N]) ");
            String response = scanner.nextLine().trim().toUpperCase();
            searchAgain = "Y".equals(response);
        }
    }
    
    private void displayAccountResults(List<Account> accounts) {
        System.out.println();
        System.out.println("ACCOUNTS:");
        System.out.println();
        System.out.println(" ID   | First    | Last     | Phone      | Address                | Enabled ");
        System.out.println("------|----------|----------|------------|------------------------|--------");
        
        for (Account account : accounts) {
            System.out.printf("%5d | %-8s | %-8s | %-10s | %-22s | %s%n",
                account.getId(),
                truncateOrPad(account.getFirstName(), 8),
                truncateOrPad(account.getLastName(), 8),
                truncateOrPad(account.getPhone(), 10),
                truncateOrPad(account.getAddress(), 22),
                account.getIsEnabled()
            );
        }
    }
    
    private String truncateOrPad(String value, int length) {
        if (value == null) {
            value = "";
        }
        if (value.length() > length) {
            return value.substring(0, length);
        }
        return String.format("%-" + length + "s", value);
    }
}
