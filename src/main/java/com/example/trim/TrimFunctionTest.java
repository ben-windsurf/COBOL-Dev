package com.example.trim;

public class TrimFunctionTest {
    
    public static void main(String[] args) {
        String testString1 = "    hello world       ";
        String testString2;
        
        System.out.println("--" + testString1 + "--");
        System.out.println("--" + testString1.trim() + "--");
        System.out.println("--" + testString1.replaceAll("^\\s+", "") + "--");
        System.out.println("--" + testString1.replaceAll("\\s+$", "") + "--");
        
        testString2 = "******************************";
        System.out.println(testString2);
        testString2 = testString1;
        System.out.println(testString2);
        
        testString2 = "******************************";
        System.out.println(testString2);
        testString2 = testString1.trim();
        System.out.println(testString2);
        
        testString2 = "******************************";
        System.out.println(testString2);
        testString2 = testString1.replaceAll("^\\s+", "");
        System.out.println(testString2);
        
        testString2 = "******************************";
        System.out.println(testString2);
        testString2 = testString1.replaceAll("\\s+$", "");
        System.out.println(testString2);
        
        System.out.println("--" + "    String literal    " + "--");
        System.out.println("--" + "   String literal    ".trim() + "--");
        System.out.println("--" + "     String literal   ".replaceAll("^\\s+", "") + "--");
        System.out.println("--" + "   String literal    ".replaceAll("\\s+$", "") + "--");
    }
}
