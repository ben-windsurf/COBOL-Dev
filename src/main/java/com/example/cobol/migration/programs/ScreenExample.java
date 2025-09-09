package com.example.cobol.migration.programs;

import com.example.cobol.migration.ui.ScreenHandler;

/**
 * Screen Example demonstrating COBOL screen handling conversion to Java.
 * Converted from COBOL screen handling patterns to Java console operations.
 */
public final class ScreenExample {
    /** Screen handler for console operations. */
    private ScreenHandler screenHandler;

    /**
     * Default constructor.
     */
    public ScreenExample() {
        this.screenHandler = new ScreenHandler();
    }

    /**
     * Main method to start the screen example.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        ScreenExample example = new ScreenExample();
        try {
            example.run();
        } finally {
            example.cleanup();
        }
    }

    /**
     * Run the screen handling demonstration.
     */
    public void run() {
        screenHandler.clearScreen();
        screenHandler.displayHeader("COBOL Screen Handling Example");

        demonstrateScreenSize();
        demonstrateUserInput();
        demonstrateFormattedOutput();

        screenHandler.displayLine("Program completed successfully.");
    }

    /**
     * Demonstrate screen size detection functionality.
     */
    private void demonstrateScreenSize() {
        screenHandler.displaySubHeader("Screen Size Detection");

        ScreenHandler.ScreenSize screenSize = screenHandler.getScreenSize();
        screenHandler.displayLine("Detected screen size:");
        screenHandler.displayLine("Rows: " + screenSize.getRows());
        screenHandler.displayLine("Columns: " + screenSize.getColumns());
        screenHandler.displaySpace();
    }

    /**
     * Demonstrate user input functionality.
     */
    private void demonstrateUserInput() {
        screenHandler.displaySubHeader("User Input Demonstration");

        String name = screenHandler.acceptInput("Enter your name: ");
        screenHandler.displayLine("Hello, " + name + "!");

        int age = screenHandler.acceptNumericInput("Enter your age: ");
        screenHandler.displayLine("You are " + age + " years old.");

        double salary = screenHandler.acceptDecimalInput("Enter your salary: ");
        screenHandler.displayLine("Your salary is $"
                + String.format("%.2f", salary));

        screenHandler.displaySpace();
    }

    /**
     * Demonstrate formatted output functionality.
     */
    private void demonstrateFormattedOutput() {
        screenHandler.displaySubHeader("Formatted Output Demonstration");

        screenHandler.displayLine("This is a regular line.");
        screenHandler.displayWithNoAdvancing("This line has no advancing... ");
        screenHandler.displayLine("and this continues on the same line.");

        screenHandler.displaySpace();
        final int separatorLength = 40;
        screenHandler.displaySeparator("*", separatorLength);
        screenHandler.displayLine("This is surrounded by separators");
        screenHandler.displaySeparator("*", separatorLength);

        screenHandler.displaySpace();
    }

    /**
     * Clean up resources.
     */
    private void cleanup() {
        if (screenHandler != null) {
            screenHandler.close();
        }
    }
}
