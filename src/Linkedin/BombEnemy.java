package Linkedin;
/*
 * Leetcode 361 Bomb Enemy
 * Given a 2D grid, each cell is either a wall 'Y', an enemy 'X' or empty '0' 
 * (the number zero), return the maximum enemies you can kill using one bomb.
 * 
    The bomb kills all the enemies in the same row and column from the planted 
    point until it hits the wall since the wall is too strong to be destroyed.
    Note that you can only put the bomb at an empty cell.

    Example:
    For the given grid
    
    0 X 0 0
    X 0 Y X
    0 X 0 0
    
    return 3. (Placing a bomb at (1,1) kills 3 enemies)
 */
public class BombEnemy {
    public static int maxKilledEnemies(char[][] grid) {
        if (grid == null)
            return 0;
        int m = grid.length;
        int n = grid.length > 0 ? grid[0].length : 0;
        
        int res = 0;
        // row stands for enemies we can kill in cur row
        int row = 0;
        // col[i] stands for enemies we can kill in col i
        int col[] = new int[n];
        
        // scan from up to down
        for (int i = 0; i < m; i++) {
            // scan from left to right
            for (int j = 0; j < n; j++) {
                // first let's do with row data
                // reset row to 0 in this 2 conditions
                // 1. start of row
                // 2. a wall in the left grid
                if (j == 0 || grid[i][j-1] == 'Y') {
                    row = 0;
                    // get the enemies we can kill in this row , 
                    // until we hit wall or reach the end of the row 
                    for (int k = j; k < n && grid[i][k] != 'Y'; k++) {
                        if (grid[i][k] == 'X')
                            row++;
                    }
                }
                // second let's do with the column data
                // reset col[i] to 0 in this 2 conditions
                // 1. start of column
                // 2. a wall in up grid
                if (i == 0 || grid[i-1][j] == 'Y') {
                    col[j] = 0;
                    // get the enemies we can kill in this column,
                    // until we hit wall or reach the end of the column
                    for (int k = i; k < m&& grid[k][j] != 'Y'; k++) {
                        if (grid[k][j] == 'X')
                            col[j]++;
                    }
                }
                // if we can place bomb in this grid, 
                // calculate the numbers ( row + column )  
                if (grid[i][j] == '0')
                    res = Math.max(res, row+col[j]);
            }
        }
        
        
        return res;
    }
    /*
     * Follow up 1:
      There is no walls this time.
      The bomb kills all the enemies in the same row and column from the planted 
      point
      Given 2D grid, returns minimum bombs that can kill all enemies
     * 
     */
    public static int minBombs(int grid[][]) {
        
        
        return 0;
    }
    public static void main(String args[]) {
        char test1[][] = {
                {'0', 'X', '0', '0'}, 
                {'X', '0', 'Y', 'X'}, 
                {'0', 'X', '0', '0'}
        };
        System.out.println(maxKilledEnemies(test1));

    }

}
