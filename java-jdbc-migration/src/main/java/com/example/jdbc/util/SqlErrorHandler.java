package com.example.jdbc.util;

import java.sql.SQLException;

public class SqlErrorHandler {
    
    public static void handleSqlException(SQLException e) {
        System.out.println();
        System.out.println("SQL Error:");
        System.out.println("SQLCODE: " + e.getErrorCode());
        System.out.println("SQLSTATE: " + e.getSQLState());
        
        String message = e.getMessage();
        if (message != null && !message.trim().isEmpty()) {
            System.out.println("ERROR MESSAGE: " + message);
        }
        System.out.println();
    }
    
    public static boolean isNoDataFound(SQLException e) {
        return "02000".equals(e.getSQLState());
    }
    
    public static boolean isSuccess(SQLException e) {
        return "00000".equals(e.getSQLState());
    }
    
    public static void checkSqlState(SQLException e) throws SQLException {
        if (isSuccess(e) || isNoDataFound(e)) {
            return;
        }
        
        handleSqlException(e);
        throw e;
    }
}
