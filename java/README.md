# COBOL to Java Migration - Database Connection Manager

This Java project provides a `DatabaseConnectionManager` class that replaces ODBC database connections with JDBC for PostgreSQL databases. This is part of the COBOL to Java migration project (ticket ET-52).

## Overview

The original COBOL application uses ODBC to connect to PostgreSQL with this connection string:
```
DRIVER={PostgreSQL Unicode};SERVER=localhost;PORT=5432;DATABASE=cobol_db_example;UID=postgres;PWD=password;COMRESSED_PROTO=0;
```

This Java implementation converts the ODBC connection string to the proper JDBC URL format:
```
jdbc:postgresql://localhost:5432/cobol_db_example
```

## Key Features

- **ODBC to JDBC Conversion**: Automatically parses ODBC connection strings and converts them to JDBC format
- **Connection Management**: Proper connection establishment using `DriverManager.getConnection()`
- **Error Handling**: Comprehensive exception handling for connection failures
- **Resource Management**: Automatic resource cleanup using try-with-resources
- **Authentication**: Proper handling of username/password authentication
- **Configuration**: Flexible configuration through `DatabaseConfig` class

## Classes

### DatabaseConnectionManager
Main class that handles database connections using JDBC instead of ODBC.

**Key Methods:**
- `getConnection(DatabaseConfig config)` - Establish connection using configuration object
- `getConnectionFromOdbcString(String odbcConnectionString)` - Convert ODBC string and establish connection
- `getDefaultConnection()` - Connect using default parameters
- `testConnection(DatabaseConfig config)` - Test if connection can be established
- `closeConnection(Connection connection)` - Safely close database connection

### DatabaseConfig
Configuration class that stores database connection parameters and provides ODBC to JDBC conversion.

**Key Methods:**
- `buildJdbcUrl()` - Generate JDBC URL from configuration
- `fromOdbcConnectionString(String odbcConnectionString)` - Parse ODBC string and create config

## ODBC to JDBC Parameter Mapping

| ODBC Parameter | JDBC Equivalent | Notes |
|----------------|-----------------|-------|
| `DRIVER={PostgreSQL Unicode}` | `jdbc:postgresql://` | Driver specified in URL protocol |
| `SERVER=localhost` | `localhost` in URL | Host part of JDBC URL |
| `PORT=5432` | `:5432` in URL | Port part of JDBC URL |
| `DATABASE=cobol_db_example` | `/cobol_db_example` in URL | Database name in URL |
| `UID=postgres` | `user` property | Username in connection properties |
| `PWD=password` | `password` property | Password in connection properties |
| `COMRESSED_PROTO=0` | *Removed* | ODBC-specific parameter not needed in JDBC |

## Usage Examples

### Basic Usage
```java
DatabaseConnectionManager manager = new DatabaseConnectionManager();

// Using default configuration
try (Connection conn = manager.getDefaultConnection()) {
    // Use connection for database operations
}

// Using custom configuration
DatabaseConfig config = new DatabaseConfig("localhost", 5432, "mydb", "user", "pass");
try (Connection conn = manager.getConnection(config)) {
    // Use connection for database operations
}
```

### Converting from ODBC Connection String
```java
DatabaseConnectionManager manager = new DatabaseConnectionManager();
String odbcString = "DRIVER={PostgreSQL Unicode};SERVER=localhost;PORT=5432;DATABASE=cobol_db_example;UID=postgres;PWD=password;COMRESSED_PROTO=0;";

try (Connection conn = manager.getConnectionFromOdbcString(odbcString)) {
    // Use connection for database operations
}
```

### Testing Connection
```java
DatabaseConnectionManager manager = new DatabaseConnectionManager();
DatabaseConfig config = new DatabaseConfig();

if (manager.testConnection(config)) {
    System.out.println("Database connection successful");
} else {
    System.out.println("Database connection failed");
}
```

## Prerequisites

- Java 8 or higher
- PostgreSQL database instance
- PostgreSQL JDBC driver (automatically included via Maven dependency)

## Database Schema

The implementation works with the database schema defined in `../sql/create_test_db.sql`:

```sql
CREATE TABLE accounts ( 
    id serial not null, 
    first_name varchar not null, 
    last_name varchar not null, 
    phone varchar not null, 
    address varchar not null, 
    is_enabled varchar(1) not null default 'N', 
    create_dt timestamp default now(), 
    mod_dt timestamp default now(), 
    primary key (id) 
);
```

## Building and Testing

### Compile the project
```bash
mvn clean compile
```

### Run tests
```bash
mvn test
```

### Package the project
```bash
mvn package
```

## Dependencies

- **PostgreSQL JDBC Driver**: Version 42.7.7
- **JUnit**: Version 4.13.2 (for testing)

## Error Handling

The implementation includes comprehensive error handling:

- **Invalid Configuration**: Throws `IllegalArgumentException` for null or invalid configurations
- **Connection Failures**: Throws `SQLException` with descriptive error messages
- **Driver Loading**: Throws `RuntimeException` if PostgreSQL JDBC driver is not found
- **Resource Cleanup**: Automatically closes connections and handles cleanup errors gracefully

## Security Considerations

- Credentials are handled through JDBC connection properties
- No hardcoded passwords in production code
- Connection strings should be externalized in production environments
- Use connection pooling for production applications

## Migration Notes

This implementation replaces the COBOL ODBC connection mechanism found in:
- `../sql/sql_example.cbl` (lines 35-42) - Original ODBC connection string
- `../sql/generated_sql_ex.cbl` (lines 97-104) - Generated COBOL code with ODBC calls

The Java implementation provides equivalent functionality with modern JDBC standards and better error handling.
