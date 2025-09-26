package com.example.comp;

import java.util.Scanner;

public class CompTest {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int compVal = 12;
        compVal = compVal * 2;
        System.out.println("COMP: " + compVal);
        
        int dispVal = compVal;
        System.out.println("DISP:" + dispVal);
        
        String dynDispVal = String.format("%3d", compVal);
        System.out.println("DYNA:" + dynDispVal);
        
        System.out.print("INPUT: ");
        int input = scanner.nextInt();
        
        System.out.println("INPUT: " + input);
        
        compVal = input;
        System.out.println("COMP: " + compVal);
        
        scanner.close();
    }
}
