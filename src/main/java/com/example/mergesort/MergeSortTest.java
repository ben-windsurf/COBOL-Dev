package com.example.mergesort;

import java.io.*;
import java.util.*;

public class MergeSortTest {
    
    public static void main(String[] args) {
        System.out.println("Note: This is a simplified version of the COBOL merge/sort example.");
        System.out.println("COBOL's MERGE and SORT verbs work with files, while this Java version");
        System.out.println("demonstrates in-memory sorting and merging of data.");
        System.out.println();
        
        List<String> data1 = Arrays.asList("apple", "cherry", "grape");
        List<String> data2 = Arrays.asList("banana", "date", "fig");
        
        System.out.println("Data set 1: " + data1);
        System.out.println("Data set 2: " + data2);
        System.out.println();
        
        List<String> merged = mergeAndSort(data1, data2);
        System.out.println("Merged and sorted result: " + merged);
        
        System.out.println();
        System.out.println("Individual sorting:");
        List<String> sorted1 = new ArrayList<>(data1);
        Collections.sort(sorted1);
        System.out.println("Sorted data set 1: " + sorted1);
        
        List<String> sorted2 = new ArrayList<>(data2);
        Collections.sort(sorted2);
        System.out.println("Sorted data set 2: " + sorted2);
    }
    
    private static List<String> mergeAndSort(List<String> list1, List<String> list2) {
        List<String> combined = new ArrayList<>();
        combined.addAll(list1);
        combined.addAll(list2);
        Collections.sort(combined);
        return combined;
    }
}
