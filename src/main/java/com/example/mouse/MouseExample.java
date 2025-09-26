package com.example.mouse;

import java.util.Scanner;

public class MouseExample {
    
    private Scanner scanner;
    private char[][] screen;
    private int screenHeight = 20;
    private int screenWidth = 80;
    private int drawColor = 1;
    private boolean exit = false;
    
    public MouseExample() {
        scanner = new Scanner(System.in);
        screen = new char[screenHeight][screenWidth];
        initializeScreen();
    }
    
    public static void main(String[] args) {
        MouseExample app = new MouseExample();
        app.run();
    }
    
    public void run() {
        System.out.println("Note: This is a simplified console version of the mouse example.");
        System.out.println("Original COBOL version used terminal mouse handling which requires");
        System.out.println("platform-specific libraries in Java.");
        System.out.println();
        System.out.println("Commands:");
        System.out.println("  d <row> <col> - Draw at position");
        System.out.println("  c <color>     - Change color (0-7)");
        System.out.println("  s             - Show screen");
        System.out.println("  q             - Quit");
        System.out.println();
        
        displayStatus();
        
        while (!exit) {
            System.out.print("Command: ");
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.isEmpty()) {
                continue;
            }
            
            String[] parts = input.split("\\s+");
            String command = parts[0];
            
            switch (command) {
                case "d":
                    if (parts.length >= 3) {
                        try {
                            int row = Integer.parseInt(parts[1]);
                            int col = Integer.parseInt(parts[2]);
                            drawAt(row, col);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid coordinates");
                        }
                    } else {
                        System.out.println("Usage: d <row> <col>");
                    }
                    break;
                    
                case "c":
                    if (parts.length >= 2) {
                        try {
                            int color = Integer.parseInt(parts[1]);
                            if (color >= 0 && color <= 7) {
                                drawColor = color;
                                displayStatus();
                            } else {
                                System.out.println("Color must be 0-7");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid color");
                        }
                    } else {
                        System.out.println("Usage: c <color>");
                    }
                    break;
                    
                case "s":
                    showScreen();
                    break;
                    
                case "q":
                    exit = true;
                    break;
                    
                default:
                    System.out.println("Unknown command: " + command);
            }
        }
        
        System.out.println("Goodbye!");
    }
    
    private void initializeScreen() {
        for (int i = 0; i < screenHeight; i++) {
            for (int j = 0; j < screenWidth; j++) {
                screen[i][j] = ' ';
            }
        }
    }
    
    private void displayStatus() {
        System.out.println("Current Draw color: " + drawColor);
    }
    
    private void drawAt(int row, int col) {
        if (row >= 0 && row < screenHeight && col >= 0 && col < screenWidth) {
            screen[row][col] = (char)('0' + drawColor);
            System.out.println("Drew color " + drawColor + " at (" + row + ", " + col + ")");
        } else {
            System.out.println("Position out of bounds");
        }
    }
    
    private void showScreen() {
        System.out.println("Screen contents:");
        System.out.print("+");
        for (int i = 0; i < screenWidth; i++) System.out.print("-");
        System.out.println("+");
        for (int i = 0; i < screenHeight; i++) {
            System.out.print("|");
            for (int j = 0; j < screenWidth; j++) {
                System.out.print(screen[i][j]);
            }
            System.out.println("|");
        }
        System.out.print("+");
        for (int i = 0; i < screenWidth; i++) System.out.print("-");
        System.out.println("+");
    }
}
