package Linkedin;
/*
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
 */
import java.util.*;

import Common.NestedInteger;

public class NestedListWeightSum {
    private int getDepth(List<NestedInteger> list, int depth) {
        if (list == null || list.size() == 0)
            return 0;
        int weight = 0;
        for (NestedInteger n:list) {
            if (n.isInteger()) {
                weight += depth * n.getInteger();
            } else {
                weight += getDepth(n.getList(), depth+1);
            }
            
        }
        
        return weight;
    }
    public int depthSum(List<NestedInteger> nestedList) {
        return getDepth(nestedList, 1);
    }
    
    
    public static void main(String args[]) {
        NestedListWeightSum sum = new NestedListWeightSum();
        
        String test1 = "[[1,1],2,[1,1]]";
        NestedInteger t1 = NestedInteger.getFromString(test1);
        
        System.out.println(sum.depthSum(t1.getList()));
    }

}
