package Google;
/*
 * Leetcode 683. K Empty Slots
 * 
 * There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom
 *  one by one in N days. In each day, there will be exactly one flower blooming and it will 
 *  be in the status of blooming since then.

        Given an array flowers consists of number from 1 to N. Each number in the array represents 
        the place where the flower will open in that day.
        
        For example, flowers[i] = x means that the unique flower that blooms at day i will be at 
        position x, where i and x will be in the range from 1 to N.
        
        Also given an integer k, you need to output in which day there exists two flowers in the 
        status of blooming, and also the number of flowers between them is k and these flowers are not blooming.
        
        If there isn't such day, output -1.
        
        Example 1:
        Input: 
        flowers: [1,3,2]
        k: 1
        Output: 2
        Explanation: In the second day, the first and the third flower have become blooming.
        Example 2:
        Input: 
        flowers: [1,2,3]
        k: 1
        Output: -1
        Note:
        The given array will be in the range [1, 20000].
 */

import java.util.*;
public class KEmptySlots {
    
    /*
     * This representation will make the problem simpler to understand now.
        So now consider example - [4,1,3,5,2].
        Day 1 : [0,0,0,B,0] - flower at index 4 bloom : setContains : [4]
        Day 2 : [B,0,0,B,0] - flower at index 1 bloom : setContains : [1,4] : k = 2
        Day 3 : [B,0,B,B,0] - flower at index 3 bloom : setContains : [1,3,4] : k = 1, k = 0
        Day 4 : [B,0,B,B,B] - flower at index 5 bloom : setContains : [1,3,4,5] : k = 0
        Day 5 : [B,B,B,B,B] - flower at index 2 bloom : setContains : [1,2,3,4,5] : k = 0
        
        On day 2, when you add 1, set has [1,4]. This means that there are no flowers that bloom 
        in between these two positions. (which is 2,3). So two flowers.(k=2)
        On day 3, when you add 3, set has [1,3,4]. This means that there are no flowers that bloom 
        in between 1-3, and 3-4. (k = 1, and k = 0)
        
        Similarly on day 4, when you add 5, position to left is 4. So k = 0. and so on.
        
        So every day when a flower blooms, we just need to find out it's left and right bloomed flowers.
        All the flowers in between is guaranteed to be no-bloom.
     */
    // Tree set solution
    public int kEmptySlots2(int[] flowers, int k) {
        TreeSet<Integer> set  = new TreeSet<>();
        
        for (int i = 0; i < flowers.length; i++) {
            int cur = flowers[i];
            Integer next = set.lower(cur);
            if (next != null && cur - next == k+1)
                return i+1;
            next = set.higher(cur);
            if (next != null && next - cur == k+1)
                return i+1;
            set.add(cur);
                        
        }
        
        return -1;
    }
    
    // O(n) array solution
    /*
     * The idea is to use an array days[] to record each position's flower's blooming day. 
     * That means days[i] is the blooming day of the flower in position i+1. 
     * We just need to find a subarray days[left, left+1,..., left+k-1, right] which satisfies: 
     * for any i = left+1,..., left+k-1, we can have days[left] < days[i] && days[right] < days[i]. 
     * Then, the result is max(days[left], days[right]).
     */
    public int kEmptySlots(int[] flowers, int k) {
        int len = flowers.length;
        // days[i] means the date when position i blooms
        int days[] = new int[len];
        
        for (int i = 0; i < len; i++)
            days[flowers[i]-1] = i +1; 
        
        int res = len + 1;
        int left = 0;
        int right = k+1;
        
        for (int i = 0; right < len; i++) {
            if (days[i] < days[left] || days[i] <= days[right]) {
                if (i == right)
                    res = Math.min(res, Math.max(days[left], days[right]));
                left = i;
                right = i + k + 1;
            }
        }
        
        return res < len ? res : -1;
    }
    

}
