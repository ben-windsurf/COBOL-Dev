package com.example.commandargs;

public class ReadCommandArgs {
    
    public static void main(String[] args) {
        System.out.println();
        System.out.println("Pass arg '--test' for special message");
        
        String fullCommandLine = String.join(" ", args);
        System.out.println("Full command line args: " + fullCommandLine);
        
        int testArgCount = 0;
        String lowerCaseArgs = fullCommandLine.toLowerCase();
        int index = 0;
        while ((index = lowerCaseArgs.indexOf("--test", index)) != -1) {
            testArgCount++;
            index += "--test".length();
        }
        
        if (testArgCount > 0) {
            System.out.println("You entered the '--test' cmd arg!");
        }
        
        System.out.println();
    }
}
