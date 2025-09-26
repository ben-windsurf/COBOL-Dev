package com.example.subprogram;

import java.util.Scanner;

public class MainApp {
    
    private String item1;
    private String item2;
    private Scanner scanner;
    
    public MainApp() {
        scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.run();
    }
    
    public void run() {
        System.out.println();
        System.out.print("Enter value for #1: ");
        item1 = scanner.nextLine();
        
        System.out.print("Enter value for #2: ");
        item2 = scanner.nextLine();
        
        displayMessage();
        
        System.out.println("Calling sub program by content:");
        SubApp subApp = new SubApp();
        subApp.callByContent(item1, item2);
        displayMessage();
        
        System.out.println("Second call of sub program should retain WS values.");
        System.out.println("Calling sub program by reference:");
        String[] items = {item1, item2};
        subApp.callByReference(items);
        item1 = items[0];
        item2 = items[1];
        displayMessage();
        
        System.out.println("Cancelling sub program");
        subApp = new SubApp();
        System.out.println("Calling sub program. WS values should be reset:");
        String[] resetItems = {item1, item2};
        subApp.callByReference(resetItems);
        item1 = resetItems[0];
        item2 = resetItems[1];
        displayMessage();
    }
    
    private void displayMessage() {
        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("Main app: " + item1 + item2);
    }
}
