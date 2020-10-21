package Facebook;
/*
 * Given a list of integers to represent the height of a list of buildings, the rightmost one is ocean
 * 
 * Return the indexes of buildings which have the ocean view
 * 
 * 
 */
import java.util.*;
public class OceanView {
    
    public static List<Integer> getOceanView(int height[]) {
        LinkedList<Integer> result = new LinkedList<>();
        
        if (height == null || height.length < 1)
            return result;
        
        result.addFirst(height.length - 1);
        int curMax = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            if (height[i] > curMax)
                result.addFirst(i);
            curMax = Math.max(curMax, height[i]);
        }
        
        return result;
    }
    
    public static void printArray(List<Integer> list) {
        for (int i : list)
            System.out.print(i + " ");
        System.out.println();
    }
    
    public static void main(String args[]) {
        int test1[] = {1,5, 2, 3, 1};
        printArray(getOceanView(test1));
    }
    

}
