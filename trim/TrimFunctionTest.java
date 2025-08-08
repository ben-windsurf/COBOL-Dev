/**
 * Java equivalent of COBOL trim.cbl program
 * Demonstrates string trimming functionality equivalent to COBOL TRIM intrinsic function
 * 
 * Original COBOL author: Erik Eriksen
 * Java migration: Devin AI
 * Date: 2025-08-08
 * Purpose: Demonstrate Java string trimming methods equivalent to COBOL TRIM function
 */
public class TrimFunctionTest {
    
    /**
     * Simulates COBOL PIC X(30) - fixed length string with padding
     */
    private static String toFixedLength(String str, int length) {
        if (str == null) str = "";
        if (str.length() >= length) {
            return str.substring(0, length);
        }
        return String.format("%-" + length + "s", str);
    }
    
    /**
     * COBOL TRIM function equivalent - trims both leading and trailing spaces
     */
    private static String trim(String str) {
        return str.trim();
    }
    
    /**
     * COBOL TRIM function with LEADING option - trims only leading spaces
     */
    private static String trimLeading(String str) {
        return str.stripLeading();
    }
    
    /**
     * COBOL TRIM function with TRAILING option - trims only trailing spaces
     */
    private static String trimTrailing(String str) {
        return str.stripTrailing();
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
