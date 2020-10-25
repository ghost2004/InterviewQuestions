package Facebook;
/*
 * You are given two arrays of integer a and b,which are both sorted in ascending order and contain unique elements
 * 
 * You can take several (possibly zero) numbers from array b and add them to a at any positions in any order. You want the array a to be an arithmetic 
 * progression after this
 * 
 * Your task is to find the maximal length of the resulting arithmetic progression represented by array a that can be achieved.
 * If it is impossible to obtain an array forming an arithmetic progression, return -1
 * 
 * Example
 *  - For a = {0, 4, 8, 16] and b = [0, 2, 6, 12, 14, 20], the output should be 
 *    maxArithmetic(a, b) = 6
 *    
 *  - For a = [5, 7, 13, 14] and b = [9, 11, 15], the output should be   
 *    maxArithmetic(a, b) = -1
 * 
 */
import java.util.*;
import java.util.stream.Collectors;
public class MaxArithmetic {
    public int maxArithmetic(int a[], int b[]) {

        int g= a[1] - a[0];
        for (int i = 2; i < a.length; i++) {
            g = gcd(g, a[i] - a[i-1]);
        }

        Set<Integer> factors = getfactor(g);
        Set<Integer> candidate = Arrays.stream(b).boxed().collect(Collectors.toSet());
        int max = -1;
        for (int factor : factors) {
            max = Math.max(max, getArithmetic(a, factor, candidate));
        }
        return max;
    }
    
    private int gcd(int a, int b) {
        if (a < b)
            return gcd(b, a);
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
    
    private Set<Integer> getfactor(int a) {
        Set<Integer> out = new HashSet<>();
        int upper = (int)Math.sqrt(a);
        for (int i = 1; i <= upper; i++) {
            if (a % i == 0) {
                out.add(i);
                out.add(a / i);
            }
        }
        return out;
    }
    
    private int getArithmetic(int a[], int delta, Set<Integer> candidate) {
        int last = a[0];
        int length = 1;
        int index = 1;
        while (index < a.length) {
            if (a[index] - last == delta) {
                index++;
            } else {
                if (!candidate.contains(last+delta)) {
                    return -1;
                }                
            }
            last += delta;
            length++;
        }
        while (candidate.contains(last + delta)) {
            length++;
            last += delta;
        }
        last = a[0]; 
        while (candidate.contains(last - delta)) {
            length++;
            last -= delta;
        }
        
        return length;
    }
    
    public static void main(String args[]) {
        MaxArithmetic m = new MaxArithmetic();
        int a1[] = {0, 4, 8, 16};
        int b1[] = {0, 2, 6, 12, 14, 20};
        System.out.println(m.maxArithmetic(a1, b1));
        int a2[] = {5, 7, 13, 14};
        int b2[] = {9, 11, 15};
        System.out.println(m.maxArithmetic(a2, b2));
    }
}
