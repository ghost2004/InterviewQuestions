package Uber;
/*
 * The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
 */
public class CountAndSay {
    public static String countAndSay(int n) {
        if (n == 0)
            return "";
        String result = "1";
        for (int i = 2; i <= n;i++) {
            StringBuffer buf = new StringBuffer();
            int idx = 0;
            do {
                char key = result.charAt(idx);
                int cnt = 1;
                while ((idx+1) < result.length() && result.charAt(idx+1) == key) {
                    cnt++;
                    idx++;
                }
                buf.append(cnt);
                buf.append(key);
                idx++;
            } while (idx < result.length());
            
            result = buf.toString();
        }
        
        return result;
    }
    
    public static void main(String args[]) {
        //System.out.println(countAndSay(2));
        System.out.println(countAndSay(4));
    }

}
