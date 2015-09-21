package PureStorage;
/*
 * Get all the coordinates of a circle, without using sqrt, sin or cos functions
 * 
 * Hint: all the coordinates are integers
 */
public class DrawCircles {
    private int radius;
    public DrawCircles(int rad) {
        this.radius = rad;
    }
    
    // use binary search for sqrt
    public int mySqrt(int key) {
        int start = 0;
        int end = key/2+1;
        while (start<=end) {
            int mid = (start+end)/2;
            int result = mid*mid;
            if (result == key)
                return mid;
            else if (result < key)
                start = mid+1;
            else
                end = mid-1;
            
        }
        return end;
        
    }
    // get Y coordinate with given X
    public int findY(int x) {
        if (Math.abs(x) == radius)
            return 0;
        int key = radius*radius - x*x;
        return mySqrt(key);
        
    }
    
    public int[][] draw() {
        // get the point of 1/8 point first
        int size = 4*radius;
        int output[][] = new int[size][2];
        int i;
        int mid = radius/2;
        // draw first 1/8 circle
        int x = -radius;
        for (i = 0; i <= mid; i++) {
            output[i][0] = x;
            output[i][1] = findY(x);
            x++;
        }
        // get 2nd 1/8 by first 1/8
        for ( i = mid+1; i < radius;i++) {
            output[i][0] = output[radius-i][1];
            output[i][1] = output[radius-i][0];
        }
        // the last point of first quarter must be done manually
        output[radius][0] = 0;
        output[radius][1] = radius;
        
        // get first half by first quarter
        for ( i = radius+1; i <= 2*radius;i++) {
            output[i][0] = -output[2*radius - i][0];
            output[i][1] = output[2*radius - i][1];
        }
        
        // get the 2nd half by first half
        for (i = 2*radius+1; i <4*radius; i++) {
            output[i][0] = output[4*radius - i][0];
            output[i][1] = -output[4*radius - i][1];
        }
        
        return output;
    }
    
    public void printCircle() {
        int output[][] = draw();
        for (int i=0; i < output.length; i++) {
            System.out.print("(" + output[i][0]+","+output[i][1]+"), ");
        }
        System.out.println();
    }
    
    public static void main(String args[]) {
        DrawCircles d = new DrawCircles(2);
        
        d.printCircle();
        
        d = new DrawCircles(4);
        
        d.printCircle();
    }

}
