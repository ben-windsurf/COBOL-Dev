/**
 * Java migration of COBOL trim.cbl program
 * Demonstrates TRIM intrinsic function with various options:
 * - Default trim (both leading and trailing)
 * - Leading-only trim
 * - Trailing-only trim
 * 
 * Maintains same functionality and output format as original COBOL program
 */
public class TrimFunctionTest {
    
    /**
     * Simulates COBOL PICTURE x(30) - fixed-length string of 30 characters
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
        return input == null ? "" : input.trim();
    }
    
    /**
     * COBOL TRIM function with LEADING option - trims only leading spaces
     */
    private static String trimLeading(String input) {
        if (input == null) return "";
        return input.replaceAll("^\\s+", "");
    }
    
    /**
     * COBOL TRIM function with TRAILING option - trims only trailing spaces
     */
    private static String trimTrailing(String input) {
        if (input == null) return "";
        return input.replaceAll("\\s+$", "");
    }
    
    public static void main(String[] args) {
        String wsTestString1 = toFixedLength("    hello world       ", 30);
        
        String wsTestString2 = toFixedLength("", 30);
        
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
