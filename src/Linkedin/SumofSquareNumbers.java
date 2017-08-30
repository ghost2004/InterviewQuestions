package Linkedin;
/*
 * Leetcode 633. Sum of Square Numbers
 * 
 * Given a non-negative integer c, your task is to decide whether there're two 
 * integers a and b such that a^2 + b^2 = c.

Example 1:
Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5
Example 2:
Input: 3
Output: False
 */
public class SumofSquareNumbers {
    public boolean judgeSquareSum(int c) {
        if (c < 0)
            return false;
        int left = 0;
        int right = (int)Math.sqrt(c);
        while (left <= right) {
            int sum = left*left + right*right;
            if (sum < c)
                left++;
            else if (sum > c)
                right--;
            else
                return true;
        }
        
        return false;
    }
}
