package Common;

public class Point {
    public int x, y;
    
    public Point(int a,int b){
        x = a;
        y = b;
    }
    
    public String toString(){
        return "("+Integer.toString(x)+","+Integer.toString(y)+")";
    }
    
    public static Point[] getFromArray (int a[][]) {
        Point[] out= new Point[a.length];
        
        for (int i = 0; i < a.length; i++) {
            out[i] = new Point(a[i][0], a[i][1]);
        }
        
        return out;
    }
}
