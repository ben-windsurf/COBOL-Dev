/**
 * Java migration of COBOL trim function demonstration program
 * Original author: Erik Eriksen
 * Migrated to Java: 2025-08-08
 * Purpose: Demonstrates comprehensive string trimming functionality
 * equivalent to COBOL's FUNCTION TRIM() with LEADING/TRAILING options
 */
public class TrimFunctionTest {
    
    /**
     * Simulates COBOL PICTURE X(30) fixed-length string behavior
     * Pads or truncates string to exactly 30 characters
     */
    private static String toFixedLength(String input, int length) {
        if (input == null) {
            input = "";
        }
        if (input.length() > length) {
            return input.substring(0, length);
        } else if (input.length() < length) {
            return String.format("%-" + length + "s", input);
        }
        return input;
    }
    
    /**
     * Trims leading whitespace only (equivalent to TRIM(string LEADING))
     */
    private static String trimLeading(String input) {
        if (input == null) return "";
        return input.replaceFirst("^\\s+", "");
    }
    
    /**
     * Trims trailing whitespace only (equivalent to TRIM(string TRAILING))
     */
    private static String trimTrailing(String input) {
        if (input == null) return "";
        return input.replaceFirst("\\s+$", "");
    }
    
    /**
     * Trims both leading and trailing whitespace (equivalent to TRIM(string))
     */
    private static String trimBoth(String input) {
        if (input == null) return "";
        return input.trim();
    }
    
    public static void main(String[] args) {
        String wsTestString1 = toFixedLength("    hello world       ", 30);
        
        String wsTestString2 = toFixedLength("", 30);
        
        System.out.println("--" + wsTestString1 + "--");
        System.out.println("--" + trimBoth(wsTestString1) + "--");
        System.out.println("--" + trimLeading(wsTestString1) + "--");
        System.out.println("--" + trimTrailing(wsTestString1) + "--");
        
        wsTestString2 = toFixedLength("******************************", 30);
        System.out.println(wsTestString2);
        
        wsTestString2 = wsTestString1;
        System.out.println(wsTestString2);
        
        wsTestString2 = toFixedLength("******************************", 30);
        System.out.println(wsTestString2);
        
        wsTestString2 = toFixedLength(trimBoth(wsTestString1), 30);
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
        System.out.println("--" + trimBoth("   String literal    ") + "--");
        System.out.println("--" + trimLeading("     String literal   ") + "--");
        System.out.println("--" + trimTrailing("   String literal    ") + "--");
    }
}
