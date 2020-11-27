package Facebook;
/*
 * Leetcode 1428. Leftmost Column with at Least a One  
 * A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, this row is sorted in non-decreasing order.
Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it. If such index doesn't exist, return -1.
You can¡¯t access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:
    BinaryMatrix.get(x, y) returns the element of the matrix at index (x, y) (0-indexed).   
    BinaryMatrix.dimensions() returns a list of 2 elements [m, n], which means the matrix is m * n.

 */

import java.util.*;
public class LeftmostColumn {
    class BinaryMatrix {
        int[][] matrix;
        List<Integer> dimension;
        
        public BinaryMatrix(int[][] m) {
            matrix = m;
            dimension = new ArrayList<>();
            
            dimension.add(matrix.length);
            dimension.add(matrix[0].length);
           
        }
        
        public int get(int x, int y) {
            return matrix[x][y];
        }
        
        public List<Integer> dimensions() {
            return dimension;
        }
         
        
    }
    
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> d = binaryMatrix.dimensions();
        int m = d.get(0);
        int n = d.get(1);
        
        int leftMost = -1;
        int x = 0;
        int y = n - 1;
        
        while (x < m && y >= 0) {
            int value = binaryMatrix.get(x, y);
            if (value == 1) {
                leftMost = y;
                y--;
            } else {
                x++;
            }
        }
        
        return leftMost;
    }

}
