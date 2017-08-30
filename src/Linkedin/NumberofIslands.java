package Linkedin;
/*
 * Leetcode 200. Number of Islands
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
 * An island is surrounded by water and is formed by connecting adjacent lands 
 * horizontally or vertically. You may assume all four edges of the grid are all 
 * surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3


 */
public class NumberofIslands {
    private void dfs(char[][] grid, int m, int n) {
        if ( m < 0 || n < 0 || m >= grid.length || n >= grid[0].length || grid[m][n] == '0')
            return;
        grid[m][n] = '0';
        dfs(grid, m-1, n);
        dfs(grid, m, n+1);
        dfs(grid, m, n-1);
        dfs(grid, m+1, n);
    }
    public int numIslands(char[][] grid) {
        int cnt = 0;
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    cnt++;
                    dfs(grid, i, j);
                }
            }
        }
        
        return cnt;
    }
}
