package com.example.cobol.migration.programs;

import java.util.Scanner;

public class MainApp {
    private String wsItem1;
    private String wsItem2;
    private final Scanner scanner;
    private SubApp subApp;
    
    public MainApp() {
        this.wsItem1 = "";
        this.wsItem2 = "";
        this.scanner = new Scanner(System.in);
        this.subApp = new SubApp();
    }
    
    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.run();
    }
    
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
    
    private void displayMessage() {
        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("Main app: " + padRight(wsItem1, 10) + padRight(wsItem2, 10));
    }
    
    private String padRight(String str, int length) {
        if (str == null) str = "";
        if (str.length() >= length) return str;
        return str + " ".repeat(length - str.length());
    }
}
