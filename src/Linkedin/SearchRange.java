package Linkedin;
/*
 * Leetcode 34 Search for a Range
 * Given an array of integers sorted in ascending order, find the starting and ending 
 * position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
 */
public class SearchRange {
    
    public int[] searchRange(int[] nums, int target) {
        int result[] = new int[2];
        int left = 0;
        int right = nums.length-1;
        int mid = 0;
        result[0] = -1;
        result[1] = -1;
        
        if (nums == null || nums.length < 1)
            return result;
        
        while (left <= right) {
            mid = (left+right)/2;
            if (nums[mid] == target)
                break;
            else if (nums[mid] < target) 
                left = mid + 1;
            else
                right = mid - 1;
        }
        
        if (nums[mid] == target) {
            left = mid;
            while (left >= 0 && nums[left] == target)
                left--;
            result[0] = left+1;
            right = mid;
            while (right < nums.length && nums[right] == target)
                right++;
            result[1] = right-1;
        }
        
        return result;
    }
    

}
