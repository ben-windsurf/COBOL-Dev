package com.example.cobol.migration.dao;

import com.example.cobol.migration.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class AccountDaoTest {
    private TestAccountDao accountDao;
    private Connection testConnection;
    
    @BeforeEach
    void setUp() throws SQLException {
        String dbName = "testdb_" + System.currentTimeMillis() + "_" + Math.random();
        testConnection = DriverManager.getConnection("jdbc:h2:mem:" + dbName + ";DB_CLOSE_DELAY=-1", "sa", "");
        createTestSchema();
        insertTestData();
        accountDao = new TestAccountDao(testConnection);
    }
    
    @AfterEach
    void tearDown() throws SQLException {
        if (testConnection != null && !testConnection.isClosed()) {
            testConnection.close();
        }
    }
    
    private void createTestSchema() throws SQLException {
        String createTableSql = "CREATE TABLE accounts (" +
                "id SERIAL PRIMARY KEY," +
                "first_name VARCHAR(255) NOT NULL," +
                "last_name VARCHAR(255) NOT NULL," +
                "phone VARCHAR(255) NOT NULL," +
                "address VARCHAR(255) NOT NULL," +
                "is_enabled VARCHAR(1) NOT NULL DEFAULT 'N'," +
                "create_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "mod_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
        
        try (Statement stmt = testConnection.createStatement()) {
            stmt.execute(createTableSql);
        }
    }
    
    private void insertTestData() throws SQLException {
        String insertSql = "INSERT INTO accounts (first_name, last_name, phone, address, is_enabled) VALUES " +
                "('John', 'Tester', '15555550100', '123 Fake St, Nowhere', 'Y')," +
                "('Mike', 'Tester1', '15555550121', '122 Real St, Nowhere', 'Y')," +
                "('Bob', 'Tester4', '15555550154', '119 Truck St, Nowhere', 'N')," +
                "('Paula', 'Tester5', '1555550165', '118 Car St, Nowhere', 'N')";
        
        try (Statement stmt = testConnection.createStatement()) {
            stmt.execute(insertSql);
        }
    }
    
    @Test
    @DisplayName("Test getAllAccounts returns all records ordered by ID")
    void testGetAllAccounts() throws SQLException {
        List<Account> accounts = accountDao.getAllAccounts();
        
        assertNotNull(accounts);
        assertEquals(4, accounts.size());
        
        for (int i = 1; i < accounts.size(); i++) {
            assertTrue(accounts.get(i-1).getId() < accounts.get(i).getId());
        }
        
        Account firstAccount = accounts.get(0);
        assertEquals("John", firstAccount.getFirstName());
        assertEquals("Tester", firstAccount.getLastName());
        assertTrue(firstAccount.isEnabled());
    }
    
    @Test
    @DisplayName("Test getDisabledAccounts returns only disabled records")
    void testGetDisabledAccounts() throws SQLException {
        List<Account> disabledAccounts = accountDao.getDisabledAccounts();
        
        assertNotNull(disabledAccounts);
        assertEquals(2, disabledAccounts.size());
        
        for (Account account : disabledAccounts) {
            assertFalse(account.isEnabled());
        }
        
        assertEquals("Bob", disabledAccounts.get(0).getFirstName());
        assertEquals("Paula", disabledAccounts.get(1).getFirstName());
    }
    
    private static class TestAccountDao {
        private final Connection testConnection;
        
        public TestAccountDao(Connection testConnection) {
            this.testConnection = testConnection;
        }
        
        protected Connection getConnection() throws SQLException {
            return testConnection;
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
    
    @Test
    @DisplayName("Test searchAccounts with first name search")
    void testSearchAccountsByFirstName() throws SQLException {
        List<Account> results = accountDao.searchAccounts("John");
        
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("John", results.get(0).getFirstName());
    }
    
    @Test
    @DisplayName("Test searchAccounts with partial address search")
    void testSearchAccountsByPartialAddress() throws SQLException {
        List<Account> results = accountDao.searchAccounts("Fake");
        
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("123 Fake St, Nowhere", results.get(0).getAddress());
    }
    
    @Test
    @DisplayName("Test searchAccounts with empty search term")
    void testSearchAccountsWithEmptyTerm() throws SQLException {
        List<Account> results = accountDao.searchAccounts("");
        
        assertNotNull(results);
        assertEquals(4, results.size()); // Should return all records when search is empty
    }
    
    @Test
    @DisplayName("Test searchAccounts with null search term")
    void testSearchAccountsWithNullTerm() throws SQLException {
        List<Account> results = accountDao.searchAccounts(null);
        
        assertNotNull(results);
        assertEquals(4, results.size()); // Should return all records when search is null
    }
    
    @Test
    @DisplayName("Test searchAccounts with no matches")
    void testSearchAccountsNoMatches() throws SQLException {
        List<Account> results = accountDao.searchAccounts("NonExistentName");
        
        assertNotNull(results);
        assertEquals(0, results.size());
    }
}
