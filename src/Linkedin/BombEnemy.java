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
import java.util.*;
import Common.Point;
public class BombEnemy {
    public  int maxKilledEnemies(char[][] grid) {
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
      There is no walls this time. 0 means empty, and 1 means an enemy
      The bomb kills all the enemies in the same row and column from the planted 
      point
      Given 2D grid, returns minimum bombs that can kill all enemies
     * 
     */
    private void addMap(HashMap<Integer, List<Point>> map, int key, Point p){
        List<Point> list = map.get(key);
        if (list == null ) {
            list = new LinkedList<>();
            map.put(key, list);
        }
        list.add(p);
    }
    private int[][] getSortedMap(HashMap<Integer, List<Point>> map) {
        int array[][] = new int[map.size()][2];
        int i = 0;
        for (Map.Entry<Integer, List<Point>> entry:map.entrySet()) {
            array[i][0] = entry.getKey();
            array[i][1] = entry.getValue().size();
            i++;
        }
        
        Arrays.sort(array, (a,b) -> b[1] - a[1]);
        return array;
    }
    public int minBombs(int grid[][]) {
        if (grid == null || grid.length < 1)
            return 0;
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        HashSet<Point> points = new HashSet<>();
        HashMap<Integer, List<Point>> xMap = new HashMap<>();
        HashMap<Integer, List<Point>> yMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    Point p = new Point(i, j);
                    points.add(p);
                    addMap(xMap, i, p);
                    addMap(yMap, j, p);                    
                }
            }
            
        }
        
        int xArr[][] = getSortedMap(xMap);
        int yArr[][] = getSortedMap(yMap);
        
        int idx = 0;
        
        while (!points.isEmpty()) {
            int x = xArr[idx][0];
            int y = yArr[idx][0];
            List<Point> list = xMap.get(x);
            for (Point p:list)
                points.remove(p);
            list = yMap.get(y);
            for (Point p:list)
                points.remove(p);
            idx++;
            count++;
        }
        
        return count;
    }
    public static void main(String args[]) {
        BombEnemy b = new BombEnemy();
        char test1[][] = {
                {'0', 'X', '0', '0'}, 
                {'X', '0', 'Y', 'X'}, 
                {'0', 'X', '0', '0'}
        };
        System.out.println(b.maxKilledEnemies(test1));
        
        int arr1[][] = {
                { 0, 1, 0, 0, 1},
                { 1, 0, 1, 0, 0},
                { 0, 1, 0, 0, 0}
        };
        
        System.out.println(b.minBombs(arr1));

    }

}
