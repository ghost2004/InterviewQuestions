package Linkedin;
/*
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
 */
public class MaxProductionSubArray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int max = nums[0];
        int prevMax = nums[0];
        int prevMin = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int pMax = prevMax * nums[i];
            int pMin = prevMin * nums[i];
            
            if (pMin > pMax) {
                int tmp = pMax;
                pMax = pMin;
                pMin = tmp;
            }
            
            max = Math.max(max, Math.max(pMax, nums[i]));
            prevMax = Math.max(nums[i], pMax);    
            prevMin = Math.min(pMin, nums[i]);
        }
        return max;
    }
}
