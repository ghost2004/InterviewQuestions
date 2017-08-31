package Linkedin;
/*
 * Leetcode 149. Max Points on a Line
 * Given n points on a 2D plane, find the maximum number of points that lie 
 * on the same straight line.
 */
import Common.Point;
import java.util.*;
public class MaxPointOnLine {
    public int maxPoints(Point[] points) {
        if (points == null || points.length < 1)
            return 0;
        int count = 1;
        
        for (int i = 0; i < points.length; i++) {
            HashMap<Double, Integer> map = new HashMap<>();
            int curMax = 1;
            int dup = 0;
            int vert = 0;
            for (int j = 0; j < points.length; j++) {
                if (i == j)
                    continue;
                if (points[i].x == points[j].x) {
                    if (points[i].y != points[j].y) {
                        vert++;
                        curMax = Math.max(curMax, vert);
                    }
                    else 
                        dup++;
                } else {
                    Double ang = (points[i].y - points[j].y)*1.0/(points[i].x - points[j].x);
                    Integer cnt = map.get(ang);
                    if (cnt == null) {
                        cnt = 1;
                    } else {
                        cnt++;
                    }
                    map.put(ang, cnt);
                    curMax = Math.max(curMax, cnt);
                }   
            }
            
            count = Math.max(count, dup+curMax+1);
            
        }
        
        return count;
        
    }
}
