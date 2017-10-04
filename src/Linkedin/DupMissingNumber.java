package Linkedin;
/*
 * Given an unsorted array, with the range from 1~N. Now change one number x to y(both in this range)
 * 
 * Find these 2 numbers
 * 
 */
public class DupMissingNumber {
    public static  int[] findDupMissing(int nums[]) {
        int out[] = new int[2];
        
        int xor1 = 0;

        // Double the array with 1~N
        // For example array like {1,2,2} will be {1,2,2,1,2,3}
        // the XOR total will be 2^3
        for (int i = 1; i <= nums.length; i++) {
            xor1 ^= i;
            xor1 ^= nums[i-1];
        }

        // get the last set bit
        int mask = xor1 & (-xor1);
        
        // use the mask split the double array into half
        // each of XOR result will be the number we are looking for
        for (int i = 1; i <= nums.length; i++) {
            if ((i & mask) == 1) {
                out[0] ^= i;
            } else {
                out[1] ^= i;
            }
            if ((nums[i-1] & mask) == 1) {
                out[0] ^= nums[i-1];
            } else {
                out[1] ^= nums[i-1];
            }
        }
        System.out.println("result: "+ out[0]+" "+out[1]);
        return out;
    }

    public static void main(String args[]) {
        int a1[] = { 1,2,2,4};
        int a2[] = { 1,3,2,5,5,6};
        
        findDupMissing(a1);
        findDupMissing(a2);
    }
}
