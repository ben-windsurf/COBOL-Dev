package com.example.numval;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumvalTest {
    
    public static void main(String[] args) {
        System.out.println("COBOL NUMVAL function equivalent in Java");
        System.out.println("Converting various string formats to numeric values");
        System.out.println();
        
        String[] testStrings = {
            "123",
            "123.45",
            "$123.45",
            "123,456.78",
            "$1,234.56",
            "  123.45  ",
            "abc123",
            "123abc",
            ""
        };
        
        for (String testString : testStrings) {
            System.out.println("Input: '" + testString + "'");
            
            double numericValue = numval(testString);
            if (!Double.isNaN(numericValue)) {
                System.out.println("Numeric value: " + numericValue);
            } else {
                System.out.println("Not a valid numeric string");
            }
            System.out.println();
        }
    }
    
    private static double numval(String input) {
        if (input == null || input.trim().isEmpty()) {
            return Double.NaN;
        }
        
        String cleaned = input.trim();
        
        cleaned = cleaned.replaceAll("[$,]", "");
        
        try {
            return Double.parseDouble(cleaned);
        } catch (NumberFormatException e) {
            try {
                NumberFormat format = NumberFormat.getInstance(Locale.US);
                Number number = format.parse(cleaned);
                return number.doubleValue();
            } catch (ParseException pe) {
                return Double.NaN;
            }
        }
    }
}
