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
        // get the total value of coins
        for (int i:values)
            sum += i;
        // dp[i] stands for max value we can get at position i
        int dp[] = new int[n];
        
        // let's scan from right to left
        // last value
        dp[n-1] = values[n-1];
        // last 2 values
        dp[n-2] = values[n-1] + values[n-2];
        // in last 3 values
        dp[n-3] = values[n-2] + values[n-3];
        
        // handle the case only 3 coins
        if (n == 3)
            return dp[0] > (sum/2);
        // 4 coin , a,b,c,d
        // Max value we can get is a+d or b+c     
        dp[n-4] = Math.max(values[n-4]+values[n-1], values[n-2]+values[n-3]);
        
        // scan from right to left
        for (int idx = n-5; idx >= 0; idx--){
            // in last 5 coins like a ,b, c, d, e
            // option 1 , take 2 coins (a+b) , opponent have to take c, and left you minimum of d and e
            dp[idx] = values[idx] + values[idx+1] + Math.min(dp[idx+3], dp[idx+4]);
            // option 2, take 1 coin (a), opponent have to take b, and left you minimum of c and d
            dp[idx] = Math.max(dp[idx],values[idx] + Math.min(dp[idx+2], dp[idx+3]));
        }
        
        return dp[0] > (sum/2);
    }

}
