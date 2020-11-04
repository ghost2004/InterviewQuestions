package Facebook;

/*
 * Leetcode 560. Subarray Sum Equals K
 * 
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.

 

Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2
 */
import java.util.*;
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int total = 0;
        int sum = 0;
        Map<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 1);
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            total += preSumMap.getOrDefault(sum - k, 0);
            int count = preSumMap.getOrDefault(sum, 0);
            preSumMap.put(sum, count + 1);
        }
        
        return total;
    }
}
