package Linkedin;
/*
 * Find the prime number
 */

import java.util.*;
public class PrimeNumber {
    public static ArrayList<Integer> findPrimeNum(int n) {
        ArrayList<Integer> out = new ArrayList<Integer>();
        if (n < 2)
            return out;
        int idx = 2;
        boolean flag[] = new boolean[n+1];
        Arrays.fill(flag, false);
        
        do {
            out.add(idx);
            for (int i = idx*2; i <= n; i+=idx) {
                flag[i]  = true;
            }
            do {
                idx++;
            } while (idx <= n && flag[idx]);
            
        } while (idx <= n);
        
        return out;
    }
    
    public static void main(String args[]) {
        ArrayList<Integer> result = findPrimeNum(100);
        for (int i = 0; i < result.size();i++) {
            System.out.print(result.get(i)+" ");
        }
    }
}
