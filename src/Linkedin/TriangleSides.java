package Linkedin;
/*
 * Leetcode 611 Valid Triangle Number
 * Given an array consists of non-negative integers, your task is to count the number of triplets 
 * chosen from the array that can make triangles if we take them as side lengths of a triangle.

Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].

 */

import java.util.*;
public class TriangleSides {
    
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3)
            return 0;
        Arrays.sort(nums);
        
        int count = 0;
        
        for (int i = 2; i < nums.length; i++) {
            int left = 0;
            int right = i - 1;
            while  (left < right) {
                if (nums[left] + nums[right] <= nums[i])
                    left++;
                else {
                    count += right - left;
                    right--;
                }
            }
        }
        
        return count;
        
    }

}
