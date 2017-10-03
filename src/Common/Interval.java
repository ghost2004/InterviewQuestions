package Common;

public class Interval {
    public int start;
    public int end;
    public Interval() { start = 0; end = 0; }
    public Interval(int s, int e) { start = s; end = e; }
    
    public static Interval[] getFromArray(int start[], int end[]){
        if (start == null || end == null || start.length != end.length)
            return null;
        
        Interval[] out = new Interval[start.length];
        
        for (int i = 0; i < start.length; i++) { 
            out[i] = new Interval(start[i], end[i]);
        }
        
        return out;
    }
    
    public static Interval[] getFromArray(int a[][]) {
        if (a == null || a.length < 1 || a[0].length != 2)
            return null;
        Interval[] out = new Interval[a.length];
        
        for (int i = 0; i < a.length; i++) {
            out[i] = new Interval(a[i][0], a[i][1]);
        }
        return out;
    }
}
