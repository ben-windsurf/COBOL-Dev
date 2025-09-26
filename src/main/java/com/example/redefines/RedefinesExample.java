package com.example.redefines;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class RedefinesExample {
    
    public static class DataStructure {
        private byte[] data;
        
        public DataStructure() {
            data = new byte[20];
        }
        
        public void setAsString(String value) {
            byte[] bytes = value.getBytes();
            System.arraycopy(bytes, 0, data, 0, Math.min(bytes.length, data.length));
        }
        
        public String getAsString() {
            return new String(data).trim();
        }
        
        public void setAsInteger(int value) {
            ByteBuffer buffer = ByteBuffer.allocate(4);
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            buffer.putInt(value);
            byte[] intBytes = buffer.array();
            System.arraycopy(intBytes, 0, data, 0, Math.min(intBytes.length, data.length));
        }
        
        public int getAsInteger() {
            ByteBuffer buffer = ByteBuffer.allocate(4);
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            buffer.put(data, 0, 4);
            buffer.flip();
            return buffer.getInt();
        }
        
        public byte[] getRawData() {
            return data.clone();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("COBOL REDEFINES equivalent in Java");
        System.out.println("Note: Java doesn't have direct REDEFINES, but we can simulate");
        System.out.println("the concept using different views of the same data.");
        System.out.println();
        
        DataStructure data = new DataStructure();
        
        System.out.println("Setting data as string: 'Hello World'");
        data.setAsString("Hello World");
        System.out.println("String view: '" + data.getAsString() + "'");
        System.out.println("Integer view: " + data.getAsInteger());
        System.out.println("Raw bytes: " + java.util.Arrays.toString(data.getRawData()));
        System.out.println();
        
        System.out.println("Setting data as integer: 123456");
        data.setAsInteger(123456);
        System.out.println("String view: '" + data.getAsString() + "'");
        System.out.println("Integer view: " + data.getAsInteger());
        System.out.println("Raw bytes: " + java.util.Arrays.toString(data.getRawData()));
    }
}
