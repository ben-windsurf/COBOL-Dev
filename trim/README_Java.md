# Java Migration of COBOL TRIM Function Example

This Java program (`TrimFunctionTest.java`) is a direct migration of the COBOL `trim.cbl` program, demonstrating equivalent string trimming functionality.

## Key Features Migrated

### 1. Fixed-Length String Handling
- **COBOL**: `PIC X(30)` clauses create 30-character fixed-length strings
- **Java**: `toFixedLength()` method simulates this behavior with padding

### 2. TRIM Function Variations
- **COBOL**: `FUNCTION TRIM(string)` - trims both leading and trailing spaces
- **Java**: `trim()` method - equivalent functionality

- **COBOL**: `FUNCTION TRIM(string LEADING)` - trims only leading spaces  
- **Java**: `trimLeading()` method using `stripLeading()`

- **COBOL**: `FUNCTION TRIM(string TRAILING)` - trims only trailing spaces
- **Java**: `trimTrailing()` method using `stripTrailing()`

### 3. String Operations
- **COBOL**: `MOVE` statements for string assignment
- **Java**: Direct string assignment with fixed-length conversion

### 4. Visual Output Formatting
- Delimiter markers (`--`) around strings to show trimming effects
- Asterisk separators (`******************************`) between sections
- Identical output format for comparison

## Compilation and Execution

```bash
# Compile the Java program
javac TrimFunctionTest.java

# Run the program
java TrimFunctionTest
```

## Expected Output

The Java program produces identical output to the COBOL version:

```
--    hello world               --
--hello world--
--hello world               --
--    hello world--
******************************
    hello world               
******************************
hello world                   
******************************
hello world                   
******************************
    hello world               
--    String literal    --
--String literal--
--String literal   --
--   String literal--
```

## Technical Notes

### String Method Equivalents
- `String.trim()` - removes leading and trailing whitespace (equivalent to COBOL default TRIM)
- `String.stripLeading()` - removes only leading whitespace (Java 11+, equivalent to COBOL TRIM LEADING)
- `String.stripTrailing()` - removes only trailing whitespace (Java 11+, equivalent to COBOL TRIM TRAILING)

### Fixed-Length String Simulation
The `toFixedLength()` method replicates COBOL's fixed-length string behavior:
- Truncates strings longer than the specified length
- Pads shorter strings with trailing spaces
- Maintains consistent 30-character length like COBOL `PIC X(30)`

### Differences from COBOL
- Java strings are dynamic by default; fixed-length behavior is simulated
- Java uses 0-based indexing vs COBOL's 1-based indexing (not relevant for this example)
- Java requires explicit method calls vs COBOL's intrinsic function syntax

## Comparison with Original COBOL

| Feature | COBOL | Java |
|---------|-------|------|
| Fixed-length strings | `PIC X(30)` | `toFixedLength(str, 30)` |
| Default trim | `FUNCTION TRIM(str)` | `str.trim()` |
| Leading trim | `FUNCTION TRIM(str LEADING)` | `str.stripLeading()` |
| Trailing trim | `FUNCTION TRIM(str TRAILING)` | `str.stripTrailing()` |
| String assignment | `MOVE str1 TO str2` | `str2 = toFixedLength(str1, 30)` |
| Output | `DISPLAY` | `System.out.println()` |
