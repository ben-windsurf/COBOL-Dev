## SQL Example Program

This example program demonstrates database connectivity using both the original COBOL esqlOC precompiler approach and the new Java JDBC replacement that can connect and query a PostgreSQL database.



**Files included:**
* ```create_test_db.sql``` - SQL script to create the database and test data.
* ```generated_sql-ex.cbl``` - Example of what the ```sql_example.cbl``` file looks like after it goes through the esqlOC precompiler. This is the file that gets compiled by ```cobc```.
* ```sql_example.cbl``` - The example program that gets sent to the precompiler.




**Prerequisites:**
* PostgreSQL database instance
* Java 11 or higher
* Apache Maven 3.6 or higher
* PostgreSQL Database - https://www.postgresql.org/





**How to build**
* Run the ```create_test_db.sql``` on your testing PostgreSQL instance.
* Navigate to the java directory: ```cd ../java```
* Compile the Java JDBC replacement: ```mvn compile```
* Run tests: ```mvn test```
* Run the example program: ```mvn exec:java -Dexec.mainClass="com.cobol.migration.example.DatabaseExample"```

**Java JDBC Implementation**
The esqlOC precompiler has been replaced with a Java JDBC implementation that provides the same database functionality:
* ```DatabaseConfig.java``` - Database connection configuration
* ```DatabaseConnectionManager.java``` - Connection management with proper resource cleanup
* ```AccountDAO.java``` - Data access operations equivalent to the original COBOL cursors
* ```DatabaseExample.java``` - Example program demonstrating the three main database operations



**Notes regarding querying with variable-length variables:**

I haven't seen this mentioned in other documents, so I figure it might be helpful to put this information here regarding 
the use of variable length variables in a WHERE clause either using the equals sign or the LIKE operator. 


Variables in the WHERE clause require that the string length 
is supplied otherwise with a regular 'PIC X(n)' it will 
include the blank space in any '=' or 'LIKE' operation and 
most likely not match any records. Using the below variable
declaration ensures that the correct length is passed for the
text supplied. 

```
       01  ws-search-value.
           05  ws-search-value-len              pic S9(4) comp-5.
           05  ws-search-value-text             pic x(50).
```

More info can be found at this link under the 'Variable-length
Character Strings' section. Note: level 49 variables are not
supported so a regular '05' seems to work instead.
https://www.microfocus.com/documentation/net-express/nx30books/dbdtyp.htm

Please also see the comments and code in the ```sql_example.cbl``` source file as this is demonstrated in the 
account querying functionality of the test program.

