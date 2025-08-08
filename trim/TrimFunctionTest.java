/**
 * Java equivalent of the COBOL trim function demonstration program
 * Original COBOL author: Erik Eriksen
 * Java migration: Devin AI
 * Date: 2025-08-08
 * Purpose: Demonstrates string trimming with different options (default, leading-only, trailing-only)
 *          and shows string manipulation with fixed-length string simulation
 */
public class TrimFunctionTest {
    
    /**
     * Simulates COBOL's fixed-length string behavior by padding or truncating to exact length
     */
    private static String toFixedLength(String str, int length) {
        if (str == null) str = "";
        if (str.length() > length) {
            return str.substring(0, length);
        } else if (str.length() < length) {
            return str + " ".repeat(length - str.length());
        }
        return str;
    }
    
    /**
     * Trims leading whitespace only (equivalent to COBOL's TRIM(string LEADING))
     */
    private static String trimLeading(String str) {
        if (str == null) return "";
        int start = 0;
        while (start < str.length() && Character.isWhitespace(str.charAt(start))) {
            start++;
        }
        return str.substring(start);
    }
    
    /**
     * Trims trailing whitespace only (equivalent to COBOL's TRIM(string TRAILING))
     */
    private static String trimTrailing(String str) {
        if (str == null) return "";
        int end = str.length();
        while (end > 0 && Character.isWhitespace(str.charAt(end - 1))) {
            end--;
        }
        return str.substring(0, end);
    }
    
    public static void main(String[] args) {
        String wsTestString1 = toFixedLength("    hello world       ", 30);
        
        String wsTestString2 = toFixedLength("", 30);
        
        System.out.println("--" + wsTestString1 + "--");
        System.out.println("--" + wsTestString1.trim() + "--");
        System.out.println("--" + trimLeading(wsTestString1) + "--");
        System.out.println("--" + trimTrailing(wsTestString1) + "--");
        
        wsTestString2 = toFixedLength("******************************", 30);
        System.out.println(wsTestString2);
        wsTestString2 = toFixedLength(wsTestString1, 30);
        System.out.println(wsTestString2);
        
        wsTestString2 = toFixedLength("******************************", 30);
        System.out.println(wsTestString2);
        wsTestString2 = toFixedLength(wsTestString1.trim(), 30);
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
        System.out.println("--" + "   String literal    ".trim() + "--");
        System.out.println("--" + trimLeading("     String literal   ") + "--");
        System.out.println("--" + trimTrailing("   String literal    ") + "--");
    }
}
