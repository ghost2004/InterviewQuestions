package Linkedin;
/*
 * Given a string S, find the longest palindromic substring in S. 
 * You may assume that the maximum length of S is 1000, and there
 *  exists one unique longest palindromic substring.
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2)
            return s;
        int len = s.length();
        // Matrix for DP 
        // dpFlag[i][j] is true when substring [i,j] is Palindrome
        boolean flag[][] = new boolean[len][len];
        // start/end of position  
        int maxStart = 0;
        int maxEnd = 1;
        int maxLen = 0;
        
        for (int i = len-1;i >= 0 ; i--) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j) && (j-i<2 || flag[i+1][j-1])) {
                    flag[i][j] = true;
                    if (j - i > maxLen) {
                        maxLen = j -i;
                        maxStart = i;
                        maxEnd = j+1;
                    }
                } else 
                    flag[i][j] = false;
            }
        }
        
        
        return s.substring(maxStart, maxEnd);
    }
}
