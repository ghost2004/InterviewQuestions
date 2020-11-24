package Facebook;
/*
 * Leetcode 215. Kth Largest Element in an Array
 * 
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
 */
public class KthElement {
    public int patition(int nums[], int left, int right) {
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] <= pivot)
                right--;
            nums[left] = nums[right];
            while (left < right && nums[left] >= pivot)
                left++;
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }
    
    public int findKthLargest(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int p = patition(nums, left, right);
            if (p == k - 1)
                return nums[p];
            if (p < k) 
                left = p + 1;
            else
                right = p - 1;
            
        }
        return nums[left];
    }
}
