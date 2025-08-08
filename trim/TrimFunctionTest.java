/**
 * Java equivalent of the COBOL trim.cbl program
 * Demonstrates string trimming functionality equivalent to COBOL's intrinsic TRIM function
 * Author: Devin AI (migrated from COBOL by Erik Eriksen)
 * Date: 2025-08-08
 */
public class TrimFunctionTest {
    
    public static void main(String[] args) {
        String wsTestString1 = "    hello world       ";
        String wsTestString2;
        
        System.out.println("--" + wsTestString1 + "--");
        System.out.println("--" + trimBoth(wsTestString1) + "--");
        System.out.println("--" + trimLeading(wsTestString1) + "--");
        System.out.println("--" + trimTrailing(wsTestString1) + "--");
        
        wsTestString2 = fixedLengthString("******************************", 30);
        System.out.println(wsTestString2);
        wsTestString2 = fixedLengthString(wsTestString1, 30);
        System.out.println(wsTestString2);
        
        wsTestString2 = fixedLengthString("******************************", 30);
        System.out.println(wsTestString2);
        wsTestString2 = fixedLengthString(trimBoth(wsTestString1), 30);
        System.out.println(wsTestString2);
        
        wsTestString2 = fixedLengthString("******************************", 30);
        System.out.println(wsTestString2);
        wsTestString2 = fixedLengthString(trimLeading(wsTestString1), 30);
        System.out.println(wsTestString2);
        
        wsTestString2 = fixedLengthString("******************************", 30);
        System.out.println(wsTestString2);
        wsTestString2 = fixedLengthString(trimTrailing(wsTestString1), 30);
        System.out.println(wsTestString2);
        
        System.out.println("--" + "    String literal    " + "--");
        System.out.println("--" + trimBoth("   String literal    ") + "--");
        System.out.println("--" + trimLeading("     String literal   ") + "--");
        System.out.println("--" + trimTrailing("   String literal    ") + "--");
    }
    
    /**
     * Equivalent to COBOL's function trim(string) - removes both leading and trailing spaces
     */
    public static String trimBoth(String str) {
        if (str == null) return null;
        return str.trim();
    }
    
    /**
     * Equivalent to COBOL's function trim(string leading) - removes only leading spaces
     */
    public static String trimLeading(String str) {
        if (str == null) return null;
        int start = 0;
        while (start < str.length() && str.charAt(start) == ' ') {
            start++;
        }
        return str.substring(start);
    }
    
    /**
     * Equivalent to COBOL's function trim(string trailing) - removes only trailing spaces
     */
    public static String trimTrailing(String str) {
        if (str == null) return null;
        int end = str.length();
        while (end > 0 && str.charAt(end - 1) == ' ') {
            end--;
        }
        return str.substring(0, end);
    }
    
    /**
     * Simulates COBOL's PICTURE X(30) behavior - creates a fixed-length string
     * If input is longer than length, truncates it
     * If input is shorter than length, pads with trailing spaces
     */
    public static String fixedLengthString(String str, int length) {
        if (str == null) str = "";
        if (str.length() >= length) {
            return str.substring(0, length);
        } else {
            StringBuilder sb = new StringBuilder(str);
            while (sb.length() < length) {
                sb.append(' ');
            }
            return sb.toString();
        }
    }
}
