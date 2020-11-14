package DoorDash;

/*
 * Leetcode 239. Sliding Window Maximum
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 *  You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

 

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Example 2:

Input: nums = [1], k = 1
Output: [1]
Example 3:

Input: nums = [1,-1], k = 1
Output: [1,-1]
Example 4:

Input: nums = [9,11], k = 2
Output: [11]
Example 5:

Input: nums = [4,-2], k = 2
Output: [4]
 */

import java.util.*;
public class SlidingWindowMaximum {
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        if(nums == null || nums.length < 2) return nums;
        int length = nums.length;
        int result[] = new int[length - k + 1];
        
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < length; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            
            queue.add(i);
            if (queue.peekFirst() <= i - k)
                queue.pollFirst();
            if (i + 1 >= k) {
                result[i - k] = result[i + 1 - k] = nums[queue.peekFirst()];
            }
            
        }
        
        
        
        return result;
    }

}
