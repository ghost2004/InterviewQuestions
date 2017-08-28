package Linkedin;

import java.util.Arrays;

/*
 * Leetcode 377. Combination Sum IV
 * 
 * Given an integer array with all positive numbers and no duplicates, find the number of possible 
 * combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?
 */
public class CombinationSum {
    // recursive solution
    public int combinationSum4(int[] nums, int target) {
        if (target == 0)
            return 1;
        
        int res = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= target) 
                res += combinationSum4(nums, target - nums[i]);
        }
        
        return res;
    }
    
    // DP solution
    public int combinationSum4DP(int[] nums, int target) {
        // dp[i] stands for all possible combination for sum i
        int dp[] = new int[target+1];
        Arrays.fill(dp, 0);
        // set dp[0] for 1, means if target equals current element, there is 1 combination
        dp[0] = 1;
        // scan sum from 1 to target
        for (int sum = 1; sum <= target; sum++) {
            // in each sum, scan all elements in the array
            for (int idx = 0; idx < nums.length; idx++) {
                if (nums[idx] <= sum) 
                    // current element could be selected for solution
                    // the possible solution of current sum should add sum-nums[i]
                    dp[sum] += dp[sum-nums[idx]];
            }
        }
        
        
        return dp[target];
    }
}
