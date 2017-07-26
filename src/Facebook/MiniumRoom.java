package Facebook;



/*
 * We have a list of scheduled meetings with begin and end time
 * return the minimal numbers of conference room which can hold
 * those meetings
 * 
 */
import Common.Interval;
import java.util.*;
public class MiniumRoom {
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals==null||intervals.length==0)
            return 0;
        // sort the meetings by start time
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        
        int count = 1;
        // minimum heap for the end time of each meeting
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        queue.offer(intervals[0].end);
        
        // scan from the second meeting
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < queue.peek()) {
                // the end of first meeting is greater than next start, new room needed
                count ++;
            } else {
                // remove the first available room from PQ
                queue.poll();
            }
            // put the end point of current meeting into PQ
            queue.offer(intervals[i].end);
        }
        
        return count;
    }

}
