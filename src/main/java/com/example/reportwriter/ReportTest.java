package com.example.reportwriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReportTest {
    
    public static class SalesRecord {
        private String region;
        private String product;
        private double amount;
        private String date;
        
        public SalesRecord(String region, String product, double amount, String date) {
            this.region = region;
            this.product = product;
            this.amount = amount;
            this.date = date;
        }
        
        public String getRegion() { return region; }
        public String getProduct() { return product; }
        public double getAmount() { return amount; }
        public String getDate() { return date; }
    }
    
    public static void main(String[] args) {
        System.out.println("COBOL Report Writer equivalent in Java");
        System.out.println("Generating a formatted sales report");
        System.out.println();
        
        List<SalesRecord> salesData = createSampleData();
        generateReport(salesData);
    }
    
    private static List<SalesRecord> createSampleData() {
        List<SalesRecord> data = new ArrayList<>();
        data.add(new SalesRecord("North", "Widget A", 1250.50, "2023-01-15"));
        data.add(new SalesRecord("North", "Widget B", 875.25, "2023-01-16"));
        data.add(new SalesRecord("South", "Widget A", 2100.75, "2023-01-17"));
        data.add(new SalesRecord("South", "Widget C", 1500.00, "2023-01-18"));
        data.add(new SalesRecord("East", "Widget B", 950.25, "2023-01-19"));
        data.add(new SalesRecord("West", "Widget A", 1750.50, "2023-01-20"));
        return data;
    }
    
    private static void generateReport(List<SalesRecord> salesData) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = LocalDateTime.now().format(formatter);
        
        System.out.println("                    SALES REPORT");
        System.out.println("                Generated: " + currentTime);
        System.out.println("================================================================");
        System.out.println();
        System.out.printf("%-10s %-12s %-10s %-12s%n", "REGION", "PRODUCT", "AMOUNT", "DATE");
        System.out.println("---------- ------------ ---------- ------------");
        
        double totalAmount = 0.0;
        String currentRegion = "";
        double regionTotal = 0.0;
        
        for (SalesRecord record : salesData) {
            if (!record.getRegion().equals(currentRegion)) {
                if (!currentRegion.isEmpty()) {
                    System.out.println("                            ----------");
                    System.out.printf("%-22s %10.2f%n", "Region Total:", regionTotal);
                    System.out.println();
                }
                currentRegion = record.getRegion();
                regionTotal = 0.0;
            }
            
            System.out.printf("%-10s %-12s %10.2f %-12s%n", 
                record.getRegion(), 
                record.getProduct(), 
                record.getAmount(), 
                record.getDate());
            
            regionTotal += record.getAmount();
            totalAmount += record.getAmount();
        }
        
        if (!currentRegion.isEmpty()) {
            System.out.println("                            ----------");
            System.out.printf("%-22s %10.2f%n", "Region Total:", regionTotal);
        }
        
        System.out.println();
        System.out.println("================================================================");
        System.out.printf("%-22s %10.2f%n", "GRAND TOTAL:", totalAmount);
        System.out.println("================================================================");
    }
}
