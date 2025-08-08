package com.cobol.migration.trim;

public class TrimUtil {
    
    public static String trimBoth(String input) {
        if (input == null) {
            return null;
        }
        return input.trim();
    }
    
    public static String trimLeading(String input) {
        if (input == null) {
            return null;
        }
        return input.stripLeading();
    }
    
    public static String trimTrailing(String input) {
        if (input == null) {
            return null;
        }
        return input.stripTrailing();
    }
}
