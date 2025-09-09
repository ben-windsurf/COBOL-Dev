package com.coboldev.migration;

public class DatabaseConfig {
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 5432;
    public static final String DEFAULT_DATABASE = "cobol_db_example";
    public static final String DEFAULT_USERNAME = "postgres";
    public static final String DEFAULT_PASSWORD = "password";
    
    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;
    
    public DatabaseConfig() {
        this(DEFAULT_HOST, DEFAULT_PORT, DEFAULT_DATABASE, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }
    
    public DatabaseConfig(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }
    
    public String buildJdbcUrl() {
        return String.format("jdbc:postgresql://%s:%d/%s", host, port, database);
    }
    
    public String getHost() {
        return host;
    }
    
    public int getPort() {
        return port;
    }
    
    public String getDatabase() {
        return database;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public static DatabaseConfig fromOdbcConnectionString(String odbcConnectionString) {
        String host = DEFAULT_HOST;
        int port = DEFAULT_PORT;
        String database = DEFAULT_DATABASE;
        String username = DEFAULT_USERNAME;
        String password = DEFAULT_PASSWORD;
        
        if (odbcConnectionString != null) {
            String[] parts = odbcConnectionString.split(";");
            for (String part : parts) {
                String[] keyValue = part.split("=", 2);
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim().toUpperCase();
                    String value = keyValue[1].trim();
                    
                    switch (key) {
                        case "SERVER":
                            host = value;
                            break;
                        case "PORT":
                            try {
                                port = Integer.parseInt(value);
                            } catch (NumberFormatException e) {
                                port = DEFAULT_PORT;
                            }
                            break;
                        case "DATABASE":
                            database = value;
                            break;
                        case "UID":
                            username = value;
                            break;
                        case "PWD":
                            password = value;
                            break;
                    }
                }
            }
        }
        
        return new DatabaseConfig(host, port, database, username, password);
    }
}
