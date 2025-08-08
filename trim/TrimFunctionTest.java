/**
 * Java equivalent of the COBOL trim.cbl program
 * Demonstrates string trimming functionality with different options
 * Author: Devin AI (migrated from COBOL by Erik Eriksen)
 * Date: 2025-08-08
 */
public class TrimFunctionTest {
    
    public static void main(String[] args) {
        String wsTestString1 = "    hello world       ";
        String wsTestString2;
        
        System.out.println("--" + wsTestString1 + "--");
        System.out.println("--" + wsTestString1.trim() + "--");
        System.out.println("--" + wsTestString1.stripLeading() + "--");
        System.out.println("--" + wsTestString1.stripTrailing() + "--");
        
        wsTestString2 = "******************************";
        System.out.println(wsTestString2);
        wsTestString2 = wsTestString1;
        System.out.println(wsTestString2);
        
        wsTestString2 = "******************************";
        System.out.println(wsTestString2);
        wsTestString2 = wsTestString1.trim();
        System.out.println(padToLength(wsTestString2, 30));
        
        wsTestString2 = "******************************";
        System.out.println(wsTestString2);
        wsTestString2 = wsTestString1.stripLeading();
        System.out.println(padToLength(wsTestString2, 30));
        
        wsTestString2 = "******************************";
        System.out.println(wsTestString2);
        wsTestString2 = wsTestString1.stripTrailing();
        System.out.println(padToLength(wsTestString2, 30));
        
        System.out.println("--" + "    String literal    " + "--");
        System.out.println("--" + "   String literal    ".trim() + "--");
        System.out.println("--" + "     String literal   ".stripLeading() + "--");
        System.out.println("--" + "   String literal    ".stripTrailing() + "--");
    }
    
    /**
     * Helper method to pad string to specified length with spaces
     * This mimics COBOL's fixed-length string behavior
     */
    private static String padToLength(String str, int length) {
        if (str.length() >= length) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.append(' ');
        }
        return sb.toString();
    }
}
