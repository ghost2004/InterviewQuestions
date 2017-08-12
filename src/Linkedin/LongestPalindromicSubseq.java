package Linkedin;
/*
 * Leetcode 516. Longest Palindromic Subsequence
 * Given a string s, find the longest palindromic subsequence's length in s. 
 * You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".
 */
public class LongestPalindromicSubseq {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() < 1)
            return 0;
        int strLen = s.length();
        int dp[][] = new int[strLen][strLen];
        
        for (int i = 0; i < strLen; i++) 
            dp[i][i] = 1;
        
        
        for (int rIdx = 1 ; rIdx < strLen; rIdx++) {

            
        }
        
        return dp[0][strLen-1];
    }

}
