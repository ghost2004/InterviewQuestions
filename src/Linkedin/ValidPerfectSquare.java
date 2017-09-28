package Linkedin;
/*
 * Leetcode 367. Valid Perfect Square
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False
 */
public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        
        
        int left = 1;
        int right = num;
        
        while (left <= right) {
            long mid = (left+right)>>1;
            
            if (mid*mid == num)
                return true;
            else if (mid*mid > num)
                right = (int)mid -1;
            else
                left = (int) mid+1;
                
        }
        
        
        return false;
    }
}
