package Linkedin;
/*
 * Leetcode 189. Rotate Array
 * Rotate an array of n elements to the right by k steps.

    For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is 
    rotated to [5,6,7,1,2,3,4].
    
    Note:
    Try to come up as many solutions as you can, there are at least 3 
    different ways to solve this problem.
    
    Hint:
    Could you do it in-place with O(1) extra space?
 */
public class RotateArray {
    private void reverse(int nums[], int left, int right) {
        while (left < right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length < 2)
            return;
        int len = nums.length;
        int pos = k % len;
        reverse(nums, 0, len-1);
        reverse(nums, 0, pos-1);
        reverse(nums, pos, len-1);
    }
}
