package Facebook;
/*
 * Leetcode 974. Subarray Sums Divisible by K
 * 
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.

 

Example 1:

Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 */
public class SubSumDivisible {
    public int subarraysDivByK(int[] A, int K) {
        int map[] = new int[K];
        map[0] = 1;
        int count = 0;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum = (sum + A[i]) % K;
            if (sum < 0) sum += K;
            count += map[sum]; 
            map[sum]++;
        }
        return count;
    }
}
