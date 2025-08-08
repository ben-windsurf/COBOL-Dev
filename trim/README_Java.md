# Java Migration of COBOL TRIM Function Example

This Java program (`TrimFunctionTest.java`) is a direct migration of the COBOL program `trim.cbl`, demonstrating equivalent functionality for the TRIM intrinsic function with various options.

## Features

The Java implementation replicates all COBOL functionality:

1. **TRIM function variations:**
   - Default trim (both leading and trailing spaces)
   - Leading-only trim
   - Trailing-only trim

2. **Fixed-length string simulation:**
   - Simulates COBOL `PICTURE x(30)` with 30-character fixed-length strings
   - Proper padding with spaces when strings are shorter than fixed length
   - Truncation when strings exceed fixed length

3. **MOVE operations equivalent:**
   - String assignment with automatic padding/truncation to maintain fixed length
   - Visual demonstration with separator strings

4. **Exact output format:**
   - Same "--" delimiters around trimmed strings
   - Same visual separators with "*" characters
   - Identical console output to original COBOL program

## Usage

### Compile and run:
```bash
javac TrimFunctionTest.java
java TrimFunctionTest
```

### Expected output:
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

## Implementation Details

### Methods

- `toFixedLength(String input, int length)` - Simulates COBOL PICTURE x(30) behavior
- `trim(String input)` - Default trim (both sides), equivalent to `function trim(ws-variable)`
- `trimLeading(String input)` - Leading-only trim, equivalent to `function trim(ws-variable leading)`
- `trimTrailing(String input)` - Trailing-only trim, equivalent to `function trim(ws-variable trailing)`

### COBOL to Java Mapping

| COBOL | Java Equivalent |
|-------|----------------|
| `01 ws-test-string-1 pic x(30) value "    hello world       ".` | `String wsTestString1 = toFixedLength("    hello world       ", 30);` |
| `function trim(ws-test-string-1)` | `trim(wsTestString1)` |
| `function trim(ws-test-string-1 leading)` | `trimLeading(wsTestString1)` |
| `function trim(ws-test-string-1 trailing)` | `trimTrailing(wsTestString1)` |
| `move ws-test-string-1 to ws-test-string-2` | `wsTestString2 = toFixedLength(wsTestString1, 30);` |

## Comparison with Original COBOL

This Java implementation produces identical output to the original COBOL program, maintaining:
- Same string processing logic
- Same visual formatting
- Same demonstration of trim function variations
- Same handling of both variables and string literals
