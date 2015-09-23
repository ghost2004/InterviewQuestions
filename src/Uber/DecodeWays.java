package Uber;
/*
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2
 */
public class DecodeWays {
    
    public boolean isValid(String input) {
        if (input.charAt(0) == '0')
            return false;
        int val = Integer.parseInt(input);
        
        return (val >= 1 && val <= 26);
                
    }

    public int numDecodings(String s) {
        if (s ==null || s.length() == 0 ||s.charAt(0) == '0')
            return 0;
        if (s.length() == 1)
            return 1;
        int len = s.length();
        
        // way to decode at (i-2)
        int prevTwoCnt = 1;
        // way to decode at (i-1)
        int prevOneCnt = 1;
        
        for (int i = 2; i <= len; i++) {
            int ways = 0;
            if (isValid(s.substring(i-1, i)))
                ways += prevOneCnt;
            if (isValid(s.substring(i-2, i)))
                ways += prevTwoCnt;
            
            prevTwoCnt = prevOneCnt;
            prevOneCnt = ways;

                
        }
        return prevOneCnt;
    }

}
