package com.example.isnumeric;

import java.util.Scanner;
import java.util.regex.Pattern;

public class IsNumericTest {
    
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("^\\d+$");
    private Scanner scanner;
    
    public IsNumericTest() {
        scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args) {
        IsNumericTest app = new IsNumericTest();
        app.run();
    }
    
    public void run() {
        processPlain();
        processZeroFill();
        processTrim();
    }
    
    private void processPlain() {
        System.out.print("(plain) Enter a value: ");
        String userInput = scanner.nextLine();
        
        if (isNumeric(userInput)) {
            System.out.println(userInput + " is numeric!");
        } else {
            System.out.println(userInput + " is not numeric.");
        }
    }
    
    private void processZeroFill() {
        System.out.print("(right justify, zero fill) Enter another value: ");
        String userInput = scanner.nextLine();
        
        String rightJustified = String.format("%10s", userInput).replace(' ', '0');
        
        if (isNumeric(rightJustified)) {
            System.out.println(rightJustified + " is numeric!");
        } else {
            System.out.println(rightJustified + " is not numeric.");
        }
    }
    
    private void processTrim() {
        System.out.print("(trim) Enter a third value: ");
        String userInput = scanner.nextLine();
        
        String trimmed = userInput.trim();
        
        if (isNumeric(trimmed)) {
            System.out.println(trimmed + " is numeric!");
        } else {
            System.out.println(trimmed + " is not numeric.");
        }
    }
    
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return NUMERIC_PATTERN.matcher(str).matches();
    }
}
