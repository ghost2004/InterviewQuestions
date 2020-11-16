package Facebook;
/*
 * Leetcode 973. K Closest Points to Origin
 * 
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

 

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
 */
public class KClosestPoints {
    class Point implements Comparable<Point>{
        int _x;
        int _y;
        int _distance;
        public Point(int x, int y) {
            _x = x;
            _y = y;
            _distance = x*x + y *y;
        }
        
        @Override
        public int compareTo(Point p) {
            return this._distance - p._distance;
        }
        
        public int[] toArray() {
            int[] result = new int[2];
            result[0] = _x;
            result[1] = _y;
            return result;
        }
    }
    
    public int[][] kClosest(int[][] points, int K) {
        int size = points.length;
        Point p[] = new Point[size];
        
        for (int i = 0; i < size; i++) {
            p[i] = new Point(points[i][0], points[i][1]);
        }
        
        int left = 0;
        int right = size - 1;
        while (left <= right) {
            int mid = partition(p, left, right);
            if (mid < K) {
                left = mid + 1;
            } else if (mid > K) {
                right = mid - 1;
            } else {
                break;
            }
        }
        
        int result[][] = new int[K][2];
        
        for (int i = 0; i < K; i++) {
            result[i] = p[i].toArray(); 
        }
        return result;
    }
    
    private int partition(Point points[], int left, int right) {
        Point pivot = points[left];
        while (left < right) {
            while (left < right && points[right].compareTo(pivot) >= 0)
                right--;
            points[left] = points[right];
            while (left < right && points[left].compareTo(pivot) <= 0)
                left++;
            points[right] = points[left];
            
        }
        points[left] = pivot;
        return left;
    }
    
    
    public static void printArray(int p[][]) {
        for (int i = 0; i < p.length; i ++) {
            System.out.print( "[" + p[i][0] + ","+ p[i][1] +"]" );
        }
        System.out.println();
    }
    public static void main(String args[]) {
        
        KClosestPoints k = new KClosestPoints();
        int[][] points = {{0,1},{1,1}};

        printArray(k.kClosest(points, 2));
        
    }
}
