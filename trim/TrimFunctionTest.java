public class TrimFunctionTest {
    private static final int FIXED_LENGTH = 30;
    
    public static void main(String[] args) {
        String wsTestString1 = createFixedLengthString("    hello world       ");
        String wsTestString2;
        
        System.out.println("--" + wsTestString1 + "--");
        System.out.println("--" + trim(wsTestString1) + "--");
        System.out.println("--" + trimLeading(wsTestString1) + "--");
        System.out.println("--" + trimTrailing(wsTestString1) + "--");
        
        wsTestString2 = moveString("******************************");
        System.out.println(wsTestString2);
        wsTestString2 = moveString(wsTestString1);
        System.out.println(wsTestString2);
        
        wsTestString2 = moveString("******************************");
        System.out.println(wsTestString2);
        wsTestString2 = moveString(trim(wsTestString1));
        System.out.println(wsTestString2);
        
        wsTestString2 = moveString("******************************");
        System.out.println(wsTestString2);
        wsTestString2 = moveString(trimLeading(wsTestString1));
        System.out.println(wsTestString2);
        
        wsTestString2 = moveString("******************************");
        System.out.println(wsTestString2);
        wsTestString2 = moveString(trimTrailing(wsTestString1));
        System.out.println(wsTestString2);
        
        System.out.println("--" + "    String literal    " + "--");
        System.out.println("--" + trim("   String literal    ") + "--");
        System.out.println("--" + trimLeading("     String literal   ") + "--");
        System.out.println("--" + trimTrailing("   String literal    ") + "--");
    }
    
    private static String createFixedLengthString(String input) {
        if (input.length() >= FIXED_LENGTH) {
            return input.substring(0, FIXED_LENGTH);
        } else {
            StringBuilder sb = new StringBuilder(input);
            while (sb.length() < FIXED_LENGTH) {
                sb.append(' ');
            }
            return sb.toString();
        }
    }
    
    private static String moveString(String input) {
        return createFixedLengthString(input);
    }
    
    private static String trim(String input) {
        return input.trim();
    }
    
    private static String trimLeading(String input) {
        int start = 0;
        while (start < input.length() && input.charAt(start) == ' ') {
            start++;
        }
        return input.substring(start);
    }
    
    private static String trimTrailing(String input) {
        int end = input.length();
        while (end > 0 && input.charAt(end - 1) == ' ') {
            end--;
        }
        return input.substring(0, end);
    }
}
