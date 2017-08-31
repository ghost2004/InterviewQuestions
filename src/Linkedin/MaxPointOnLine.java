package Linkedin;
/*
 * Leetcode 149. Max Points on a Line
 * Given n points on a 2D plane, find the maximum number of points that lie 
 * on the same straight line.
 */
import Common.Point;
import java.util.*;
public class MaxPointOnLine {
    public int maxPoints_v1(Point[] points) {
        if (points == null || points.length < 1)
            return 0;
        int count = 1;
        
        for (int i = 0; i < points.length; i++) {
            HashMap<Double, Integer> map = new HashMap<>();
            int curMax = 0;
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
    
    private int getGCD(int x, int y) {
        int temp;
        if (x < y) {
            temp = x;
            x = y;
            y = temp;
        }
        
        while (x % y != 0) {
            temp = y;
            y = x % y;
            x = temp;
        }
        
        return y;
    }
    
    private String getKey(Point p1, Point p2) {
        int x = p1.x - p2.x;
        if (x == 0)
            return "0-0";
        int y = p1.y - p2.y;
        if (y == 0)
            return "0-0-0";
        
        int g = getGCD(x,y);
        Integer diff1 = x/g;
        Integer diff2 = y/g;
        
        return diff2.toString()+"-"+diff1.toString();
    }
    
    public int maxPoints(Point[] points) {

        if (points == null || points.length < 1)
            return 0;
        int count = 1;
        
        for (int i = 0; i < points.length; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            int curMax = 0;
            int dup = 0;
            for (int j = 0; j < points.length; j++) {
                if (i == j)
                    continue;
                if (points[i].x == points[j].x && points[i].y == points[j].y)
                    dup++;
                else {
                    String key = getKey(points[i], points[j]);
                    Integer cnt = map.get(key);
                    if (cnt == null)
                        cnt = 1;
                    else 
                        cnt++;
                    map.put(key, cnt);
                    curMax = Math.max(curMax, cnt);
                }
                
            }
            
            count = Math.max(curMax+dup+1, count);
        }
        
        return count;
    }
}
