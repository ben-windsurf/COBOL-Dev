package com.example.cobol.migration.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Screen Handler for COBOL to Java migration.
 * Handles console input/output operations converted from COBOL screen handling.
 */
public final class ScreenHandler {
    /** Scanner for user input. */
    private Scanner scanner;
    /** BufferedReader for line input. */
    private BufferedReader reader;

    /**
     * Default constructor.
     */
    public ScreenHandler() {
        this.scanner = new Scanner(System.in);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Display message to console.
     *
     * @param message message to display
     */
    public void display(final String message) {
        System.out.print(message);
    }

    /**
     * Display message with line break.
     *
     * @param message message to display
     */
    public void displayLine(final String message) {
        System.out.println(message);
    }

    /**
     * Display message without advancing to next line.
     *
     * @param message message to display
     */
    public void displayWithNoAdvancing(final String message) {
        System.out.print(message);
        System.out.flush();
    }

    /**
     * Accept input from user.
     *
     * @return user input string
     */
    public String acceptInput() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
            return "";
        }
    }

    /**
     * Accept input from user with prompt.
     *
     * @param prompt prompt message to display
     * @return user input string
     */
    public String acceptInput(final String prompt) {
        displayWithNoAdvancing(prompt);
        return acceptInput();
    }

    /**
     * Accept numeric input from user with prompt.
     *
     * @param prompt prompt message to display
     * @return numeric input value
     */
    public int acceptNumericInput(final String prompt) {
        while (true) {
            String input = acceptInput(prompt);
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                displayLine("Invalid numeric input. Please try again.");
            }
        }
    }

    /**
     * Accept decimal input from user with prompt.
     *
     * @param prompt prompt message to display
     * @return decimal input value
     */
    public double acceptDecimalInput(final String prompt) {
        while (true) {
            String input = acceptInput(prompt);
            try {
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException e) {
                displayLine("Invalid decimal input. Please try again.");
            }
        }
    }

    /**
     * Clear the console screen.
     */
    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls")
                        .inheritIO().start().waitFor();
            } else {
                System.out.print("\033[2J\033[H");
                System.out.flush();
            }
        } catch (Exception e) {
            final int fallbackLines = 50;
            for (int i = 0; i < fallbackLines; i++) {
                System.out.println();
            }
        }
    }

    /**
     * Display a blank line.
     */
    public void displaySpace() {
        System.out.println();
    }

    /**
     * Display a separator line with specified character and length.
     *
     * @param character character to repeat
     * @param length number of characters to display
     */
    public void displaySeparator(final String character, final int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(character);
        }
        System.out.println();
    }

    /**
     * Display a header with title surrounded by separators.
     *
     * @param title header title to display
     */
    public void displayHeader(final String title) {
        final int headerWidth = 50;
        displaySeparator("=", headerWidth);
        displayLine(title);
        displaySeparator("=", headerWidth);
    }

    /**
     * Display a sub-header with title surrounded by separators.
     *
     * @param title sub-header title to display
     */
    public void displaySubHeader(final String title) {
        final int subHeaderWidth = 30;
        displaySeparator("-", subHeaderWidth);
        displayLine(title);
        displaySeparator("-", subHeaderWidth);
    }

    /**
     * Get current screen size.
     *
     * @return screen size object with rows and columns
     */
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
            BufferedReader inputReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line = inputReader.readLine();

            if (line != null && !line.trim().isEmpty()) {
                if (os.contains("windows")) {
                    String[] parts = line.split("\\s+");
                    for (String part : parts) {
                        if (part.contains("Columns:")) {
                            int cols = Integer.parseInt(
                                    part.split(":")[1].trim());
                            final int defaultRows = 24;
                            return new ScreenSize(defaultRows, cols);
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
            System.err.println("Could not determine screen size: "
                    + e.getMessage());
        }

        final int defaultRows = 24;
        final int defaultCols = 80;
        return new ScreenSize(defaultRows, defaultCols);
    }

    /**
     * Close scanner and reader resources.
     */
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

    /**
     * Screen size data class.
     */
    public static class ScreenSize {
        /** Number of rows. */
        private final int rows;
        /** Number of columns. */
        private final int columns;

        /**
         * Constructor for screen size.
         *
         * @param screenRows number of rows
         * @param screenColumns number of columns
         */
        public ScreenSize(final int screenRows, final int screenColumns) {
            this.rows = screenRows;
            this.columns = screenColumns;
        }

        /**
         * Get number of rows.
         *
         * @return number of rows
         */
        public int getRows() {
            return rows;
        }

        /**
         * Get number of columns.
         *
         * @return number of columns
         */
        public int getColumns() {
            return columns;
        }

        /**
         * String representation of screen size.
         *
         * @return string representation
         */
        @Override
        public String toString() {
            return "ScreenSize{rows=" + rows + ", columns=" + columns + "}";
        }
    }
}
