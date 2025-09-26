package com.example.screensize;

public class GetScreenSize {
    
    public static void main(String[] args) {
        System.out.println("Note: Java console applications cannot directly detect terminal size");
        System.out.println("without additional libraries or system calls.");
        System.out.println();
        
        String columns = System.getenv("COLUMNS");
        String lines = System.getenv("LINES");
        
        if (columns != null && lines != null) {
            System.out.println("Terminal size from environment:");
            System.out.println("Columns: " + columns);
            System.out.println("Lines: " + lines);
        } else {
            System.out.println("Default assumed terminal size:");
            System.out.println("Columns: 80");
            System.out.println("Lines: 24");
        }
        
        System.out.println();
        System.out.println("For full terminal control in Java, consider using:");
        System.out.println("- JLine library");
        System.out.println("- Lanterna library");
        System.out.println("- System.getProperty() calls");
    }
}
