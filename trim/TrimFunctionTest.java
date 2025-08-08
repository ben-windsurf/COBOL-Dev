/**
 * Java equivalent of the COBOL trim function demonstration program.
 * Migrated from trim.cbl - demonstrates TRIM function with various options.
 * 
 * Author: Devin AI (migrated from Erik Eriksen's COBOL version)
 * Date: 2025-08-08
 * Purpose: Demonstrate trim functionality equivalent to COBOL intrinsic TRIM function
 */
public class TrimFunctionTest {
    
    /**
     * Creates a fixed-length string equivalent to COBOL PICTURE x(30)
     * Pads with spaces to reach exactly 30 characters or truncates if longer
     */
    private static String toFixedLength(String input, int length) {
        if (input == null) {
            input = "";
        }
        if (input.length() >= length) {
            return input.substring(0, length);
        }
        return input + " ".repeat(length - input.length());
    }
    
    /**
     * Default trim - removes both leading and trailing spaces
     * Equivalent to COBOL: function trim(string)
     */
    private static String trimDefault(String input) {
        return input.trim();
    }
    
    /**
     * Leading trim - removes only leading spaces
     * Equivalent to COBOL: function trim(string leading)
     */
    private static String trimLeading(String input) {
        return input.replaceAll("^\\s+", "");
    }
    
    /**
     * Trailing trim - removes only trailing spaces
     * Equivalent to COBOL: function trim(string trailing)
     */
    private static String trimTrailing(String input) {
        return input.replaceAll("\\s+$", "");
    }
    
    public static void main(String[] args) {
        String wsTestString1 = "    hello world       ";
        wsTestString1 = toFixedLength(wsTestString1, 30);
        
        String wsTestString2 = toFixedLength("", 30);
        
        System.out.println("--" + wsTestString1 + "--");
        System.out.println("--" + trimDefault(wsTestString1) + "--");
        System.out.println("--" + trimLeading(wsTestString1) + "--");
        System.out.println("--" + trimTrailing(wsTestString1) + "--");
        
        wsTestString2 = toFixedLength("******************************", 30);
        System.out.println(wsTestString2);
        wsTestString2 = toFixedLength(wsTestString1, 30);
        System.out.println(wsTestString2);
        
        wsTestString2 = toFixedLength("******************************", 30);
        System.out.println(wsTestString2);
        wsTestString2 = toFixedLength(trimDefault(wsTestString1), 30);
        System.out.println(wsTestString2);
        
        wsTestString2 = toFixedLength("******************************", 30);
        System.out.println(wsTestString2);
        wsTestString2 = toFixedLength(trimLeading(wsTestString1), 30);
        System.out.println(wsTestString2);
        
        wsTestString2 = toFixedLength("******************************", 30);
        System.out.println(wsTestString2);
        wsTestString2 = toFixedLength(trimTrailing(wsTestString1), 30);
        System.out.println(wsTestString2);
        
        System.out.println("--" + "    String literal    " + "--");
        System.out.println("--" + trimDefault("   String literal    ") + "--");
        System.out.println("--" + trimLeading("     String literal   ") + "--");
        System.out.println("--" + trimTrailing("   String literal    ") + "--");
    }
}
