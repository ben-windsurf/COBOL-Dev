/**
 * Java equivalent of the COBOL trim.cbl program
 * Demonstrates string trimming functionality equivalent to COBOL's TRIM intrinsic function
 * 
 * Author: Devin AI (migrated from COBOL by Erik Eriksen)
 * Date: 2025-08-08
 * Purpose: Demonstrate trim function variations with same output format as COBOL version
 */
public class TrimFunctionTest {
    
    /**
     * Simulates COBOL's fixed-length string behavior with PICTURE x(30)
     * Pads or truncates string to exactly 30 characters
     */
    private static String toFixedLength(String input, int length) {
        if (input == null) {
            input = "";
        }
        if (input.length() >= length) {
            return input.substring(0, length);
        } else {
            return String.format("%-" + length + "s", input);
        }
    }
    
    /**
     * COBOL TRIM function equivalent - trims both leading and trailing spaces
     */
    private static String trim(String input) {
        return input.trim();
    }
    
    /**
     * COBOL TRIM function with LEADING option - trims only leading spaces
     */
    private static String trimLeading(String input) {
        return input.replaceFirst("^\\s+", "");
    }
    
    /**
     * COBOL TRIM function with TRAILING option - trims only trailing spaces
     */
    private static String trimTrailing(String input) {
        return input.replaceFirst("\\s+$", "");
    }
    
    public static void main(String[] args) {
        String wsTestString1 = toFixedLength("    hello world       ", 30);
        
        String wsTestString2;
        
        System.out.println("--" + wsTestString1 + "--");
        System.out.println("--" + trim(wsTestString1) + "--");
        System.out.println("--" + trimLeading(wsTestString1) + "--");
        System.out.println("--" + trimTrailing(wsTestString1) + "--");
        
        wsTestString2 = toFixedLength("******************************", 30);
        System.out.println(wsTestString2);
        wsTestString2 = toFixedLength(wsTestString1, 30);
        System.out.println(wsTestString2);
        
        wsTestString2 = toFixedLength("******************************", 30);
        System.out.println(wsTestString2);
        wsTestString2 = toFixedLength(trim(wsTestString1), 30);
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
        System.out.println("--" + trim("   String literal    ") + "--");
        System.out.println("--" + trimLeading("     String literal   ") + "--");
        System.out.println("--" + trimTrailing("   String literal    ") + "--");
    }
}
