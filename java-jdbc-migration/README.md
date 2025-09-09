# JDBC Migration from COBOL esqlOC

This Java application replaces the COBOL esqlOC precompiler implementation with a modern JDBC-based solution for PostgreSQL database connectivity.

## Overview

This project migrates the functionality from the original COBOL implementation in `../sql/sql_example.cbl` to Java using JDBC. It eliminates the need for the esqlOC precompiler while maintaining the same database operations and user interface.

## Original COBOL Implementation

The original implementation used:
- esqlOC precompiler to convert embedded SQL to OCSQL calls
- unixODBC with PostgreSQL driver for database connectivity
- COBOL cursors for result set processing
- Manual SQLSTATE/SQLCODE error checking

## New Java Implementation

The Java version provides:
- Direct JDBC connectivity to PostgreSQL
- PreparedStatement for SQL injection protection
- Modern Java exception handling
- Resource management with try-with-resources
- Maven build system

## Database Operations

Both implementations support the same three operations:

1. **Display all accounts**: `SELECT * FROM ACCOUNTS ORDER BY ID`
2. **Display disabled accounts**: `SELECT * FROM ACCOUNTS WHERE IS_ENABLED = 'N' ORDER BY ID`
3. **Query accounts**: Parameterized LIKE search across FIRST_NAME, LAST_NAME, PHONE, and ADDRESS fields

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- PostgreSQL database instance
- Database created using `../sql/create_test_db.sql`

## Database Configuration

The application connects to PostgreSQL using the same parameters as the COBOL version:
- Server: localhost
- Port: 5432
- Database: cobol_db_example
- Username: postgres
- Password: Set via `DB_PASSWORD` environment variable or `db.password` system property

### Setting Database Password

For security, the database password must be provided via environment variable:
```bash
export DB_PASSWORD=password
```

Or as a system property:
```bash
mvn exec:java -Ddb.password=password -Dexec.mainClass="com.example.jdbc.SqlExampleApplication"
```

## Build Process

### Compile the project:
```bash
mvn clean compile
```

### Run the application:
```bash
mvn exec:java -Dexec.mainClass="com.example.jdbc.SqlExampleApplication"
```

### Create executable JAR:
```bash
mvn clean package
java -jar target/jdbc-migration-1.0.0.jar
```

### Run tests:
```bash
mvn test
```

## Key Differences from COBOL Version

### Build Process
- **COBOL**: Required esqlOC precompiler step: `esqlOC -static -o generated_sql_ex.cbl sql_example.cbl`
- **Java**: Direct compilation with Maven: `mvn clean compile`

### Database Connectivity
- **COBOL**: ODBC connection string with driver specification
- **Java**: JDBC URL with connection properties

### SQL Execution
- **COBOL**: Cursor-based processing with OPEN/FETCH/CLOSE
- **Java**: ResultSet iteration with try-with-resources

### Error Handling
- **COBOL**: Manual SQLSTATE/SQLCODE checking in `check-sql-state` paragraph
- **Java**: SQLException handling with equivalent error reporting

### Variable-Length Strings
- **COBOL**: Used `ws-search-value` structure with length and text fields
- **Java**: String trimming and wildcard addition: `"%" + searchTerm.trim() + "%"`

## Project Structure

```
java-jdbc-migration/
├── pom.xml                                    # Maven configuration
├── README.md                                  # This file
└── src/
    └── main/
        └── java/
            └── com/
                └── example/
                    └── jdbc/
                        ├── SqlExampleApplication.java      # Main application
                        ├── config/
                        │   └── DatabaseConnectionFactory.java  # Connection management
                        ├── model/
                        │   └── Account.java                # Account data model
                        ├── service/
                        │   └── AccountService.java         # Database operations
                        └── util/
                            └── SqlErrorHandler.java        # Error handling
```

## Testing

To test the application:

1. Ensure PostgreSQL is running and the test database is created:
   ```bash
   psql -U postgres -f ../sql/create_test_db.sql
   ```

2. Run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="com.example.jdbc.SqlExampleApplication"
   ```

3. Test each menu option:
   - Option 1: Should display all 11 test accounts
   - Option 2: Should display 3 disabled accounts (Bob, Paula, Bill)
   - Option 3: Should allow searching by name, phone, or address

## Migration Benefits

1. **Simplified Build Process**: No precompiler step required
2. **Modern Error Handling**: Structured exception handling vs manual state checking
3. **SQL Injection Protection**: PreparedStatement prevents injection attacks
4. **Resource Management**: Automatic connection cleanup
5. **Maintainability**: Object-oriented design with separation of concerns
6. **Portability**: Standard JDBC works across different environments

## Jira Ticket

This implementation addresses ticket **ET-51**: "Replace esqlOC precompiler with JDBC setup"
