package com.example.cobol.migration.dao;

import com.example.cobol.migration.DatabaseConnectionManager;
import com.example.cobol.migration.model.Account;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private final DatabaseConnectionManager connectionManager;
    
    public AccountDao() {
        this.connectionManager = DatabaseConnectionManager.getInstance();
    }
    
    protected Connection getConnection() throws SQLException {
        return connectionManager.getConnection();
    }
    
    public List<Account> getAllAccounts() throws SQLException {
        String sql = "SELECT id, first_name, last_name, phone, address, is_enabled, create_dt, mod_dt " +
                    "FROM accounts ORDER BY id";
        
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
    
    public List<Account> getDisabledAccounts() throws SQLException {
        String sql = "SELECT id, first_name, last_name, phone, address, is_enabled, create_dt, mod_dt " +
                    "FROM accounts WHERE is_enabled = 'N' ORDER BY id";
        
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
    
    public List<Account> searchAccounts(String searchTerm) throws SQLException {
        String trimmedSearchTerm = searchTerm != null ? searchTerm.trim() : "";
        String likePattern = "%" + trimmedSearchTerm + "%";
        
        String sql = "SELECT id, first_name, last_name, phone, address, is_enabled, create_dt, mod_dt " +
                    "FROM accounts WHERE " +
                    "first_name LIKE ? OR last_name LIKE ? OR phone LIKE ? OR address LIKE ? " +
                    "ORDER BY id";
        
        List<Account> accounts = new ArrayList<>();
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, likePattern);
            stmt.setString(2, likePattern);
            stmt.setString(3, likePattern);
            stmt.setString(4, likePattern);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Account account = mapResultSetToAccount(rs);
                    accounts.add(account);
                }
            }
        }
        
        return accounts;
    }
    
    private Account mapResultSetToAccount(ResultSet rs) throws SQLException {
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
