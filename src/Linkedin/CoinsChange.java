package Linkedin;

import java.util.Arrays;

/*
 * Leetcode 322. Coin Change
 * You are given coins of different denominations and a total amount of money amount. 
 * Write a function to compute the fewest number of coins that you need to make up 
 * that amount. If that amount of money cannot be made up by any combination of the 
 * coins, return -1.

    Example 1:
    coins = [1, 2, 5], amount = 11
    return 3 (11 = 5 + 5 + 1)
    
    Example 2:
    coins = [2], amount = 3
    return -1.
    
    Note:
    You may assume that you have an infinite number of each kind of coin.
 */
public class CoinsChange {

    public int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        int dp[] = new int[amount+1];
        dp[0] = 0;
        Arrays.sort(coins);
        for (int sum = 1; sum <= amount; sum++) {
            int min = -1;
            for (int coin:coins) {
                if (coin > sum)
                    break;
                if (dp[sum-coin] != -1){
                    if (min < 0)
                        min = dp[sum-coin]+1;
                    else 
                        min = Math.min(min, dp[sum-coin]+1);
                }
            }
            dp[sum] = min;
        }
        
        return dp[amount];
    }
}
