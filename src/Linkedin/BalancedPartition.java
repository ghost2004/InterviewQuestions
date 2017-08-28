package Linkedin;
/*
 * Given a set of integers, the task is to divide it into two sets S1 and S2 such that the absolute difference 
 * between their sums is minimum.

If there is a set S with n elements, then if we assume Subset1 has m elements, Subset2 must have n-m elements 
and the value of abs(sum(Subset1) – sum(Subset2)) should be minimum.

Example:

Input:  arr[] = {1, 6, 11, 5} 
Output: 1
Explanation:
Subset1 = {1, 5, 6}, sum of Subset1 = 12 
Subset2 = {11}, sum of Subset2 = 11 
 */
public class BalancedPartition {
    // recursive solution
    private int getMinDiff(int arr[], int idx, int curSum, int total) {
        if (idx < 0) 
            return Math.abs((total - curSum) - curSum);
        
        return Math.min(getMinDiff(arr, idx-1, curSum+arr[idx], total), 
                getMinDiff(arr, idx-1, curSum, total));
    }
    public int findMinV1(int arr[]) {
        int sum  = 0;
        for (int n:arr) {
            sum += n;
        }
        
        return getMinDiff(arr, arr.length-1, 0, sum);
    }
    
    // DP solution
    
    public int findMin(int arr[]) {
        int total = 0;
        for (int i:arr)
            total += i;
        int target = total/2;
        int len = arr.length;
        
        boolean dp[][] = new boolean[len+1][target+1];

        for (int i = 0; i <= len; i++)
            dp[i][0] = true;
        for (int i = 1; i <= target; i++)
            dp[0][i] = false;
        
        for (int idx = 1; idx <= len; idx++) {
            for (int sum = 1; sum <= target; sum++) {
                dp[idx][sum] = dp[idx-1][sum];
                if (arr[idx-1] <= sum)
                    dp[idx][sum] |= dp[idx][sum - arr[idx-1]];
            }
        }
        int diff = total;
        for (int i = target; i >= 1; i--) {
            if (dp[len][i]) {
                return total - 2*i;
            }
        }
        return diff;
    }
    

}
