package Linkedin;


/*
 * LintCode 396 Coins in a Line III 
 * There are n coins in a line. Two players take turns to take a coin from one of the ends of the 
 * line until there are no more coins left. The player with the larger amount of money wins.
    Could you please decide the first player will win or lose?
    Example
    Given array A = [3,2,2], return true.
    Given array A = [1,2,4], return true.
    Given array A = [1,20,4], return false.
    Challenge
    Follow Up Question:
    If n is even. Is there any hacky algorithm that can decide whether first player will 
    win or lose in O(1) memory and O(n) time?
 */
public class CoinsInLineIII {
    int dp[][];

    int sums[];
    
    private int search(int left, int right) {
        if (dp[left][right] == -1) {
            // the total value in that section [left, right]
            int sum = sums[right] - sums[left-1];
            // opponent will left the worst case, either take left or take right
            int v = Math.min(search(left+1, right), search(left, right-1));
            dp[left][right] = sum - v;
            
        }
        
        
        return dp[left][right];
    }
    
    public boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0)
            return false;
        int n = values.length;
        // sum[i] stands for total value at position [0,i-1]
        sums = new int[n+1];
        sums[0] = 0;
        for (int i = 1; i <= n; i++)
            sums[i] = sums[i-1] + values[i-1];
        // dp[i][j] stands for the max value we can get at values[i,j]
        dp = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) 
                if (i == j)
                    // in case only one left
                    dp[i][j] = values[i-1];
                else
                    // -1 means unknown 
                    dp[i][j] = -1;
        }
       
        
        return search(1 , n) > sums[n]/2;
    }
    
    public static void main(String args[]) {
        CoinsInLineIII c = new CoinsInLineIII();
        int a1[] = { 3,2,2};
        int a2[] = {1,2,4};
        int a3[] = {1, 30, 4};
        
        System.out.println(c.firstWillWin(a1));
        System.out.println(c.firstWillWin(a2));
        System.out.println(c.firstWillWin(a3));
    }

}
