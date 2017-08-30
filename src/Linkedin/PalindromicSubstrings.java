package Linkedin;
/*
 * Leetcode 647. Palindromic Substrings
 * Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings 
even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
Note:
The input string length won't exceed 1000.
 */
public class PalindromicSubstrings {
    private int count(String s, int start, int end) {
        int cnt = 0;
        if (start != end && s.charAt(start) != s.charAt(end))
            return 0;
        int left = start;
        int right = end;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            cnt++;
            left--;
            right++;
        }
        return cnt;
    }
    
    public int countSubstrings(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            cnt += count(s, i, i);
            if (i < s.length()-1)
                cnt += count(s, i, i+1);
            
        }
        
        return cnt;
    }

}
