package com.example.unstring;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class UnstringExample {
    
    public static class UnstringResult {
        private List<String> parts;
        private List<String> delimiters;
        private List<Integer> counts;
        private int fieldsUsed;
        
        public UnstringResult() {
            parts = new ArrayList<>();
            delimiters = new ArrayList<>();
            counts = new ArrayList<>();
            fieldsUsed = 0;
        }
        
        public void addPart(String part, String delimiter, int count) {
            parts.add(part);
            delimiters.add(delimiter);
            counts.add(count);
            fieldsUsed++;
        }
        
        public List<String> getParts() { return parts; }
        public List<String> getDelimiters() { return delimiters; }
        public List<Integer> getCounts() { return counts; }
        public int getFieldsUsed() { return fieldsUsed; }
    }
    
    public static void main(String[] args) {
        UnstringExample app = new UnstringExample();
        app.run();
    }
    
    public void run() {
        example1();
        example2();
        example3();
        example4();
        example5();
        example6();
    }
    
    private void example1() {
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 1 : SIMPLE UNSTRING");
        System.out.println();
        
        String sourceStr = "Hello World";
        System.out.println("SOURCE STRING: " + sourceStr);
        
        String[] parts = sourceStr.split(" ", 2);
        String part1 = parts.length > 0 ? parts[0] : "";
        String part2 = parts.length > 1 ? parts[1] : "";
        
        System.out.println("PART1: " + part1);
        System.out.println("PART2: " + part2);
    }
    
    private void example2() {
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 2 : UNSTRING MULTIPLE TIMES INTO SAME DEST.");
        System.out.println();
        
        String sourceStr = "Hello World";
        System.out.println("SOURCE STRING: " + sourceStr);
        
        int pointer = 0;
        for (int i = 0; i < 2; i++) {
            if (pointer < sourceStr.length()) {
                String remaining = sourceStr.substring(pointer).trim();
                String[] parts = remaining.split("\\s+", 2);
                String part = parts.length > 0 ? parts[0] : "";
                
                if (!part.isEmpty()) {
                    pointer = sourceStr.indexOf(part, pointer) + part.length();
                    while (pointer < sourceStr.length() && Character.isWhitespace(sourceStr.charAt(pointer))) {
                        pointer++;
                    }
                    System.out.println("Successfully unstrung.");
                } else {
                    System.out.println("ERROR: OVERFLOW");
                }
                
                System.out.println("PART VALUE: " + part);
                System.out.println("POINTER: " + (pointer + 1));
            }
        }
    }
    
    private void example3() {
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 3 : UNSTRING INTO EXPLICIT FIELDS");
        System.out.println();
        
        String sourceStr = "Hello World";
        System.out.println("SOURCE STRING: " + sourceStr);
        
        String[] parts = sourceStr.split("\\s+");
        String part1 = parts.length > 0 ? parts[0] : "";
        String part2 = parts.length > 1 ? parts[1] : "";
        int pointer = sourceStr.length() + 1;
        
        System.out.println("Successfully unstrung.");
        System.out.println("PART1: " + part1);
        System.out.println("PART2: " + part2);
        System.out.println("POINTER: " + pointer);
    }
    
    private void example4() {
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 4 : UNSTRING WITH MULTIPLE DELIMITERS ");
        System.out.println();
        
        String sourceStr = "A<B<CD>E%FG!HIJ|KL!MN>OP#QR!ST";
        System.out.println("SOURCE STRING: " + sourceStr);
        
        String[] delimiters = {"<", ">", "!", "|"};
        Pattern pattern = Pattern.compile("[<>!|]");
        
        int pointer = 0;
        int fieldsUsed = 0;
        
        while (pointer < sourceStr.length()) {
            String remaining = sourceStr.substring(pointer);
            String[] parts = pattern.split(remaining, 2);
            
            if (parts.length > 0 && !parts[0].isEmpty()) {
                String part = parts[0];
                String delimiter = "";
                
                int nextDelimPos = pointer + part.length();
                if (nextDelimPos < sourceStr.length()) {
                    delimiter = String.valueOf(sourceStr.charAt(nextDelimPos));
                    pointer = nextDelimPos + 1;
                } else {
                    pointer = sourceStr.length();
                }
                
                fieldsUsed++;
                
                System.out.println();
                System.out.println("VALUE: " + part);
                System.out.println("DELIMITER: " + delimiter);
                System.out.println("CHAR COUNT:" + part.length());
                System.out.println("CURRENT POINTER: " + (pointer + 1));
                System.out.println("TOTAL FIELDS FILLED: " + fieldsUsed);
                System.out.println("-------------------------------------------");
            } else {
                break;
            }
        }
    }
    
    private void example5() {
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 5 : UNSTRING WITH MULTIPLE DELIMITERS INTO MULTIPLE DESTINATIONS");
        System.out.println();
        
        String sourceStr = "A<B<CD>EFG!HIJ|KLMN>O";
        System.out.println("SOURCE STRING: " + sourceStr);
        
        UnstringResult result = unstringMultiple(sourceStr, new String[]{"<", ">", "!", "|"}, 6);
        
        for (int i = 0; i < result.getParts().size(); i++) {
            System.out.println();
            System.out.println("STRING NUMBER: " + (i + 1));
            System.out.println("VALUE: " + result.getParts().get(i));
            System.out.println("DELIMITER: " + result.getDelimiters().get(i));
            System.out.println("CHAR COUNT:" + result.getCounts().get(i));
            System.out.println("-------------------------------------------");
        }
        
        System.out.println("TOTALS: ");
        System.out.println("FIELDS FILLED: " + result.getFieldsUsed());
    }
    
    private void example6() {
        System.out.println();
        System.out.println("=================================================");
        System.out.println("EX 6 : UNSTRING FORMATTED NUMBER");
        System.out.println();
        
        String sourceNum = "$123,456.12";
        System.out.println("SOURCE VALUE: " + sourceNum);
        
        String numPart = sourceNum.substring(1);
        String[] parts = numPart.split("[,.]");
        
        System.out.println("PART 1: " + (parts.length > 0 ? parts[0] : ""));
        System.out.println("PART 2: " + (parts.length > 1 ? parts[1] : ""));
        System.out.println("PART 3: " + (parts.length > 2 ? parts[2] : ""));
        System.out.println();
    }
    
    private UnstringResult unstringMultiple(String source, String[] delimiters, int maxFields) {
        UnstringResult result = new UnstringResult();
        Pattern pattern = Pattern.compile("[" + Pattern.quote(String.join("", delimiters)) + "]");
        
        int pointer = 0;
        int fieldsUsed = 0;
        
        while (pointer < source.length() && fieldsUsed < maxFields) {
            String remaining = source.substring(pointer);
            String[] parts = pattern.split(remaining, 2);
            
            if (parts.length > 0) {
                String part = parts[0];
                String delimiter = "";
                
                int nextDelimPos = pointer + part.length();
                if (nextDelimPos < source.length()) {
                    delimiter = String.valueOf(source.charAt(nextDelimPos));
                    pointer = nextDelimPos + 1;
                } else {
                    pointer = source.length();
                }
                
                result.addPart(part, delimiter, part.length());
                fieldsUsed++;
            } else {
                break;
            }
        }
        
        return result;
    }
}
