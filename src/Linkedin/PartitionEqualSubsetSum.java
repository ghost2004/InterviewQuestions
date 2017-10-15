package Linkedin;

import java.util.Arrays;

/*
 * Leetcode 416. Partition Equal Subset Sum
 * Given a non-empty array containing only positive integers, find if the array can be partitioned 
 * into two subsets such that the sum of elements in both subsets is equal.

    Note:
    Each of the array element will not exceed 100.
    The array size will not exceed 200.
    Example 1:
    
    Input: [1, 5, 11, 5]
    
    Output: true
    
    Explanation: The array can be partitioned as [1, 5, 5] and [11].
    Example 2:
    
    Input: [1, 2, 3, 5]
    
    Output: false
    
    Explanation: The array cannot be partitioned into equal sum subsets.
    
    
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2)
            return false;
        int total = 0;
        for (int i:nums)
            total += i;
        if (total % 2 != 0)
            return false;
        
        int target = total/2;
        boolean dp[] = new boolean[target+1];
        dp[0] = true;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target)
                return false;
            if (dp[target- nums[i]])
                return true;
            for (int j = target-1; j >= nums[i]; j--)
                dp[j] |= dp[j - nums[i]];
        }
        
        return false;
    }
    
    public boolean canPartition2(int[] nums) {
        if (nums == null || nums.length < 2)
            return false;
        int total = 0;
        for (int i:nums)
            total += i;
        if (total % 2 == 1) 
            return false;
        int target = total/2;
        int len = nums.length;
        boolean dp[][] = new boolean[len+1][target+1];
        
        for (int i = 0; i <= len; i++)
            dp[i][0] = true;
        
        for (int i = 1; i <= len; i++) {
            if (nums[i-1] > target)
                return false;
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i-1][j];
                if (j >= nums[i-1])
                    dp[i][j] |= dp[i-1][j-nums[i-1]];
            }
            
            
        }
        
        return dp[len][target];
    }
    /*
     *  follow up
     *  given a list of numbers, see if you can separate them into k groups 
     *  such that each group has the same sum
     *  
     *  Ideas from http://www.geeksforgeeks.org/partition-set-k-subsets-equal-sum/
     */
    
    // used[i] means item at nums[i] is used by other subset
    private boolean used[];
    // subSetSum[i] means sum of subet i
    private int subSetSum[];
    
    private boolean dfs(int nums[], int target, int k, int curSubSet, int idx ) {
        if (subSetSum[curSubSet] == target) {
            if (curSubSet == k - 2)
                return true;
            return dfs(nums, target, k, curSubSet+1, nums.length-1);
        }
       
        for (int i = idx; i >= 0; i--) {
            
            if (!used[i] && subSetSum[curSubSet] <= target - nums[i]) {
                used[i] = true;
                subSetSum[curSubSet] += nums[i];
                boolean result = dfs(nums, target, k, curSubSet, i-1);
                used[i] = false;
                subSetSum[curSubSet] -= nums[i];
                if (result)
                    return true;
            }
        }
        
        return false;
    }
    public boolean canPartitionK(int nums[], int k) {
        if (nums == null || nums.length < k)
            return false;
        if (k == 1)
            return true;
        
        int len = nums.length;
        int sum = 0;
        int maxItem = 0;

        for (int i: nums) {
            sum += i;
            maxItem = Math.max(maxItem, i);
        }
        


        
        if (sum % k != 0 || maxItem > sum/k)
            return false;
        
        int target = sum / k;
        
        used = new boolean[len];
        subSetSum = new int[k];
        
        used[len-1] = true;
        subSetSum[0] = nums[len-1];
        
        return dfs(nums, target, k, 0, len-1);

    }
    
    public static void main(String args[]) {
        PartitionEqualSubsetSum p = new PartitionEqualSubsetSum();
        int a1[] = {1,2,5};
        
        //System.out.println(p.canPartition(a1));
        
        int a2[] = {2, 1, 4, 5, 3, 3};
        int a3[] = {2, 1, 4, 5, 6};
        int a4[] = {2, 3, 5, 5, 6};
        int a5[] = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int a6[] = {4,3,2,3,5,2,1};
                
                
        /*
        System.out.println(p.canPartitionK(a2, 3));
        System.out.println(p.canPartitionK(a3, 3));
        System.out.println(p.canPartitionK(a4, 3));
        System.out.println(p.canPartitionK(a5, 1));
        */
        System.out.println(p.canPartitionK(a6, 4));
        
    }
}
