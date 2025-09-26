package com.example.subprogram;

public class SubApp {
    
    private String wsTestItem1 = "";
    private String wsTestItem2 = "";
    
    public void callByContent(String item1, String item2) {
        String lTestItem1 = item1;
        String lTestItem2 = item2;
        
        processCall(lTestItem1, lTestItem2, false);
    }
    
    public void callByReference(String[] items) {
        String lTestItem1 = items[0];
        String lTestItem2 = items[1];
        
        processCall(lTestItem1, lTestItem2, true);
        
        items[0] = lTestItem1;
        items[1] = lTestItem2;
    }
    
    private void processCall(String lTestItem1, String lTestItem2, boolean byReference) {
        String lsTestItem1 = "";
        String lsTestItem2 = "";
        
        System.out.println("In sub program: " + lTestItem1 + " " + lTestItem2);
        System.out.println();
        System.out.println("working-storage values at start:");
        System.out.println("ws-test-item-1: " + wsTestItem1);
        System.out.println("ws-test-item-2: " + wsTestItem2);
        System.out.println();
        System.out.println("local-storage values at start:");
        System.out.println("ls-test-item-1: " + lsTestItem1);
        System.out.println("ls-test-item-2: " + lsTestItem2);
        System.out.println();
        System.out.println("Moving linkage section values to ws and ls vars..");
        
        wsTestItem1 = lTestItem1;
        wsTestItem2 = lTestItem2;
        lsTestItem1 = lTestItem1;
        lsTestItem2 = lTestItem2;
        
        System.out.println("setting input variables to new value...");
        if (byReference) {
            lTestItem1 = "replace1";
            lTestItem2 = "replace2";
        }
        
        System.out.println();
        System.out.println("working-storage values at end:");
        System.out.println("ws-test-item-1: " + wsTestItem1);
        System.out.println("ws-test-item-2: " + wsTestItem2);
        System.out.println();
        System.out.println("local-storage values at end:");
        System.out.println("ls-test-item-1: " + lsTestItem1);
        System.out.println("ls-test-item-2: " + lsTestItem2);
        System.out.println();
        System.out.println("Exit sub program: " + lTestItem1 + " " + lTestItem2);
    }
}
