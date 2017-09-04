package Linkedin;

import java.util.Arrays;

/*
 * Given a string, find the minimum number of characters to be inserted to convert it to palindrome.

Before we go further, let us understand with few examples:
    ab: Number of insertions required is 1. bab
    aa: Number of insertions required is 0. aa
    abcd: Number of insertions required is 3. dcbabcd
    abcda: Number of insertions required is 2. adcbcda which is same as number of insertions in the substring bcd(Why?).
    abcde: Number of insertions required is 4. edcbabcde
 */
public class InsertForPalindrome {
    //Recursive Solution
    public int recurFindMin(char str[], int left, int right) {
        if (left > right)
            return Integer.MAX_VALUE;
        if (left == right)
            return 0;
        if (left == right-1) 
            return str[left] == str[right] ? 0 : 1;
        
        if (str[left] == str[right]) {
            return recurFindMin(str, left+1, right-1);
        }
        
        return Math.min(recurFindMin(str, left+1, right)+1, recurFindMin(str, left, right-1)+1);
    }
    

   public int findInsertMin2(String str) {
       return recurFindMin(str.toCharArray(), 0 , str.length()-1);
   }
   
   // DP solution
   
   public int findInsertMin(String str) {
       char arr[] = str.toCharArray();
       int n = str.length();
       int dp[][] = new int[n][n];
       for (int i = 0; i < n; i++)
           Arrays.fill(dp[i], 0);
       
       int left,right;
       
       for (int len = 1; len < n; len++) {
           for (left = 0, right = len; right < n; left++, right++ ) {
               if (arr[left] == arr[right]) {
                   dp[left][right] = dp[left+1][right-1];
               } else {
                   dp[left][right] = Math.min(dp[left+1][right]+1, dp[left][right-1]+1);
               }
           }
       }
       
       return dp[0][n-1];
       
   }
            
}
