package Linkedin;
/*
 * Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,

add(1); 
add(3); 
add(5);
find(4) -> true
find(7) -> false
 */
import java.util.*;
public class TwoSumIII {

    private HashMap<Integer, Integer> map = new HashMap<>();
    
    public void add(int num) {
        Integer cnt = map.get(num);
        if (cnt != null) {
            map.put(num, cnt+1);
        } else
            map.put(num, 1);
        
    }
    
    public boolean find(int sum) {
        for (Integer i:map.keySet()) {
            int target = sum - i;
            if (map.containsKey(target)) {
                if (target != i)
                    return true;
                if (map.get(target) > 1)
                    return true;
                
            }
            
        }
        
        return false;
    }
    
}
