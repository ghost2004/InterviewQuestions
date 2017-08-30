package Linkedin;
/*
 * LintCode 612 K Closest Points
 * Given an array containing N points find the K closest points to the origin in the 2D plane. 
 * You can assume K is much smaller than N and N is very large.
 */

import Common.Point;
import java.util.*;
public class KClosestPoints {
    public Point gOrigin = null;
    // Heap solution 
    public Point[] kClosest(Point[] points, Point origin, int k) {
        Point[] result = new Point[k];
        
        gOrigin = origin;
        
        PriorityQueue<Point> pq = new PriorityQueue<>(k, new Comparator<Point> (){
            public int compare(Point a, Point b) {
                int diff1 = (a.x - gOrigin.x) * (a.x - gOrigin.x) + (a.y - gOrigin.y) * (a.y - gOrigin.y);
                int diff2 = (b.x - gOrigin.x) * (b.x - gOrigin.x) + (b.y - gOrigin.y) * (b.y - gOrigin.y);
                
                if (diff1 == diff2) {
                    return b.x == a.x ? b.y-a.y: b.x-a.x;
                }
                return diff2 - diff1;
            }
        });
        
        for (Point p:points) {
            
            pq.offer(p);
            if (pq.size() > k)
                pq.poll();
        }
       
        for (int i = k-1; i >= 0; i--)
            result[i] = pq.poll();
        return result;
    }
    
    public class Distance implements Comparable<Distance> {
        public int index;
        public int distance;
        
        public Point point;
        
        public void getDis(Point o) {
            this.distance = (this.point.x - o.x) * (this.point.x - o.x)  + (this.point.y - o.y) * (this.point.y - o.y);
        }
        
        public int compareTo(Distance other) {
            
            if (this.distance == other.distance) {
                return this.point.x == other.point.x ? other.point.y - this.point.y : other.point.x  - this.point.x; 
            }
            
            return this.distance - other.distance;
        }
    }
    
    private void quickSelect(Distance dis[], int k) {
        int start = 0;
        int end = dis.length - 1;
        
        while (start < end) {
            
        }
    }
    
    public Point[] kClosest2(Point[] points, Point origin, int k) {
        Point[] result = new Point[k];
        
        Distance dis[] = new Distance[points.length];
        
        for (int i = 0; i < points.length; i++) {
            dis[i] = new Distance();
            dis[i].index = i;
            dis[i].point = points[i];
            dis[i].getDis(origin);
        }
        
        quickSelect(dis, k);
        
        
        return result;
    }
    
    
    public static void main(String args[]) {
        int a1[][] = {{4,6},{4,7},{4,4},{2,5},{1,1}};
        Point arr[] = Point.getFromArray(a1);
        Point o = new Point(0,0);
        KClosestPoints pk = new KClosestPoints();
        
        Point rp[] = pk.kClosest(arr, o, 3);
        
        for (Point p:rp) {
            System.out.print(p.toString()+ " ");
        }
        
    }
}