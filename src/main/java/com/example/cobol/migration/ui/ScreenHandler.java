package com.example.cobol.migration.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ScreenHandler {
    private Scanner scanner;
    private BufferedReader reader;
    
    public ScreenHandler() {
        this.scanner = new Scanner(System.in);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public void display(String message) {
        System.out.print(message);
    }
    
    public void displayLine(String message) {
        System.out.println(message);
    }
    
    public void displayWithNoAdvancing(String message) {
        System.out.print(message);
        System.out.flush();
    }
    
    public String acceptInput() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
            return "";
        }
    }
    
    public String acceptInput(String prompt) {
        displayWithNoAdvancing(prompt);
        return acceptInput();
    }
    
    public int acceptNumericInput(String prompt) {
        while (true) {
            String input = acceptInput(prompt);
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                displayLine("Invalid numeric input. Please try again.");
            }
        }
    }
    
    public double acceptDecimalInput(String prompt) {
        while (true) {
            String input = acceptInput(prompt);
            try {
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException e) {
                displayLine("Invalid decimal input. Please try again.");
            }
        }
    }
    
    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[2J\033[H");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
    
    public void displaySpace() {
        System.out.println();
    }
    
    public void displaySeparator(String character, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(character);
        }
        System.out.println();
    }
    
    public void displayHeader(String title) {
        displaySeparator("=", 50);
        displayLine(title);
        displaySeparator("=", 50);
    }
    
    public void displaySubHeader(String title) {
        displaySeparator("-", 30);
        displayLine(title);
        displaySeparator("-", 30);
    }
    
    public ScreenSize getScreenSize() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;
            
            if (os.contains("windows")) {
                pb = new ProcessBuilder("cmd", "/c", "mode con");
            } else {
                pb = new ProcessBuilder("stty", "size");
            }
            
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            
            if (line != null && !line.trim().isEmpty()) {
                if (os.contains("windows")) {
                    String[] parts = line.split("\\s+");
                    for (String part : parts) {
                        if (part.contains("Columns:")) {
                            int cols = Integer.parseInt(part.split(":")[1].trim());
                            return new ScreenSize(24, cols);
                        }
                    }
                } else {
                    String[] parts = line.trim().split("\\s+");
                    if (parts.length >= 2) {
                        int rows = Integer.parseInt(parts[0]);
                        int cols = Integer.parseInt(parts[1]);
                        return new ScreenSize(rows, cols);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Could not determine screen size: " + e.getMessage());
        }
        
        return new ScreenSize(24, 80);
    }
    
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing reader: " + e.getMessage());
        }
    }
    
    public static class ScreenSize {
        private final int rows;
        private final int columns;
        
        public ScreenSize(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
        }
        
        public int getRows() {
            return rows;
        }
        
        public int getColumns() {
            return columns;
        }
        
        @Override
        public String toString() {
            return "ScreenSize{rows=" + rows + ", columns=" + columns + "}";
        }
    }
}
