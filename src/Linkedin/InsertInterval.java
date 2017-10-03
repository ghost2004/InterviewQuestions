package Linkedin;

import java.util.*;
import Common.Interval;
public class InsertInterval {
    /*
     * Leetcode 57. Insert Interval
     * Given a set of non-overlapping intervals, insert a new interval into the 
     * intervals (merge if necessary).
        
        You may assume that the intervals were initially sorted according to 
        their start times.
        
        Example 1:
        Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
        
        Example 2:
        Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as 
        [1,2],[3,10],[12,16].
        
        This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
     */
    
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> out = new ArrayList<>();
        Iterator<Interval> iter = intervals.iterator();
        Interval next = newInterval;
        while (iter.hasNext()) {
            Interval cur = iter.next();
            if (cur.end < next.start) {
                out.add(cur);
            } else if (next.end < cur.start) {
                out.add(next);
                next = cur;
            } else {
                next.start = Math.min(next.start, cur.start);
                next.end = Math.max(next.end, cur.end);
            }
            
        }
        out.add(next);
        
        return out;
        
    }
    
    /*
     * Leetcode 56. Merge Intervals
     * Given a collection of intervals, merge all overlapping intervals.

        For example,
        Given [1,3],[2,6],[8,10],[15,18],
        return [1,6],[8,10],[15,18].
     */
    
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() < 2)
            return intervals;
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval t1, Interval t2) {
                return t1.start - t2.start;
            }
        });
        Iterator<Interval> iter = intervals.iterator();
        List<Interval> out = new ArrayList<>();
        Interval prev = iter.next();
        
        while (iter.hasNext()) {
            Interval cur = iter.next();
            if (cur.start <= prev.end) {
                prev.end = Math.max(prev.end, cur.end);
            } else {
                out.add(prev);
                prev = cur;
            }
        }
        out.add(prev);
        return out;
    }
}
