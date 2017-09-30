package Google;
/*
 * Leetcode 681. Next Closest Time
 * Given a time represented in the format "HH:MM", form the next closest 
 * time by reusing the current digits. There is no limit on how many times 
 * a digit can be reused.

    You may assume the given input string is always valid. For example, 
    "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
    
    Example 1:
    
    Input: "19:34"
    Output: "19:39"
    Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, 
    which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours 
    and 59 minutes later.
    
    Example 2:
    
    Input: "23:59"
    Output: "22:22"
    Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. 
    It may be assumed that the returned time is next day's time since it is smaller 
    than the input time numerically.
 */
import java.util.*;
public class NextClosestTime {
    // O(1) solution, just try all time in next 24 hours
    public String nextClosestTime2(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int min =  Integer.parseInt(time.substring(3));
        int cur = 60 * hour + min;
        Set<Integer> set = new HashSet<>();
        set.add(hour/10);
        set.add(hour%10);
        set.add(min/10);
        set.add(min%10);
        
        for (int i = 1; i < 24*60; i++) {
            int t = (cur + i) % (24 * 60);

            if (set.contains(t/60/10) && set.contains((t/60)%10) &&
                    set.contains((t%60)/10) && set.contains(t%60 %10)) {
                return String.format("%02d:%02d", t/60, t% 60);
            }
            
            
        }
        
        return time;
    }
    // O(1) solution , try all combination of 4 digits 4*4*4*4 = 256 possible solution
    
    public String nextClosestTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int min =  Integer.parseInt(time.substring(3));
        int cur = 60 * hour + min;
        int start = cur;
        Set<Integer> set = new HashSet<>();
        set.add(hour/10);
        set.add(hour%10);
        set.add(min/10);
        set.add(min%10);
        int diff = 24* 60;
        for (int h1:set) {
            for (int h2:set) {
                if (h1*10+h2 < 24) {
                    for (int m1:set) {
                        for (int m2:set) {
                            if (m1*10 + m2 < 60) {
                                int t = (h1*10+h2)* 60 + m1*10+m2;
                                int d = t > cur ? t - cur: 60*24 + t - cur;
                                if (d < diff) {
                                    diff = d;
                                    start = t;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return String.format("%02d:%02d", start/60, start% 60);
    }

}
