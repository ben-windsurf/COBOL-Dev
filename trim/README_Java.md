# Java Migration of COBOL TRIM Function Example

This directory contains both the original COBOL program (`trim.cbl`) and its Java equivalent (`TrimFunctionTest.java`).

## Java Implementation Details

The Java version replicates the COBOL TRIM function behavior with the following key features:

### Fixed-Length String Handling
- Implements `toFixedLength()` method to replicate COBOL PICTURE x(30) behavior
- Ensures all strings are exactly 30 characters (padded with spaces or truncated)

### TRIM Function Variations
- `trimDefault()` - equivalent to `function trim(string)` - removes both leading and trailing spaces
- `trimLeading()` - equivalent to `function trim(string leading)` - removes only leading spaces  
- `trimTrailing()` - equivalent to `function trim(string trailing)` - removes only trailing spaces

### MOVE Statement Equivalent
- Uses string assignment with `toFixedLength()` to replicate COBOL MOVE behavior
- Maintains the visual demonstration with "******************************" separators

## Running the Programs

### COBOL Version
```bash
cd trim
cobc -x trim.cbl
./trim
```

### Java Version
```bash
cd trim
javac TrimFunctionTest.java
java TrimFunctionTest
```

## Expected Output

Both programs should produce identical output:

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

- Java strings are variable-length by default, so the `toFixedLength()` method ensures consistent 30-character behavior
- Java's `String.trim()` method provides the default trim functionality
- Regular expressions handle leading-only and trailing-only trim operations
- The program structure mirrors the original COBOL procedure division flow
