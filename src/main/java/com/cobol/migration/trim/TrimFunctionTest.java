package com.cobol.migration.trim;

public class TrimFunctionTest {
    
    private String testString1;
    private String testString2;
    
    public TrimFunctionTest() {
        this.testString1 = "    hello world       ";
        this.testString2 = "";
    }
    
    public void runTest() {
        System.out.println("--" + testString1 + "--");
        System.out.println("--" + TrimUtil.trimBoth(testString1) + "--");
        System.out.println("--" + TrimUtil.trimLeading(testString1) + "--");
        System.out.println("--" + TrimUtil.trimTrailing(testString1) + "--");
        
        testString2 = "******************************";
        System.out.println(testString2);
        testString2 = testString1;
        System.out.println(testString2);
        
        testString2 = "******************************";
        System.out.println(testString2);
        testString2 = TrimUtil.trimBoth(testString1);
        System.out.println(padToLength(testString2, 30));
        
        testString2 = "******************************";
        System.out.println(testString2);
        testString2 = TrimUtil.trimLeading(testString1);
        System.out.println(padToLength(testString2, 30));
        
        testString2 = "******************************";
        System.out.println(testString2);
        testString2 = TrimUtil.trimTrailing(testString1);
        System.out.println(padToLength(testString2, 30));
        
        System.out.println("--" + "    String literal    " + "--");
        System.out.println("--" + TrimUtil.trimBoth("   String literal    ") + "--");
        System.out.println("--" + TrimUtil.trimLeading("     String literal   ") + "--");
        System.out.println("--" + TrimUtil.trimTrailing("   String literal    ") + "--");
    }
    
    private String padToLength(String input, int length) {
        if (input.length() >= length) {
            return input;
        }
        StringBuilder sb = new StringBuilder(input);
        while (sb.length() < length) {
            sb.append(" ");
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        TrimFunctionTest test = new TrimFunctionTest();
        test.runTest();
    }
}
