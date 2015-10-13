package Uber;
/*
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
 */
import java.util.*;
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> out = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0) 
            return out;
        int m = matrix.length;
        int n = matrix[0].length;
        
        int x = 0;
        int y = 0;
        
        while (m > 0 && n > 0) {
            if (m == 1) {
                for (int i = 0; i < n; i++) 
                    out.add(matrix[x][y++]);
                break;
            } else if (n == 1) {
                for (int i = 0; i < m; i++) 
                    out.add(matrix[x++][y]);
                break;
            }
            
            for (int i = 1; i < n; i++)
                out.add(matrix[x][y++]);
            for (int i = 1; i < m;i++)
                out.add(matrix[x++][y]);
            for (int i = 1; i < n; i++)
                out.add(matrix[x][y--]);
            for (int i = 1; i < m; i++)
                out.add(matrix[x--][y]);
            
            x++;
            y++;
            m-=2;
            n-=2;
        }
        
        return out;
    }
    public void printArray(int[][] matrix) {
        List<Integer> out = spiralOrder(matrix);
        Iterator<Integer> iter = out.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next()+"->");
        }
        System.out.println("end");
    }
    public static void main(String args[]) {
        SpiralMatrix s = new SpiralMatrix();
        int test1[][] = { {1,2},{3,4}};
        s.printArray(test1);
        int test2[][] = {{1,2,3,4,5,6,7,8,9,10},{11,12,13,14,15,16,17,18,19,20}};
        s.printArray(test2);
    }
}
