package com.example.cobol.migration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * Database Connection Manager for COBOL to Java migration.
 * Provides connection pooling and database connectivity management.
 */
public final class DatabaseConnectionManager {
    /** Singleton instance of the connection manager. */
    private static DatabaseConnectionManager instance;
    /** HikariCP data source for connection pooling. */
    private HikariDataSource dataSource;

    /**
     * Private constructor for singleton pattern.
     */
    private DatabaseConnectionManager() {
        initializeConnectionPool();
    }

    /**
     * Get singleton instance of DatabaseConnectionManager.
     *
     * @return singleton instance
     */
    public static synchronized DatabaseConnectionManager getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    /**
     * Initialize HikariCP connection pool with configuration.
     */
    private void initializeConnectionPool() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:postgresql://localhost:5432/cobol_db_example");
        config.setUsername("postgres");
        config.setPassword("password");

        final int maxPoolSize = 10;
        final int minIdleConnections = 2;
        final int connectionTimeoutMs = 30000;
        final int idleTimeoutMs = 600000;
        final int maxLifetimeMs = 1800000;
        final int leakDetectionMs = 60000;

        config.setMaximumPoolSize(maxPoolSize);
        config.setMinimumIdle(minIdleConnections);
        config.setConnectionTimeout(connectionTimeoutMs);
        config.setIdleTimeout(idleTimeoutMs);
        config.setMaxLifetime(maxLifetimeMs);
        config.setLeakDetectionThreshold(leakDetectionMs);

        config.setConnectionTestQuery("SELECT 1");
        final int validationTimeoutMs = 5000;
        config.setValidationTimeout(validationTimeoutMs);

        this.dataSource = new HikariDataSource(config);
    }

    /**
     * Get database connection from the pool.
     *
     * @return database connection
     * @throws SQLException if connection fails
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Close the data source and all connections.
     */
    public void close() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

    /**
     * Check if database connection is available.
     *
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
