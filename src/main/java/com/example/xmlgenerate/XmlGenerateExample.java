package com.example.xmlgenerate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.StringWriter;

public class XmlGenerateExample {
    
    @XmlRootElement(name = "ws-record")
    public static class Record {
        private String recordName;
        private String recordValue;
        private boolean recordFlag;
        
        public Record() {}
        
        public Record(String name, String value, boolean flag) {
            this.recordName = name;
            this.recordValue = value;
            this.recordFlag = flag;
        }
        
        @XmlElement(name = "name")
        public String getRecordName() { return recordName; }
        public void setRecordName(String recordName) { this.recordName = recordName; }
        
        @XmlElement(name = "value")
        public String getRecordValue() { return recordValue; }
        public void setRecordValue(String recordValue) { this.recordValue = recordValue; }
        
        @XmlAttribute(name = "enabled")
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
            
            JAXBContext jaxbContext = JAXBContext.newInstance(Record.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(wsRecord, stringWriter);
            String xmlOutput = stringWriter.toString();
            
            System.out.println("XML document successfully generated.");
            System.out.println("Generated xml for record: " + wsRecord);
            System.out.println("----------------------------");
            System.out.println(xmlOutput.trim());
            System.out.println("----------------------------");
            System.out.println("XML output character count: " + xmlOutput.length());
            System.out.println("Done.");
            
        } catch (JAXBException e) {
            System.out.println("Error generating XML: " + e.getMessage());
            System.exit(1);
        }
    }
}
