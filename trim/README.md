# Intrinsic TRIM Function Example

The intrinsic ```TRIM``` function trims spaces from the string passed as a parameter. By default, it will trim 
both leading and trailing spaces. You can specify to only trim the leading or trailing spaces by also 
passing ```leading``` or ```trailing``` as a parameter.


Examples:

```function trim(ws-variable)```

```function trim(ws-variable leading)```

```function trim(ws-variable trailing)```

```function trim("  string literal  ")```




If you do not want to have to type ```function``` every time use an intrinsic function, you can add ```function all instrinsic``` 
to your ```repository``` paragraph in the ```configuration section```. 

Example:
```
       environment division. 
       
       configuration section.
       repository.
           function all intrinsic.      
```




```trim.cbl``` demonstrates a couple of examples of using the ```trim``` function.

## Java Migration

```TrimFunctionTest.java``` provides equivalent functionality in Java:

- `trimBoth()` - equivalent to `FUNCTION TRIM(string)`
- `trimLeading()` - equivalent to `FUNCTION TRIM(string LEADING)`  
- `trimTrailing()` - equivalent to `FUNCTION TRIM(string TRAILING)`
- `toFixedLength()` - simulates COBOL `PICTURE X(30)` fixed-length behavior
- Identical output format with "--" delimiters

### Running the Java Program

```bash
javac TrimFunctionTest.java
java TrimFunctionTest
```

**Example of program output:**

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



