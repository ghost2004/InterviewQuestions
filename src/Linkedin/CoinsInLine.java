package Linkedin;
/*
 * LintCode 394 Coins in a Line
 * There are n coins in a line. Two players take turns to take one or two coins from right side until 
 * there are no more coins left. The player who take the last coin wins.
        Could you please decide the first play will win or lose?
        Example
        n = 1, return true.
        n = 2, return true.
        n = 3, return false.
        n = 4, return true.
        n = 5, return true.
        Challenge
        O(n) time and O(1) memory
 */
public class CoinsInLine {
    // Time O(n) Space O(n)
    public boolean firstWillWin(int n)  {
        switch (n) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                return true;
        }
        
        boolean dp[] = new boolean[n+1];
        
        dp[0] = false;
        dp[1] = true;
        dp[2] = true;
        
        for (int i = 3; i <= n; i++)
            dp[i] = !dp[i-2] || !dp[i-1];
        
        return dp[n];
        
    }
    // Time O(n) Space O(1)
    public boolean firstWillWin_On(int n)  {
        switch (n) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                return true;
        }
        
        boolean dp[] = new boolean[3];

        dp[0] = false;
        dp[1] = true;
        dp[2] = true;
        
        for (int i = 3; i <= n; i++) {
            dp[0] = dp[1];
            dp[1] = dp[2];
            dp[2] = !dp[0] || !dp[1];
            
        }
        
        return dp[2];
        
    }
    
    
    // O(1) solution
    public boolean firstWillWin_O1(int n)  {
        if (n % 3 != 0)
            return true;
        return false;
        
    }
}
