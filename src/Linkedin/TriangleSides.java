package Linkedin;
/*
 * 3 sides can form  a triangle when 
 *  A + B > C
 *  B + C > A
 *  A + C > B
 *  
 *  Given a list of segments and return sides can form a triangle
 *  
 *  return empty array when there is no solution
 */

import java.util.*;
public class TriangleSides {
    
    public int[] getTriangleSides(int[] segments) {
        int out[] = new int[0];
        if (segments == null || segments.length < 3)
            return out;
        Arrays.sort(segments);
        
        for (int i = 2; i < segments.length; i++) {
            if (segments[i] < segments[i-1] + segments[i-2]) {
                out = new int[3];
                for (int j = 0; j < 3; j++)
                    out[j] = segments[i-j];
                return out;
            }
        }
        
        return out;
    }

}
