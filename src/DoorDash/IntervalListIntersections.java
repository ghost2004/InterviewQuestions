package DoorDash;

/*
 * Leetcode 986. Interval List Intersections
 * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  
The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval. 

 For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
 */
import java.util.*;
public class IntervalListIntersections {
    
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A == null || B == null || A.length == 0 || B.length == 0)
            return new int[][]{};
        List<int[]> result = new ArrayList<>();
        int idxA = 0;
        int idxB = 0;
        while (idxA < A.length && idxB < B.length) {
            int maxStart = Math.max(A[idxA][0], B[idxB][0]);
            int minEnd = Math.min(A[idxA][1], B[idxB][1]);
            
            if (minEnd >= maxStart) {
                result.add(new int[]{maxStart, minEnd});
            }
            
            if (A[idxA][1] == minEnd) 
                idxA++;
            if (B[idxB][1] == minEnd)
                idxB++;
            
        }
        
        return result.toArray(new int[result.size()][2]);
    }

}
