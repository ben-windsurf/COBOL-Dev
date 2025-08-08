# Java Migration of COBOL TRIM Function

This directory contains both the original COBOL program (`trim.cbl`) and its Java equivalent (`TrimFunctionTest.java`).

## Java Implementation

The Java program `TrimFunctionTest.java` replicates the exact functionality of the COBOL `trim.cbl` program, including:

- **TRIM function variations**: Default trim (both leading/trailing), leading-only trim, trailing-only trim
- **Fixed-length string behavior**: Simulates COBOL's `PICTURE x(30)` with padding/truncation to 30 characters
- **String assignment operations**: Equivalent to COBOL's MOVE statements
- **Visual output formatting**: Maintains the same "--" delimiter format for easy comparison

## Running the Programs

### COBOL Version
```bash
cobc -x trim.cbl
./trim
```

### Java Version
```bash
javac TrimFunctionTest.java
java TrimFunctionTest
```

Both programs produce identical output, demonstrating the successful migration of COBOL string manipulation functionality to Java.

## Key Mappings

| COBOL Feature | Java Equivalent |
|---------------|-----------------|
| `PICTURE x(30)` | `toFixedLength(string, 30)` method |
| `FUNCTION TRIM(string)` | `string.trim()` |
| `FUNCTION TRIM(string LEADING)` | `string.replaceFirst("^\\s+", "")` |
| `FUNCTION TRIM(string TRAILING)` | `string.replaceFirst("\\s+$", "")` |
| `MOVE source TO target` | `target = toFixedLength(source, 30)` |
| `DISPLAY` statements | `System.out.println()` |

## Expected Output

Both programs produce the following output:
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
