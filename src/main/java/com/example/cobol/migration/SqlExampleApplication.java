package com.example.cobol.migration;

import com.example.cobol.migration.dao.AccountDao;
import com.example.cobol.migration.model.Account;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * SQL Example Application demonstrating COBOL to Java migration.
 * Converted from COBOL sql_example.cbl program.
 */
public final class SqlExampleApplication {
    /** Data access object for account operations. */
    private final AccountDao accountDao;
    /** Scanner for user input. */
    private final Scanner scanner;
    /** Database connection status. */
    private boolean isConnected;

    /**
     * Default constructor.
     */
    public SqlExampleApplication() {
        this.accountDao = new AccountDao();
        this.scanner = new Scanner(System.in);
        this.isConnected = false;
    }

    /**
     * Main method to start the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        SqlExampleApplication app = new SqlExampleApplication();
        app.run();
    }

    /**
     * Main application loop.
     */
    public void run() {
        System.out.println();
        System.out.println("COBOL SQL DB Example Program");
        System.out.println("----------------------------");
        System.out.println();

        try {
            DatabaseConnectionManager connectionManager = 
                    DatabaseConnectionManager.getInstance();
            if (connectionManager.isConnected()) {
                this.isConnected = true;
                System.out.println(
                        "Database connection established successfully.");
            } else {
                throw new SQLException(
                        "Failed to establish database connection");
            }
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
            return;
        }

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
            }
        }

        try {
            DatabaseConnectionManager.getInstance().close();
            System.out.println("Disconnected.");
        } catch (Exception e) {
            System.err.println("Error during disconnect: " + e.getMessage());
        }

        System.out.println();
        scanner.close();
    }

    /**
     * Display all accounts from the database.
     */
    private void displayAllAccounts() {
        try {
            List<Account> accounts = accountDao.getAllAccounts();
            displayAccountResults(accounts, "All Accounts");
        } catch (SQLException e) {
            handleSqlException("Error retrieving all accounts", e);
        }
    }

    /**
     * Display only disabled accounts from the database.
     */
    private void displayDisabledAccounts() {
        try {
            List<Account> accounts = accountDao.getDisabledAccounts();
            displayAccountResults(accounts, "Disabled Accounts");
        } catch (SQLException e) {
            handleSqlException("Error retrieving disabled accounts", e);
        }
    }

    /**
     * Interactive account search functionality.
     * Allows user to search accounts by various criteria.
     */
    private void queryAccounts() {
        boolean searchAgain = true;

        while (searchAgain) {
            System.out.print("Enter search term (first name, last name, phone, "
                    + "or address): ");
            String searchTerm = scanner.nextLine().trim();

            if (searchTerm.isEmpty()) {
                System.out.println("Search term cannot be empty.");
                continue;
            }

            try {
                List<Account> accounts = accountDao.searchAccounts(searchTerm);
                displayAccountResults(accounts, 
                        "Search Results for: " + searchTerm);

                System.out.print("Search again? (Y/N): ");
                String response = scanner.nextLine().trim().toUpperCase();
                searchAgain = "Y".equals(response);

            } catch (SQLException e) {
                handleSqlException("Error searching accounts", e);
                searchAgain = false;
            }
        }
    }

    /**
     * Display account results in formatted table.
     *
     * @param accounts list of accounts to display
     * @param title title for the results section
     */
    private void displayAccountResults(final List<Account> accounts, 
            final String title) {
        System.out.println();
        final int lineWidth = 80;
        System.out.println("=".repeat(lineWidth));
        System.out.println(title);
        System.out.println("=".repeat(lineWidth));

        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        System.out.printf("%-5s %-10s %-10s %-12s %-25s %-8s%n",
                "ID", "First", "Last", "Phone", "Address", "Enabled");
        System.out.println("-".repeat(lineWidth));

        for (Account account : accounts) {
            System.out.printf("%-5d %-10s %-10s %-12s %-25s %-8s%n",
                    account.getId(),
                    truncate(account.getFirstName(), 10),
                    truncate(account.getLastName(), 10),
                    account.getPhone(),
                    truncate(account.getAddress(), 25),
                    account.isEnabled() ? "Y" : "N");
        }

        System.out.println();
        System.out.println("Total records: " + accounts.size());
    }

    /**
     * Truncate string to specified length with ellipsis.
     *
     * @param str string to truncate
     * @param maxLength maximum length allowed
     * @return truncated string with ellipsis if needed
     */
    private String truncate(final String str, final int maxLength) {
        if (str == null) {
            return "";
        }
        final int ellipsisLength = 3;
        return str.length() > maxLength 
                ? str.substring(0, maxLength - ellipsisLength) + "..." : str;
    }

    /**
     * Handle SQL exceptions with detailed error reporting.
     *
     * @param message descriptive error message
     * @param e the SQL exception to handle
     */
    private void handleSqlException(final String message, 
            final SQLException e) {
        System.err.println(message + ": " + e.getMessage());
        System.err.println("SQL State: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());

        String sqlState = e.getSQLState();
        if ("08001".equals(sqlState) || "08006".equals(sqlState)) {
            System.err.println("Database connection problem. "
                    + "Please check your connection settings.");
        } else if ("42000".equals(sqlState)) {
            System.err.println("SQL syntax error. Please contact support.");
        } else if ("23000".equals(sqlState)) {
            System.err.println("Data integrity constraint violation.");
        }
    }
}
