package com.example.accept;

import java.io.Console;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AcceptExample {
    
    private Scanner scanner;
    
    public AcceptExample() {
        scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args) {
        AcceptExample app = new AcceptExample();
        app.run();
    }
    
    public void run() {
        String input;
        
        System.out.print("Simple accept. Enter a value: ");
        input = scanner.nextLine();
        System.out.println("You entered: " + input);
        
        System.out.println("Press any key to enter screen mode.");
        scanner.nextLine();
        
        clearScreen();
        
        System.out.print("\033[1;1HEnter value or wait 3 seconds: ");
        input = readWithTimeout(3000);
        System.out.print("\033[2;1HYou entered: " + input);
        
        System.out.print("\033[3;1HEnter 16 chars to auto skip: ");
        input = readWithMaxLength(16);
        System.out.print("\033[4;1HYou entered: " + input);
        
        System.out.print("\033[5;1HEnter a value (no echo): ");
        input = readWithNoEcho();
        System.out.print("\033[6;1HYou entered: " + input);
        
        System.out.print("\033[7;1HEnter a value: ");
        input = scanner.nextLine().toUpperCase();
        System.out.print("\033[8;1HYou entered: " + input);
        
        System.out.println();
    }
    
    private void clearScreen() {
        System.out.print("\033[2J\033[H");
    }
    
    private String readWithTimeout(int timeoutMs) {
        try {
            if (System.in.available() > 0 || waitForInput(timeoutMs)) {
                return scanner.nextLine();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }
    
    private boolean waitForInput(int timeoutMs) {
        try {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < timeoutMs) {
                if (System.in.available() > 0) {
                    return true;
                }
                Thread.sleep(50);
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    private String readWithMaxLength(int maxLength) {
        StringBuilder input = new StringBuilder();
        try {
            int ch;
            while (input.length() < maxLength && (ch = System.in.read()) != '\n' && ch != '\r') {
                if (ch >= 32 && ch <= 126) {
                    input.append((char) ch);
                    System.out.print((char) ch);
                }
            }
            if (input.length() < maxLength) {
                scanner.nextLine();
            }
        } catch (Exception e) {
            return input.toString();
        }
        return input.toString();
    }
    
    private String readWithNoEcho() {
        Console console = System.console();
        if (console != null) {
            char[] password = console.readPassword();
            return new String(password);
        } else {
            return scanner.nextLine();
        }
    }
}
