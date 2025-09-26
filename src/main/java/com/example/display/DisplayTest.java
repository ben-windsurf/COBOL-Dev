package com.example.display;

public class DisplayTest {
    
    public static void main(String[] args) {
        System.out.print("\033[5;5H" + "hello world");
        
        System.out.print("\033[6;5H" + "hello world");
        
        System.out.print("\033[7;5H");
        System.out.print("\033[2K");
        System.out.print("hello world");
        
        System.out.print("\033[8;5H");
        System.out.print("\033[K");
        System.out.print("hello world");
        
        System.out.print("\033[9;5H");
        System.out.print("\007");
        System.out.print("hello world");
        
        System.out.print("\033[10;5H");
        System.out.print("\033[43m\033[36m");
        System.out.print("hello world");
        System.out.print("\033[0m");
        
        System.out.println();
    }
}
