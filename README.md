# COBOL Development Examples

This repository contains practical examples and demonstrations of COBOL programming features, including database connectivity implementations. It serves as a learning platform for COBOL developers and demonstrates modern integration techniques.

## Overview

The repository provides two main approaches for database connectivity:

### 1. Original COBOL Implementation
- **Location**: `sql/` directory
- **Technology**: COBOL with esqlOC precompiler and ODBC
- **Target**: PostgreSQL database connectivity using embedded SQL
- **Build Process**: `esqlOC` preprocessing followed by `cobc` compilation

### 2. Java JDBC Migration (NEW)
- **Location**: `java-jdbc-migration/` directory  
- **Technology**: Java with JDBC (Maven-based project)
- **Target**: Direct PostgreSQL connectivity without preprocessing
- **Build Process**: Standard Maven compilation (`mvn clean compile`)

Both implementations provide identical functionality with the same three SQL operations:
- Display all accounts
- Display disabled accounts  
- Parameterized search across multiple fields

## Database Examples

### SQL Directory (`sql/`)
Contains the original COBOL implementation with embedded SQL:
- `sql_example.cbl` - Original COBOL source with embedded SQL statements
- `generated_sql_ex.cbl` - esqlOC preprocessor output with OCSQL calls
- `create_test_db.sql` - Database schema and test data setup
- `README.md` - COBOL build process and usage instructions

### Java JDBC Migration (`java-jdbc-migration/`)
Modern Java implementation that replaces the esqlOC preprocessing step:
- Complete Maven project structure with PostgreSQL JDBC driver
- Object-oriented design with separation of concerns
- Secure credential management via environment variables
- PreparedStatement usage for SQL injection protection
- Comprehensive documentation and build instructions

## Getting Started

### Prerequisites
- **For COBOL**: GnuCOBOL compiler, esqlOC precompiler, unixODBC
- **For Java**: Java 11+, Maven 3.6+, PostgreSQL JDBC driver (auto-downloaded)
- **Database**: PostgreSQL server with test database

### Quick Start - Java Version (Recommended)
```bash
cd java-jdbc-migration/
export DB_PASSWORD=password
mvn clean compile
mvn exec:java -Dexec.mainClass="com.example.jdbc.SqlExampleApplication"
```

### Quick Start - COBOL Version
```bash
cd sql/
# Follow build instructions in sql/README.md
```

## Migration Benefits (COBOL → Java)

| Aspect | COBOL + esqlOC | Java + JDBC |
|--------|----------------|-------------|
| **Build Process** | `esqlOC` → `cobc` | `mvn compile` |
| **Dependencies** | esqlOC, unixODBC, OCSQL | PostgreSQL JDBC driver |
| **Security** | Hardcoded credentials | Environment variables |
| **SQL Injection** | Manual escaping | PreparedStatement |
| **Error Handling** | SQLSTATE/SQLCODE | SQLException |
| **Maintainability** | Procedural | Object-oriented |

## Other COBOL Examples

The repository also contains various other COBOL programming examples:
- Input/Output operations (`accept/`, `display_test/`)
- Data processing (`xml_generate/`, `json_generate/`, `trim/`)
- Program structure (`sub_program/`, `search/`)
- And many more practical demonstrations

All COBOL programs were written using [GnuCOBOL](https://gnucobol.sourceforge.io/) in Linux.

## Contributing

Each example directory contains its own README.md with specific build instructions and usage examples. The codebase emphasizes practical, executable code over theoretical documentation.    



