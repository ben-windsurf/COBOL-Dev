# COBOL to Java Migration: trim.cbl

## Overview

This directory contains the Java equivalent of the COBOL `trim.cbl` program, which demonstrates string trimming functionality with three different modes.

## Migration Approach

### COBOL to Java Mapping

| COBOL Construct | Java Equivalent | Notes |
|-----------------|-----------------|-------|
| `working-storage section` | Instance variables in TrimFunctionTest class | Converted fixed-length COBOL strings to Java String objects |
| `01 ws-test-string-1 pic x(30)` | `String testString1` | COBOL's fixed 30-character field becomes dynamic Java String |
| `function trim(string)` | `TrimUtil.trimBoth(string)` | Uses Java's `String.trim()` method |
| `function trim(string leading)` | `TrimUtil.trimLeading(string)` | Uses Java's `String.stripLeading()` method |
| `function trim(string trailing)` | `TrimUtil.trimTrailing(string)` | Uses Java's `String.stripTrailing()` method |
| `display` statements | `System.out.println()` | Direct mapping for console output |
| `move` statements | Variable assignment | Java assignment replaces COBOL MOVE |

### Key Differences

1. **String Length Handling**
   - COBOL: Fixed-length strings with `PIC X(30)` - strings are always 30 characters, padded with spaces
   - Java: Dynamic-length strings - strings can be any length
   - Solution: Added `padToLength()` method to simulate COBOL's fixed-length behavior when needed

2. **Memory Management**
   - COBOL: Explicit memory layout with working-storage section
   - Java: Automatic memory management with object-oriented design
   - Solution: Used instance variables to represent working-storage fields

3. **String Operations**
   - COBOL: Built-in `function trim()` with optional `leading`/`trailing` parameters
   - Java: Separate methods `trim()`, `stripLeading()`, `stripTrailing()`
   - Solution: Created utility class `TrimUtil` with static methods for each trim operation

## Class Structure

### TrimUtil.java
Utility class containing static methods for string trimming operations:
- `trimBoth(String)` - Removes leading and trailing whitespace
- `trimLeading(String)` - Removes only leading whitespace
- `trimTrailing(String)` - Removes only trailing whitespace

### TrimFunctionTest.java
Main demonstration class that replicates the behavior of the original COBOL program:
- Contains instance variables equivalent to COBOL working-storage
- Implements the same test sequence as the original program
- Produces identical output to the COBOL version

## Usage

### Compilation
```bash
javac src/main/java/com/cobol/migration/trim/*.java
```

### Execution
```bash
java -cp src/main/java com.cobol.migration.trim.TrimFunctionTest
```

### Expected Output
The Java program produces the same output as the original COBOL program:
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

## Migration Notes

1. **Preserved Functionality**: All original COBOL trim operations are maintained
2. **Object-Oriented Design**: Converted procedural COBOL to object-oriented Java while preserving behavior
3. **Error Handling**: Added null checks in utility methods for robustness
4. **Code Reusability**: Created separate utility class for potential reuse in other migrations
5. **Documentation**: Comprehensive documentation for future maintenance and understanding

## Future Enhancements

- Could be extended to handle other COBOL string functions
- Utility methods could be enhanced with additional validation
- Could be integrated into a larger COBOL-to-Java migration framework
