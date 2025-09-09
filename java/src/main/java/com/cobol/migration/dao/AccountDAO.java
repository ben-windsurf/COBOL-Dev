package com.cobol.migration.dao;

import com.cobol.migration.connection.DatabaseConnectionManager;
import com.cobol.migration.exception.DatabaseException;
import com.cobol.migration.model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private final DatabaseConnectionManager connectionManager;
    
    private static final String SELECT_ALL_ACCOUNTS = 
        "SELECT id, first_name, last_name, phone, address, is_enabled, create_dt, mod_dt " +
        "FROM accounts ORDER BY id";
    
    private static final String SELECT_DISABLED_ACCOUNTS = 
        "SELECT id, first_name, last_name, phone, address, is_enabled, create_dt, mod_dt " +
        "FROM accounts WHERE is_enabled = 'N' ORDER BY id";
    
    private static final String SEARCH_ACCOUNTS = 
        "SELECT id, first_name, last_name, phone, address, is_enabled, create_dt, mod_dt " +
        "FROM accounts WHERE first_name LIKE ? OR last_name LIKE ? OR phone LIKE ? OR address LIKE ? " +
        "ORDER BY id";
    
    public AccountDAO(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
    
    public List<Account> getAllAccounts() throws DatabaseException {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ACCOUNTS);
             ResultSet resultSet = statement.executeQuery()) {
            
            return mapResultSetToAccounts(resultSet);
            
        } catch (SQLException e) {
            throw new DatabaseException(
                "Failed to retrieve all accounts: " + e.getMessage(),
                e.getSQLState(),
                e.getErrorCode(),
                e
            );
        }
    }
    
    public List<Account> getDisabledAccounts() throws DatabaseException {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_DISABLED_ACCOUNTS);
             ResultSet resultSet = statement.executeQuery()) {
            
            return mapResultSetToAccounts(resultSet);
            
        } catch (SQLException e) {
            throw new DatabaseException(
                "Failed to retrieve disabled accounts: " + e.getMessage(),
                e.getSQLState(),
                e.getErrorCode(),
                e
            );
        }
    }
    
    public List<Account> searchAccounts(String searchTerm) throws DatabaseException {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String searchPattern = "%" + searchTerm.trim() + "%";
        
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SEARCH_ACCOUNTS)) {
            
            statement.setString(1, searchPattern);
            statement.setString(2, searchPattern);
            statement.setString(3, searchPattern);
            statement.setString(4, searchPattern);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                return mapResultSetToAccounts(resultSet);
            }
            
        } catch (SQLException e) {
            throw new DatabaseException(
                "Failed to search accounts: " + e.getMessage(),
                e.getSQLState(),
                e.getErrorCode(),
                e
            );
        }
    }
    
    private List<Account> mapResultSetToAccounts(ResultSet resultSet) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        
        while (resultSet.next()) {
            Account account = new Account();
            account.setId(resultSet.getInt("id"));
            account.setFirstName(resultSet.getString("first_name"));
            account.setLastName(resultSet.getString("last_name"));
            account.setPhone(resultSet.getString("phone"));
            account.setAddress(resultSet.getString("address"));
            account.setIsEnabled(resultSet.getString("is_enabled"));
            
            Timestamp createTs = resultSet.getTimestamp("create_dt");
            if (createTs != null) {
                account.setCreateDt(createTs.toLocalDateTime());
            }
            
            Timestamp modTs = resultSet.getTimestamp("mod_dt");
            if (modTs != null) {
                account.setModDt(modTs.toLocalDateTime());
            }
            
            accounts.add(account);
        }
        
        return accounts;
    }
}
