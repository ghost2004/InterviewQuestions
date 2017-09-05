package Linkedin;
/*
 * Leetcode 265 Paint House II
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a 
 * certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is 
the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... 

Find the minimum cost to paint all houses.
 * 
 */
public class PaintHouseII {
    
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length < 1)
            return 0;
        int n = costs.length;
        int dp[][] = costs.clone();
        int preColor = -1;
        int preMin = 0;
        int preSecMin = 0;
        
        for (int i = 0; i < n; i++) {
            int curMin = Integer.MAX_VALUE;
            int curSecMin = Integer.MAX_VALUE;
            int curColor = -1;
            for (int j = 0; j < dp[i].length; j++) {
                if (preColor == j) {
                    dp[i][j] += preSecMin;
                } else {
                    dp[i][j] += preMin;
                }
                
                if (dp[i][j] < curMin) {
                    curSecMin = curMin;
                    curMin = dp[i][j];
                    curColor = j;
                } else if (dp[i][j] < curSecMin) {
                    curSecMin = dp[i][j];
                }
            }
            
            preMin = curMin;
            preSecMin = curSecMin;
            preColor = curColor;
            
        }
        
        return preMin;
    }

}
