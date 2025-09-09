package com.cobol.migration.exception;

public class DatabaseException extends Exception {
    private final String sqlState;
    private final int sqlCode;
    
    public DatabaseException(String message) {
        super(message);
        this.sqlState = null;
        this.sqlCode = 0;
    }
    
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
        this.sqlState = null;
        this.sqlCode = 0;
    }
    
    public DatabaseException(String message, String sqlState, int sqlCode) {
        super(message);
        this.sqlState = sqlState;
        this.sqlCode = sqlCode;
    }
    
    public DatabaseException(String message, String sqlState, int sqlCode, Throwable cause) {
        super(message, cause);
        this.sqlState = sqlState;
        this.sqlCode = sqlCode;
    }
    
    public String getSqlState() {
        return sqlState;
    }
    
    public int getSqlCode() {
        return sqlCode;
    }
    
    public boolean isSuccess() {
        return "00000".equals(sqlState);
    }
    
    public boolean isNoData() {
        return "02000".equals(sqlState);
    }
    
    public boolean isDuplicate() {
        return sqlState != null && sqlState.startsWith("23");
    }
}
