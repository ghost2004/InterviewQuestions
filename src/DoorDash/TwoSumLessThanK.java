package DoorDash;

import java.util.Arrays;

/*
 * LeetCode 1099. Two Sum Less Than K
 * Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S and S < K. 
 * If no i, jexist satisfying this equation, return -1.

Example 1:

Input: A = [34,23,1,24,75,33,54,8], K = 60
Output: 58
Explanation: 
We can use 34 and 24 to sum 58 which is less than 60.
Example 2:

Input: A = [10,20,30], K = 15
Output: -1
Explanation: 
In this case it's not possible to get a pair sum less that 15.
Note:

1 <= A.length <= 100
1 <= A[i] <= 1000
1 <= K <= 2000
 */
public class TwoSumLessThanK {
    public static int twoSumLessThanK(int[] A, int K) {
        if (A == null || A.length < 1)
            return -1;
        
        int result = -1;
        
        int left = 0;
        int right = A.length - 1;
        
        Arrays.sort(A);
        if (A[0] > K)
            return -1;

        while (left < right) {
            int sum = A[left] + A[right];
            if (sum < K) {
                result = Math.max(result, sum);
                left ++;
            } else {
                right --;
            }
        }
        
        return result;
    }
    
    public static void main(String args[]) {
        int[] testArray1 = {34,23,1,24,75,33,54,8};
        int[] testArray2 = {10,20,30};
        
        System.out.println(twoSumLessThanK(testArray1, 60));
        System.out.println(twoSumLessThanK(testArray2, 15));
    }

}
