package com.example.displaytiming;

public class DisplayTiming {
    
    public static void main(String[] args) {
        System.out.println("Display timing test - Java version");
        System.out.println("Note: Java console output is typically buffered");
        System.out.println();
        
        for (int i = 1; i <= 10; i++) {
            System.out.print("Message " + i + " ");
            System.out.flush();
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        
        System.out.println();
        System.out.println("Timing test complete");
    }
}
