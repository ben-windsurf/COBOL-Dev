# COBOL to Java Migration

This directory contains Java equivalents of all the COBOL programs in the COBOL-Dev repository. Each Java program maintains the same functionality and demonstrates the same programming concepts as its COBOL counterpart.

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- PostgreSQL (for database examples)

## Building the Project

```bash
mvn compile
```

## Running Individual Examples

Each example can be run using Maven:

```bash
# JSON Generation Example
mvn exec:java -Dexec.mainClass="com.example.jsongenerate.JsonGenerateExample"

# XML Generation Example  
mvn exec:java -Dexec.mainClass="com.example.xmlgenerate.XmlGenerateExample"

# SQL Database Example (requires PostgreSQL setup)
mvn exec:java -Dexec.mainClass="com.example.sql.SqlExample"

# Input/Output Examples
mvn exec:java -Dexec.mainClass="com.example.accept.AcceptExample"

# String Processing Examples
mvn exec:java -Dexec.mainClass="com.example.unstring.UnstringExample"
mvn exec:java -Dexec.mainClass="com.example.trim.TrimFunctionTest"

# Search Examples
mvn exec:java -Dexec.mainClass="com.example.search.SearchExample"

# Sub-program Examples
mvn exec:java -Dexec.mainClass="com.example.subprogram.MainApp"

# Data Validation Examples
mvn exec:java -Dexec.mainClass="com.example.isnumeric.IsNumericTest"

# Display and Terminal Examples
mvn exec:java -Dexec.mainClass="com.example.display.DisplayTest"
mvn exec:java -Dexec.mainClass="com.example.mouse.MouseExample"

# Command Line Processing
mvn exec:java -Dexec.mainClass="com.example.commandargs.ReadCommandArgs" -Dexec.args="--test arg1 arg2"

# Other Examples
mvn exec:java -Dexec.mainClass="com.example.comp.CompTest"
mvn exec:java -Dexec.mainClass="com.example.screensize.GetScreenSize"
mvn exec:java -Dexec.mainClass="com.example.mergesort.MergeSortTest"
mvn exec:java -Dexec.mainClass="com.example.redefines.RedefinesExample"
mvn exec:java -Dexec.mainClass="com.example.numval.NumvalTest"
mvn exec:java -Dexec.mainClass="com.example.reportwriter.ReportTest"
mvn exec:java -Dexec.mainClass="com.example.displaytiming.DisplayTiming"
```

## COBOL to Java Mapping

### Key Concepts Translated

1. **JSON GENERATE** → Jackson ObjectMapper
2. **XML GENERATE** → JAXB marshalling  
3. **Embedded SQL** → JDBC PreparedStatements
4. **ACCEPT statements** → Scanner and Console input
5. **DISPLAY statements** → System.out with ANSI escape codes
6. **UNSTRING** → String.split() and StringTokenizer
7. **SEARCH/SEARCH ALL** → Collections.binarySearch() and stream filtering
8. **Sub-programs** → Method calls with parameter passing
9. **Working-storage** → Instance variables
10. **Local-storage** → Local method variables

### Database Setup (for SQL examples)

The SQL example requires a PostgreSQL database. Create the database and table:

```sql
CREATE DATABASE cobol_db_example;

CREATE TABLE ACCOUNTS (
    ID SERIAL PRIMARY KEY,
    FIRST_NAME VARCHAR(8),
    LAST_NAME VARCHAR(8), 
    PHONE VARCHAR(10),
    ADDRESS VARCHAR(22),
    IS_ENABLED CHAR(1),
    CREATE_DT VARCHAR(20),
    MOD_DT VARCHAR(20)
);

INSERT INTO ACCOUNTS VALUES 
(1, 'John', 'Doe', '555-1234', '123 Main St', 'Y', '2023-01-01', '2023-01-01'),
(2, 'Jane', 'Smith', '555-5678', '456 Oak Ave', 'N', '2023-01-02', '2023-01-02'),
(3, 'Bob', 'Johnson', '555-9012', '789 Pine Rd', 'Y', '2023-01-03', '2023-01-03');
```

### Notes on Java Adaptations

- **Terminal Control**: Java console applications have limited terminal control compared to COBOL. Some examples use ANSI escape codes or provide simplified alternatives.
- **Mouse Handling**: The mouse example is simplified since Java console applications don't have built-in mouse support without additional libraries.
- **Screen Positioning**: COBOL's precise screen positioning is approximated using ANSI escape sequences.
- **Data Types**: COBOL's COMP and DISPLAY data types are represented using appropriate Java primitives and formatting.

### Dependencies Used

- **Jackson**: For JSON processing (equivalent to JSON GENERATE)
- **JAXB**: For XML processing (equivalent to XML GENERATE)  
- **PostgreSQL JDBC**: For database connectivity (equivalent to embedded SQL)
- **JLine**: For enhanced console input/output (optional)

Each Java example includes comments explaining the COBOL-to-Java translation and maintains the educational value of the original COBOL programs.
