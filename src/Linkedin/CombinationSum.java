package Linkedin;

import java.util.*;


public class CombinationSum {
    /*
     * Leetcode 39. Combination Sum
     * Given a set of candidate numbers (C) (without duplicates) and a target number (T), 
     * find all unique combinations in C where the candidate numbers sums to T.

        The same repeated number may be chosen from C unlimited number of times.
        
        Note:
        All numbers (including target) will be positive integers.
        The solution set must not contain duplicate combinations.
        For example, given candidate set [2, 3, 6, 7] and target 7, 
        A solution set is: 
        [
          [7],
          [2, 2, 3]
        ]
     */
    private void bt_combSum(List<List<Integer>>  result, List<Integer> buf, int nums[], int key, int idx) {
        if (key == 0) {
            result.add(new ArrayList<>(buf));
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            if (nums[i] > key)
                break;
            buf.add(nums[i]);
            bt_combSum(result, buf, nums, key-nums[i], i);
            buf.remove(buf.size()-1);
            
        }
        
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>>  result = new ArrayList<>();
        List<Integer> buf = new ArrayList<>();
        
        Arrays.sort(candidates);
        bt_combSum(result, buf, candidates, target, 0);
        return result;
    }
    
    /*
     * Leetcode 40. Combination Sum II
     * Given a collection of candidate numbers (C) and a target number (T), find all 
     * unique combinations in C where the candidate numbers sums to T.

        Each number in C may only be used once in the combination.
        
        Note:
        All numbers (including target) will be positive integers.
        The solution set must not contain duplicate combinations.
        For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
        A solution set is: 
        [
          [1, 7],
          [1, 2, 5],
          [2, 6],
          [1, 1, 6]
        ]
     */
    private void bt_combSum2(List<List<Integer>> result, List<Integer> buf, int nums[], int key, int idx) {
        if (key == 0) {
            result.add(new ArrayList<>(buf));
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            if (nums[i] > key)
                break;
            if (i > idx && nums[i] == nums[i-1])
                continue;
            buf.add(nums[i]);
            bt_combSum2(result, buf, nums, key-nums[i], i+1);
            buf.remove(buf.size()-1);
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>>  result = new ArrayList<>();
        List<Integer> buf = new ArrayList<>();
        
        Arrays.sort(candidates);
        bt_combSum2(result, buf, candidates, target, 0);
        return result;
    }
    /*
     * Find all possible combinations of k numbers that add up to a number n, 
     * given that only numbers from 1 to 9 can be used and each combination 
     * should be a unique set of numbers.

        
        Example 1:
        
        Input: k = 3, n = 7
        
        Output:
        
        [[1,2,4]]
        
        Example 2:
        
        Input: k = 3, n = 9
        
        Output:
        
        [[1,2,6], [1,3,5], [2,3,4]]
     */
    
    private void bt_combSum3(List<List<Integer>> res, List<Integer> buf, int k, int n, int start) {
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(buf));
            return;
        }
        
        for (int i = start; i < 10; i++) {
            if (i > n)
                break;
            buf.add(i);
            bt_combSum3(res, buf, k-1, n-i, i+1);
            buf.remove(buf.size()-1);
        }
            
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> buf = new ArrayList<>();
        
        bt_combSum3(res, buf, k, n, 1);
        
        return res;
    }
    
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
