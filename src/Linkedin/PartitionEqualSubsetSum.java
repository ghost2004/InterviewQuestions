package Linkedin;
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
     */
    
    public static void main(String args[]) {
        PartitionEqualSubsetSum p = new PartitionEqualSubsetSum();
        int a1[] = {1,2,5};
        
        System.out.println(p.canPartition(a1));
    }
}
