package Linkedin;
/*
 * Leetcode 384. Shuffle an Array
 * Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();
 */


public class ShuffleArray {
    private int nums[];

    public ShuffleArray(int[] nums) {
        this.nums = nums;
        
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return this.nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int result[] =  nums.clone();
        
        for (int i = 1; i < nums.length; i++) {
            int n = (int)(Math.random()*(i+1));
            int temp = result[i];
            result[i] = result[n];
            result[n] = temp;
        }
        
        return result;
    }

}
