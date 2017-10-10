package Linkedin;
/*
 * Leetcode 69. Sqrt(x)
 * Implement int sqrt(int x).

    Compute and return the square root of x.
 * 
 */
public class MySqrt {
    public int mySqrt(int x) {
        long start = 0;
        long end = x/2+1;
        
        while (start <= end) {
            long mid = (start+end)/2;
            long t = mid*mid;
            if (t == x)
                return (int)mid;
            else if (t > x)
                end = mid - 1;
            else 
                start = mid+1;
               
            
        }
        
        return (int)end; 
    }
    
    public double mySqrt(double x)  {
        if (x < 0)
            return -1;
        if (x == 0 || x == 1)
            return x;
        double precision=0.00000000001;
        
        double start;
        double end;
        
        if (x > 1) {
            start = 1;
            end = x;
        } else {
            start = x;
            end = 1;
        }
        
        while (end - start > precision) {
            double mid = (start+end)/2;
            double s = mid*mid;
            if (Math.abs(x-s) < precision)
                return mid;
            else if (s < x) 
                start = mid;
            else
                end = mid;
            
        }
        
        return end;
            
    }
    
    public static void main(String args[]) {
        MySqrt m = new MySqrt();
        System.out.println(m.mySqrt(0.34));
        System.out.println(m.mySqrt(81.0));
    }

}
