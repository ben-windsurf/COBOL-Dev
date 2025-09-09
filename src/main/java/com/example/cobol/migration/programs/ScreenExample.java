package com.example.cobol.migration.programs;

import com.example.cobol.migration.ui.ScreenHandler;

public class ScreenExample {
    private ScreenHandler screenHandler;
    
    public ScreenExample() {
        this.screenHandler = new ScreenHandler();
    }
    
    public static void main(String[] args) {
        ScreenExample example = new ScreenExample();
        try {
            example.run();
        } finally {
            example.cleanup();
        }
    }
    
    public void run() {
        screenHandler.clearScreen();
        screenHandler.displayHeader("COBOL Screen Handling Example");
        
        demonstrateScreenSize();
        demonstrateUserInput();
        demonstrateFormattedOutput();
        
        screenHandler.displayLine("Program completed successfully.");
    }
    
    private void demonstrateScreenSize() {
        screenHandler.displaySubHeader("Screen Size Detection");
        
        ScreenHandler.ScreenSize screenSize = screenHandler.getScreenSize();
        screenHandler.displayLine("Detected screen size:");
        screenHandler.displayLine("Rows: " + screenSize.getRows());
        screenHandler.displayLine("Columns: " + screenSize.getColumns());
        screenHandler.displaySpace();
    }
    
    private void demonstrateUserInput() {
        screenHandler.displaySubHeader("User Input Demonstration");
        
        String name = screenHandler.acceptInput("Enter your name: ");
        screenHandler.displayLine("Hello, " + name + "!");
        
        int age = screenHandler.acceptNumericInput("Enter your age: ");
        screenHandler.displayLine("You are " + age + " years old.");
        
        double salary = screenHandler.acceptDecimalInput("Enter your salary: ");
        screenHandler.displayLine("Your salary is $" + String.format("%.2f", salary));
        
        screenHandler.displaySpace();
    }
    
    private void demonstrateFormattedOutput() {
        screenHandler.displaySubHeader("Formatted Output Demonstration");
        
        screenHandler.displayLine("This is a regular line.");
        screenHandler.displayWithNoAdvancing("This line has no advancing... ");
        screenHandler.displayLine("and this continues on the same line.");
        
        screenHandler.displaySpace();
        screenHandler.displaySeparator("*", 40);
        screenHandler.displayLine("This is surrounded by separators");
        screenHandler.displaySeparator("*", 40);
        
        screenHandler.displaySpace();
    }
    
    private void cleanup() {
        if (screenHandler != null) {
            screenHandler.close();
        }
    }
}
