package Linkedin;
/*
 * Leetcode 260. Single Number III
 * Given an array of numbers nums, in which exactly two elements appear only once 
 * and all the other elements appear exactly twice. Find the two elements that 
 * appear only once.

    For example:
    
    Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
    
    Note:
    The order of the result is not important. So in the above example, 
    [5, 3] is also correct.
    Your algorithm should run in linear runtime complexity. Could you 
    implement it using only constant space complexity?
 */
public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        int result[] = new int[2];
        
        int xor_all = 0;
        
        for (int i:nums)
            xor_all ^= i;
        // get the last bit of xor result, 
        int mask = xor_all & (-xor_all);
        
        // use the mask to split array into half
        for (int i:nums)
            if ((i & mask) == 0)
                result[0] ^= i;
            else
                result[1] ^= i;
        
        
        return result;
    }

}
