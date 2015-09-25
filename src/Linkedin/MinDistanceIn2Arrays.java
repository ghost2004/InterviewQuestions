package Linkedin;
/*
 * find  min distance between elements across two sorted arrays
 */
public class MinDistanceIn2Arrays {
    public static int findMinDiff(int[] array1, int[] array2) {
        int idx1 = 0;
        int idx2 = 0;
        int minDiff = Integer.MAX_VALUE;
        while (idx1 < array1.length && idx2 < array2.length) {
            
            if (array1[idx1] == array2[idx2]) 
                return 0;
            int diff = Math.abs(array1[idx1]  - array2[idx2]);
            minDiff = Math.min(minDiff, diff);
            if (array1[idx1] > array2[idx2])
                idx2++;
            else
                idx1++;
            
        }
        
        if (idx1 < array1.length) {
            idx2 = array2.length - 1;
            while (idx1 < array1.length && array1[idx1] <= array1[idx2] ) {
                
                int diff = Math.abs(array1[idx1]  - array2[idx2]);
                minDiff = Math.min(minDiff, diff);
                idx1++;
            }
        } else {
            idx1 = array1.length - 1;
            while (idx2 < array2.length && array1[idx1] >= array1[idx2]  ) {
                
                int diff = Math.abs(array1[idx1]  - array2[idx2]);
                minDiff = Math.min(minDiff, diff);
                idx2++;
            }
        }
        
        return minDiff;
    }
    public static void main(String [] args)
    {
        int [] array1 = {12, 34, 57, 61, 69, 80};
        int [] array2 = {27, 39, 48, 51, 79};
        int a1[] = {1, 4, 5, 6};
        int a2[] = { 100,200};
        int a3[] = {-100, 4, 50,65};
        int a4[] = {55, 80,100,200};
        System.out.println(findMinDiff(array1,array2));
        System.out.println(findMinDiff(a1,a2));
        System.out.println(findMinDiff(a3,a4));
    }
}
