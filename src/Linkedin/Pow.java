package Linkedin;
/*
 * Leetcode 50. Pow(x, n)
 * 
 * Implement pow(x, n).
 */
public class Pow {
    public double myPow(double x, int n) {
        if (n == 0)
            return 1;
        
        if (n < 0) {
            x = 1/x;
            n = -n;
        }
        
        double tmp = myPow(x, n/2);
        
        if (n % 2 == 0) 
            tmp *= tmp;
        else
            tmp *= tmp*x;
        
        return tmp;
        
        
    }
}
