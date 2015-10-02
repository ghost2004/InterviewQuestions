package Linkedin;
/*
 * Find the intersection of 2 sorted array
 * There should be no duplicate elements in the intersection
 */
import java.util.*;
public class IntersctionOf2array {
    public static ArrayList<Integer> getInter(int array1[], int array2[]) {
        ArrayList<Integer> out = new ArrayList<Integer>();
        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < array1.length && idx2 < array2.length) {
            if (array1[idx1] == array2[idx2]) {
                int key = array1[idx1];
                while (idx1 < array1.length && array1[idx1] == key)
                    idx1++;
                while (idx2 < array1.length && array2[idx2] == key)
                    idx2++;
                out.add(key);
            } else if (array1[idx1] > array2[idx2]) {
                idx2++;
            } else {
                idx1++;
            }
                
        }
        
        return out;
    }
    
    public static void printArray(ArrayList<Integer> a) {
        for (int i = 0 ; i < a.size();i++) {
            System.out.print(a.get(i)+" ");
        }
        System.out.println();
    }
    
    public static void main(String args[]) {
        int a1[] = { 1, 2, 4, 6, 10};
        int a2[] = { 1, 2, 4, 9, 10};
        int a3[] = { 1, 2, 4, 4, 4};
        int a4[] = { 1, 2, 4, 9, 10};
        int a5[] = { 1, 2, 4, 6, 11};
        int a6[] = {  11, 14, 19, 120};
        printArray(getInter(a1,a2));
        printArray(getInter(a3,a4));
        printArray(getInter(a5,a6));
        printArray(getInter(a1,a6));
    }
}
