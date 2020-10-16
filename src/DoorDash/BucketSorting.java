package DoorDash;
/*
Imagine you have an array like this (note that the array has duplicates, and includes 0 and k): a = [ 4, 64, 200, 42, 56, 22, 1, 64, 0, 161, 200, 0, 42 ]
What you know about this array is
Values are integers
Values are non-negative and less than some number, for example: 0 <= value <= k, where k is an integer
To start, can you please write some code to sort this array using any algorithm you want and not using the library functions.
*/
public class BucketSorting {
     public static int[] sort(int[] nums, int k)  {
         int bucket[] = new int[k+1];
         for (int i : nums) {
             bucket[i]++;
         }
         int idx = 0;
         for (int i = 0; i < k+1 && idx < nums.length; i++) {
             for (int j = 0; j < bucket[i]; j++) {
                     nums[idx++] = i;
                 }
             
         }
         
         return nums;
        
    }
     
     public static void printArray(int[] nums) {
         for (int i: nums)
             System.out.print(i + " ");
         System.out.println();
     }
     
     public static void main(String args[]) {
         
         int test1[] = { 4, 64, 200, 42, 56, 22, 1, 64, 0, 161, 200, 0, 42};
         printArray(sort(test1, 200));
         
     }
}
