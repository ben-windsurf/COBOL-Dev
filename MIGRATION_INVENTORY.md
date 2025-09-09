# COBOL to Java Migration Inventory Analysis

## Program Inventory

### 1. PROGRAM-ID Declarations and Program Boundaries

| Program ID | File Path | Purpose | Dependencies |
|------------|-----------|---------|--------------|
| sql-example | sql/sql_example.cbl | PostgreSQL database connectivity with embedded SQL | esqlOC precompiler, unixODBC, PostgreSQL driver |
| main-app | sub_program/main_app.cbl | Main application demonstrating sub-program calls | Calls sub-app |
| sub-app | sub_program/sub.cbl | Sub-program demonstrating parameter passing | Called by main-app |
| redefines-test | redifines/redefines.cbl | Memory overlay patterns with REDEFINES | None |
| search-example | search/search.cbl | Table search operations (SEARCH and SEARCH ALL) | None |
| json-generate-example | json_generate/json_generate.cbl | JSON document generation from COBOL data | libjson-c library |
| xml-generate-example | xml_generate/xml_generate.cbl | XML document generation from COBOL data | libxml2 library |

### 2. CALL/CANCEL Relationships

**Inter-Program Communication:**
- `main-app` → `sub-app` (CALL "sub-app" using parameters)
- `main-app` → `sub-app` (CANCEL "sub-app" to reset working storage)

**Parameter Passing Patterns:**
- BY CONTENT: Variables not modified upon return
- BY REFERENCE (default): Variables can be modified by called program

### 3. Data Structure Catalog

#### WORKING-STORAGE SECTION Variables

**sql-example:**
- `ws-db-connection-string` (PIC X(1024)) - PostgreSQL ODBC connection string
- `ws-sql-account-record` - Database record structure with account fields
- `ws-search-value` - Variable-length string structure for WHERE clauses
- `ws-account-record` (OCCURS 0 to 100) - Dynamic array for query results
- `ws-num-accounts` (PIC 999 COMP) - Record counter

**main-app:**
- `ws-group-1` containing `ws-item-1` and `ws-item-2` (PIC X(10))

**sub-app:**
- `ws-test-item-1`, `ws-test-item-2` (PIC X(10)) - Persistent between calls

**redefines-test:**
- `ws-customer` (OCCURS 0 to 99) - Customer array with REDEFINES patterns
- `ws-diff-data-types` (OCCURS 2) - Data type conversion examples

**search-example:**
- `ws-item-table` (OCCURS 3) - Indexed table with ascending/descending keys
- `ws-no-key-item-table` (OCCURS 3) - Non-indexed table for sequential search

#### LOCAL-STORAGE SECTION Variables

**sub-app:**
- `ls-test-item-1`, `ls-test-item-2` (PIC X(10)) - Auto-reset on each call

#### LINKAGE SECTION Variables

**sub-app:**
- `l-test-item-1`, `l-test-item-2` (PIC X(10)) - Parameter passing interface

### 4. File I/O Patterns and Data Formats

**Database Operations (sql-example):**
- PostgreSQL connectivity via ODBC connection strings
- Cursor-based data retrieval (DECLARE CURSOR, OPEN, FETCH, CLOSE)
- Parameterized queries with variable-length strings

**Data Format Generation:**
- JSON output from COBOL data structures
- XML output with attributes and declarations

### 5. Database Dependencies and esqlOC Usage

**esqlOC Precompiler Dependencies:**
- Input: `sql_example.cbl`
- Output: `generated_sql_ex.cbl`
- Command: `esqlOC -static -o generated_sql_ex.cbl sql_example.cbl`
- Compilation: `cobc -x -static -locsql generated_sql_ex.cbl`

**Embedded SQL Patterns:**
- `EXEC SQL BEGIN DECLARE SECTION END-EXEC`
- `EXEC SQL CONNECT TO :ws-db-connection-string END-EXEC`
- `EXEC SQL DECLARE cursor-name CURSOR FOR SELECT ... END-EXEC`
- `EXEC SQL OPEN cursor-name END-EXEC`
- `EXEC SQL FETCH cursor-name INTO :variables END-EXEC`
- `EXEC SQL CLOSE cursor-name END-EXEC`
- `EXEC SQL CONNECT RESET END-EXEC`

**SQLCA Error Handling:**
- `SQLCODE` checking for success/failure
- `SQLSTATE` for detailed error information
- `SQL-SUCCESS`, `SQL-NODATA` condition names
- `SQLERRML`, `SQLERRMC` for error messages

## Migration Priority Assessment

### High Priority (Phase 1 - Database Integration)
1. **sql-example** - Complex database integration requiring JDBC conversion
2. **esqlOC precompiler removal** - Critical infrastructure change

### Medium Priority (Phase 2 - Program Architecture)
1. **main-app/sub-app** - Inter-program communication patterns
2. **Parameter passing mechanisms** - BY CONTENT vs BY REFERENCE semantics

### Lower Priority (Phase 3 - Data Structure Migration)
1. **redefines-test** - Memory overlay patterns to Java inheritance
2. **search-example** - Table operations to Collections/Stream API
3. **json/xml-generate** - Modern data format generation (may already be handled by Java libraries)

## Technical Complexity Notes

- Variable-length string handling in SQL queries requires careful Java implementation
- REDEFINES memory overlays need polymorphic class design
- OCCURS arrays with dynamic sizing map to Java Collections
- SEARCH operations can leverage Java Stream API
- Working-storage persistence vs local-storage auto-reset semantics need proper Java scoping
