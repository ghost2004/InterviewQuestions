package Linkedin;
/*
 * Leetcode 81. Search in Rotated Sorted Array II
 * 
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Write a function to determine if a given target is in the array.

The array may contain duplicates.
 */
public class SearchInRotatedArrayII {
    
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int mid;
        while (left <=  right) {
            mid = (left + right) / 2;
            if (nums[mid] == target)
                return true;
            
            if (nums[mid] < nums[right] || nums[mid] < nums[left]) {
                // right part sorted, left part is not sorted
                if (nums[mid] < target && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            } else if (nums[mid] > nums[right] || nums[mid] > nums[right]) {
                // left part sorted, right part not sorted
                if (nums[left] <= target && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {
                // equal values, shift from right
                right--;
            }
            
        }
        
        return false;
        
    }

}
