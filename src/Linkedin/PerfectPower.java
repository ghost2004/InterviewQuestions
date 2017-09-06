package Linkedin;
/*
 * Provide an implementation of the following interface:
Class Powers {
Public:
long next();
void reset();
}
Next function: returns the next integer a in the arithemtic sequence of 
integers where a = m^n, m > 1, n > 1, and m and n are both integers. Thus, 
the first few outputs will be 4, 8, 9, 16, 25, 27, 32, 36 etc.
Reset function: resets the sequence to the beginning, such that the next 
call to next() will return 4. (Heap + don’t add new power base until the last element is used)
 */
public class PerfectPower {
    private long next = 4;
    private int base = 2;
    
    private int isPerfectPower(long n) {
        int a;
        for (a = 2; a < n/2; a++) {
            double b = Math.log(n)/Math.log(a);
            if (b - (int)b == 0)
                return a;
        }
        return -1;
    }
    
    public long next() {
        long prev = next;
        next *= base;
        
        for (long i = prev+1; i < next; i++) {
            int a = isPerfectPower(i);
            if (a != -1) {
                next = i;
                base = a;
                break;
            }
        }
        
        return prev;
    }
    
    public void reset() {
        next = 4;
        base = 2;
    }
    
    public static void main(String args[]) {
        PerfectPower p = new PerfectPower();
        for (int i = 0; i < 20; i++) {
            System.out.print(p.next()+" ");
        }
    }

}
