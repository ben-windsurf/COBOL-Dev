package com.example.jdbc.service;

import com.example.jdbc.config.DatabaseConnectionFactory;
import com.example.jdbc.model.Account;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountService {
    
    private final DatabaseConnectionFactory connectionFactory;
    
    private static final String SELECT_ALL_ACCOUNTS = 
        "SELECT ID, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, IS_ENABLED, CREATE_DT, MOD_DT " +
        "FROM ACCOUNTS ORDER BY ID";
    
    private static final String SELECT_DISABLED_ACCOUNTS = 
        "SELECT ID, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, IS_ENABLED, CREATE_DT, MOD_DT " +
        "FROM ACCOUNTS WHERE IS_ENABLED = 'N' ORDER BY ID";
    
    private static final String SELECT_ACCOUNTS_BY_SEARCH = 
        "SELECT ID, FIRST_NAME, LAST_NAME, PHONE, ADDRESS, IS_ENABLED, CREATE_DT, MOD_DT " +
        "FROM ACCOUNTS WHERE " +
        "FIRST_NAME LIKE ? OR LAST_NAME LIKE ? OR PHONE LIKE ? OR ADDRESS LIKE ? " +
        "ORDER BY ID";
    
    public AccountService(DatabaseConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
    
    public List<Account> getAllAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        
        try (Connection connection = connectionFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ACCOUNTS);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                accounts.add(mapResultSetToAccount(resultSet));
            }
        }
        
        return accounts;
    }
    
    public List<Account> getDisabledAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        
        try (Connection connection = connectionFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_DISABLED_ACCOUNTS);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                accounts.add(mapResultSetToAccount(resultSet));
            }
        }
        
        return accounts;
    }
    
    public List<Account> queryAccounts(String searchTerm) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        
        String searchPattern = "%" + searchTerm.trim() + "%";
        
        try (Connection connection = connectionFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ACCOUNTS_BY_SEARCH)) {
            
            statement.setString(1, searchPattern);
            statement.setString(2, searchPattern);
            statement.setString(3, searchPattern);
            statement.setString(4, searchPattern);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    accounts.add(mapResultSetToAccount(resultSet));
                }
            }
        }
        
        return accounts;
    }
    
    private Account mapResultSetToAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        
        account.setId(resultSet.getInt("ID"));
        account.setFirstName(resultSet.getString("FIRST_NAME"));
        account.setLastName(resultSet.getString("LAST_NAME"));
        account.setPhone(resultSet.getString("PHONE"));
        account.setAddress(resultSet.getString("ADDRESS"));
        account.setIsEnabled(resultSet.getString("IS_ENABLED"));
        
        Timestamp createTs = resultSet.getTimestamp("CREATE_DT");
        if (createTs != null) {
            account.setCreateDt(createTs.toLocalDateTime());
        }
        
        Timestamp modTs = resultSet.getTimestamp("MOD_DT");
        if (modTs != null) {
            account.setModDt(modTs.toLocalDateTime());
        }
        
        return account;
    }
}
