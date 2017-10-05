package Linkedin;
/*
 * LintCode 395 Coins in a Line II
 * 
 * There are n coins with different value in a line. Two players take turns to take one or two 
 * coins from left side until there are no more coins left. The player who take the coins with 
 * the most value wins.
    Could you please decide the first player will win or lose?
    Example
    Given values array A = [1,2,2], return true.
    Given A = [1,2,4], return false.
 */
public class CoinsInLineII {
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0)
            return false;
        if (values.length < 3)
            return true;
        int n = values.length;
        int sum = 0;
        for (int i:values)
            sum += i;
        int dp[] = new int[n];
        
        dp[n-1] = values[n-1];
        dp[n-2] = values[n-1] + values[n-2];
        dp[n-3] = values[n-2] + values[n-3];
        
        if (n == 3)
            return dp[0] > (sum/2);
        dp[n-4] = Math.max(values[n-4]+values[n-1], values[n-2]+values[n-3]);
        
        for (int idx = n-5; idx >= 0; idx--){
            dp[idx] = values[idx] + values[idx+1] + Math.min(dp[idx+3], dp[idx+4]);
            dp[idx] = Math.max(dp[idx],values[idx] + Math.min(dp[idx+2], dp[idx+3]));
        }
        
        return dp[0] > (sum/2);
    }

}
