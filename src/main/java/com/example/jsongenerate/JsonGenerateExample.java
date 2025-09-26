package com.example.jsongenerate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonGenerateExample {
    
    public static class Record {
        @JsonProperty("name")
        private String recordName;
        
        @JsonProperty("value") 
        private String recordValue;
        
        @JsonProperty("enabled")
        private boolean recordFlag;
        
        public Record() {}
        
        public Record(String name, String value, boolean flag) {
            this.recordName = name;
            this.recordValue = value;
            this.recordFlag = flag;
        }
        
        public String getRecordName() { return recordName; }
        public void setRecordName(String recordName) { this.recordName = recordName; }
        
        public String getRecordValue() { return recordValue; }
        public void setRecordValue(String recordValue) { this.recordValue = recordValue; }
        
        public boolean isRecordFlag() { return recordFlag; }
        public void setRecordFlag(boolean recordFlag) { this.recordFlag = recordFlag; }
        
        @Override
        public String toString() {
            return String.format("Record{name='%s', value='%s', flag=%s}", 
                recordName, recordValue, recordFlag);
        }
    }
    
    public static void main(String[] args) {
        try {
            Record wsRecord = new Record("Test Name", "Test Value", true);
            
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonOutput = objectMapper.writeValueAsString(wsRecord);
            
            System.out.println("JSON document successfully generated.");
            System.out.println("Generated JSON for record: " + wsRecord);
            System.out.println("----------------------------");
            System.out.println(jsonOutput);
            System.out.println("----------------------------");
            System.out.println("JSON output character count: " + jsonOutput.length());
            System.out.println("Done.");
            
        } catch (Exception e) {
            System.out.println("Error generating JSON: " + e.getMessage());
            System.exit(1);
        }
    }
}
