package Linkedin;
/*
 * Leetcode 409. Longest Palindrome
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
import java.util.*;
public class LongestPalindrome {
    
    public int longestPalindrome(String s) {
        if (s == null || s.length() < 1)
            return 0;
        int count = 0;
        HashSet<Character> set = new HashSet<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                count += 2;
                set.remove(c);
            } else
                set.add(c);
            
        }
        
        if (!set.isEmpty())
            count++;
        return count;
    }

}
