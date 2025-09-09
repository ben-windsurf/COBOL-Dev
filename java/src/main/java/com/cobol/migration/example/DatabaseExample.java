package com.cobol.migration.example;

import com.cobol.migration.config.DatabaseConfig;
import com.cobol.migration.connection.DatabaseConnectionManager;
import com.cobol.migration.dao.AccountDAO;
import com.cobol.migration.exception.DatabaseException;
import com.cobol.migration.model.Account;

import java.util.List;
import java.util.Scanner;

public class DatabaseExample {
    private final AccountDAO accountDAO;
    private final Scanner scanner;
    
    public DatabaseExample() {
        DatabaseConfig config = new DatabaseConfig();
        DatabaseConnectionManager connectionManager = new DatabaseConnectionManager(config);
        this.accountDAO = new AccountDAO(connectionManager);
        this.scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args) {
        DatabaseExample example = new DatabaseExample();
        example.run();
    }
    
    public void run() {
        System.out.println();
        System.out.println("Java JDBC DB Example Program");
        System.out.println("----------------------------");
        System.out.println();
        
        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim();
            
            try {
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
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Please make a selection between 1-4");
                }
            } catch (DatabaseException e) {
                handleDatabaseError(e);
            }
        }
    }
    
    private void displayMenu() {
        System.out.println();
        System.out.println("1) Display all accounts");
        System.out.println("2) Display disabled accounts");
        System.out.println("3) Query accounts");
        System.out.println("4) Exit");
        System.out.print("Selection: ");
    }
    
    private void displayAllAccounts() throws DatabaseException {
        List<Account> accounts = accountDAO.getAllAccounts();
        displayAccountResults(accounts);
    }
    
    private void displayDisabledAccounts() throws DatabaseException {
        List<Account> accounts = accountDAO.getDisabledAccounts();
        displayAccountResults(accounts);
    }
    
    private void queryAccounts() throws DatabaseException {
        boolean searchAgain = true;
        
        while (searchAgain) {
            System.out.println();
            System.out.print("Enter search value: ");
            String searchTerm = scanner.nextLine().trim();
            
            List<Account> accounts = accountDAO.searchAccounts(searchTerm);
            displayAccountResults(accounts);
            
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
        System.out.println("------|----------|----------|------------|------------------------|---------");
        
        for (Account account : accounts) {
            System.out.printf("%5d | %-8s | %-8s | %-10s | %-22s | %s%n",
                account.getId(),
                truncate(account.getFirstName(), 8),
                truncate(account.getLastName(), 8),
                truncate(account.getPhone(), 10),
                truncate(account.getAddress(), 22),
                account.getIsEnabled()
            );
        }
    }
    
    private String truncate(String str, int maxLength) {
        if (str == null) return "";
        return str.length() <= maxLength ? str : str.substring(0, maxLength);
    }
    
    private void handleDatabaseError(DatabaseException e) {
        System.out.println();
        System.out.println("Database Error:");
        if (e.getSqlCode() != 0) {
            System.out.println("SQLCODE: " + e.getSqlCode());
        }
        if (e.getSqlState() != null) {
            System.out.println("SQLSTATE: " + e.getSqlState());
        }
        System.out.println("ERROR MESSAGE: " + e.getMessage());
        System.out.println();
        
        System.out.println("Terminating application due to database error.");
        System.exit(1);
    }
}
