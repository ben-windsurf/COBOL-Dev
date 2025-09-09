package com.example.cobol.migration.dao;

import com.example.cobol.migration.DatabaseConnectionManager;
import com.example.cobol.migration.model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Account operations.
 * Converted from COBOL database access patterns to JDBC-based operations.
 * Provides CRUD operations for account data with proper exception handling.
 */
public class AccountDao {
    /** Database connection manager for handling connections. */
    private final DatabaseConnectionManager connectionManager;

    /**
     * Default constructor.
     */
    public AccountDao() {
        this.connectionManager = DatabaseConnectionManager.getInstance();
    }

    /**
     * Get database connection from connection manager.
     *
     * @return database connection
     * @throws SQLException if connection fails
     */
    protected Connection getConnection() throws SQLException {
        return connectionManager.getConnection();
    }

    /**
     * Retrieve all accounts from the database.
     *
     * @return list of all accounts
     * @throws SQLException if database operation fails
     */
    public List<Account> getAllAccounts() throws SQLException {
        String sql = "SELECT id, first_name, last_name, phone, address, "
                    + "is_enabled, create_dt, mod_dt "
                    + "FROM accounts ORDER BY id";

        List<Account> accounts = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Account account = mapResultSetToAccount(rs);
                accounts.add(account);
            }
        }

        return accounts;
    }

    /**
     * Retrieve only disabled accounts from the database.
     *
     * @return list of disabled accounts
     * @throws SQLException if database operation fails
     */
    public List<Account> getDisabledAccounts() throws SQLException {
        String sql = "SELECT id, first_name, last_name, phone, address, "
                    + "is_enabled, create_dt, mod_dt "
                    + "FROM accounts WHERE is_enabled = 'N' ORDER BY id";

        List<Account> accounts = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Account account = mapResultSetToAccount(rs);
                accounts.add(account);
            }
        }

        return accounts;
    }

    /**
     * Search accounts by term across multiple fields.
     *
     * @param searchTerm search criteria
     * @return list of matching accounts
     * @throws SQLException if database operation fails
     */
    public List<Account> searchAccounts(final String searchTerm) throws SQLException {
        String trimmedSearchTerm = searchTerm != null ? searchTerm.trim() : "";
        String likePattern = "%" + trimmedSearchTerm + "%";

        String sql = "SELECT id, first_name, last_name, phone, address, "
                    + "is_enabled, create_dt, mod_dt "
                    + "FROM accounts WHERE "
                    + "first_name LIKE ? OR last_name LIKE ? OR phone LIKE ? "
                    + "OR address LIKE ? "
                    + "ORDER BY id";

        List<Account> accounts = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            final int firstNameParam = 1;
            final int lastNameParam = 2;
            final int phoneParam = 3;
            final int addressParam = 4;

            stmt.setString(firstNameParam, likePattern);
            stmt.setString(lastNameParam, likePattern);
            stmt.setString(phoneParam, likePattern);
            stmt.setString(addressParam, likePattern);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Account account = mapResultSetToAccount(rs);
                    accounts.add(account);
                }
            }
        }

        return accounts;
    }

    /**
     * Map database result set to Account object.
     *
     * @param rs database result set
     * @return mapped Account object
     * @throws SQLException if mapping fails
     */
    private Account mapResultSetToAccount(final ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setFirstName(rs.getString("first_name"));
        account.setLastName(rs.getString("last_name"));
        account.setPhone(rs.getString("phone"));
        account.setAddress(rs.getString("address"));

        String enabledFlag = rs.getString("is_enabled");
        account.setEnabled("Y".equals(enabledFlag));

        Timestamp createTs = rs.getTimestamp("create_dt");
        if (createTs != null) {
            account.setCreateDate(createTs.toLocalDateTime());
        }

        Timestamp modTs = rs.getTimestamp("mod_dt");
        if (modTs != null) {
            account.setModDate(modTs.toLocalDateTime());
        }

        return account;
    }
}
