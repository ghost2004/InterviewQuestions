package Linkedin;
/*
 * Leetcode 35. Search Insert Position
 * Given a sorted array and a target value, return the index if the 
 * target is found. If not, return the index where it would be if 
 * it were inserted in order.
    
    You may assume no duplicates in the array.
    
    Here are few examples.
    [1,3,5,6], 5 → 2
    [1,3,5,6], 2 → 1
    [1,3,5,6], 7 → 4
    [1,3,5,6], 0 → 0
 */
public class SearchInsertPos {

    public int searchInsert(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = (left+right)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return left;
        
    }
}
