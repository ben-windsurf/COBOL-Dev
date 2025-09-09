package com.example.cobol.migration.programs;

import java.util.Scanner;

/**
 * Main Application demonstrating COBOL to Java migration.
 * Converted from COBOL main_app.cbl program.
 * Shows parameter passing and program communication patterns.
 */
public class MainApp {
    private String wsItem1;
    private String wsItem2;
    private final Scanner scanner;
    private SubApp subApp;

    /**
     * Default constructor.
     */
    public MainApp() {
        this.wsItem1 = "";
        this.wsItem2 = "";
        this.scanner = new Scanner(System.in);
        this.subApp = new SubApp();
    }

    /**
     * Main method to start the application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        MainApp app = new MainApp();
        app.run();
    }

    /**
     * Main execution method that handles user input and program flow.
     */
    public void run() {
        System.out.println();
        System.out.print("Enter value for #1: ");
        this.wsItem1 = scanner.nextLine().trim();
        if (this.wsItem1.length() > 10) {
            this.wsItem1 = this.wsItem1.substring(0, 10);
        }

        System.out.print("Enter value for #2: ");
        this.wsItem2 = scanner.nextLine().trim();
        if (this.wsItem2.length() > 10) {
            this.wsItem2 = this.wsItem2.substring(0, 10);
        }

        displayMessage();

        System.out.println("Calling sub program by content:");
        String item1Copy = this.wsItem1;
        String item2Copy = this.wsItem2;
        subApp.execute(item1Copy, item2Copy, false);
        displayMessage();

        System.out.println("Second call of sub program should retain WS values.");
        System.out.println("Calling sub program by reference:");
        String[] result = subApp.execute(this.wsItem1, this.wsItem2, true);
        this.wsItem1 = result[0];
        this.wsItem2 = result[1];
        displayMessage();

        System.out.println("Cancelling sub program");
        subApp.cancel();
        System.out.println("Calling sub program. WS values should be reset:");
        result = subApp.execute(this.wsItem1, this.wsItem2, true);
        this.wsItem1 = result[0];
        this.wsItem2 = result[1];
        displayMessage();

        scanner.close();
    }

    /**
     * Display current working storage values.
     */
    private void displayMessage() {
        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("Main app: " + padRight(wsItem1, 10) + padRight(wsItem2, 10));
    }

    /**
     * Pad string to the right with spaces.
     *
     * @param str string to pad
     * @param length target length
     * @return padded string
     */
    private String padRight(final String str, final int length) {
        if (str == null) return "";
        if (str.length() >= length) return str;
        return str + " ".repeat(length - str.length());
    }
}
