public class TrimDemo {
    public static void main(String[] args) {
        String testString1 = "    hello world       ";
        String testString2;
        
        System.out.println("--" + padTo30(testString1) + "--");
        System.out.println("--" + testString1.trim() + "--");
        System.out.println("--" + testString1.stripTrailing() + "--");
        System.out.println("--" + testString1.stripLeading() + "--");
        
        testString2 = "******************************";
        System.out.println(testString2);
        testString2 = padTo30(testString1);
        System.out.println(testString2);
        
        testString2 = "******************************";
        System.out.println(testString2);
        testString2 = padTo30(testString1.trim());
        System.out.println(testString2);
        
        testString2 = "******************************";
        System.out.println(testString2);
        testString2 = padTo30(testString1.stripLeading());
        System.out.println(testString2);
        
        testString2 = "******************************";
        System.out.println(testString2);
        testString2 = padTo30(testString1.stripTrailing());
        System.out.println(testString2);
        
        System.out.println("--" + "    String literal    " + "--");
        System.out.println("--" + "   String literal    ".trim() + "--");
        System.out.println("--" + "     String literal   ".stripLeading() + "--");
        System.out.println("--" + "   String literal    ".stripTrailing() + "--");
    }
    
    private static String padTo30(String str) {
        return String.format("%-30s", str);
    }
}
