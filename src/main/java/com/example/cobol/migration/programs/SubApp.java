package com.example.cobol.migration.programs;

public class SubApp {
    private String wsTestItem1;
    private String wsTestItem2;
    
    public SubApp() {
        this.wsTestItem1 = "";
        this.wsTestItem2 = "";
    }
    
    public String[] execute(String lTestItem1, String lTestItem2, boolean byReference) {
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
        
        this.wsTestItem1 = lTestItem1;
        this.wsTestItem2 = lTestItem2;
        lsTestItem1 = lTestItem1;
        lsTestItem2 = lTestItem2;
        
        System.out.println("setting input variables to new value...");
        lTestItem1 = "replace1";
        lTestItem2 = "replace2";
        
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
        
        if (byReference) {
            return new String[]{lTestItem1, lTestItem2};
        } else {
            return new String[]{lTestItem1, lTestItem2};
        }
    }
    
    public void cancel() {
        this.wsTestItem1 = "";
        this.wsTestItem2 = "";
    }
}
