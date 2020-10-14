package DoorDash;

/*
 * LeetCode 1197 Minimum Knight Moves

In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction,
 then one square in an orthogonal direction.



Return the minimum number of steps needed to move the knight to the square [x, y]. It is guaranteed the answer exists.

Example 1:

Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] -> [2, 1]
Example 2:

Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] -> [2, 1] -> [4, 2] -> [3, 4] -> [5, 5]
Constraints:

|x| + |y| <= 300
 */
import java.util.*;
public class MinimumKnightMoves {
    static int [][] directions = {{-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {-2, -1}, {-2, 1}, {2, -1}, {2, 1}};
    static class Point {
        int _x;
        int _y;
        public Point(int x, int y) {
            _x = x;
            _y = y;
        }
        public String toString() {
            return String.format("%d-%d", _x, _y);
        }
    }
    static int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        Point o = new Point(0,0);
        Set<String> visited = new HashSet<>();
        LinkedList<Point> queue = new LinkedList<>();
        queue.offer(o);
        visited.add(o.toString());
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                Point t = queue.poll();
                if (t._x == x && t._y == y) {
                    return step;
                }
                
                for (int[] dir : directions) {
                    Point next = new Point(t._x + dir[0], t._y + dir[1]);
                    if (!visited.contains(next.toString())) {
                        queue.offer(next);
                        visited.add(next.toString());
                    }
                }
            }
            step++;
        }
        return -1;
    }
    
    public static void main(String args[]) {
        System.out.println(minKnightMoves(0,0));
        System.out.println(minKnightMoves(2,1));
        System.out.println(minKnightMoves(5,5));
        
    }
}
