package Linkedin;
/*
 * Consider this string representation for binary trees. Each node is of the form (lr), 
 * where l represents the left child and r represents the right child. If l is the
 *  character 0, then there is no left child. Similarly, if r is the character 0, then 
 *  there is no right child. Otherwise, the child can be a node of the form (lr), and 
 *  the representation continues recursively. 

For example: (00) is a tree that consists of one node. ((00)0) is a two-node tree in which 
the root has a left child, and the left child is a leaf. And ((00)(00)) is a three-node tree,
 with a root, a left and a right child. 

Write a function that takes as input such a string, and returns -1 if the string is malformed, 
and the depth of the tree if the string is well-formed. 

For instance: 
find_depth('(00)') -> 0 
find_depth('((00)0)') -> 1 
find_depth('((00)(00))') -> 1 
find_depth('((00)(0(00)))') -> 2 
find_depth('((00)(0(0(00))))') -> 3 
find_depth('x') -> -1 
find_depth('0') -> -1 
find_depth('()') -> -1 
find_depth('(0)') -> -1 
find_depth('(00)x') -> -1 
find_depth('(0p)') -> -1
 */
import java.util.*;
public class StringTreeDepth {
    
    public int getTreeDepth (String in) {
        if (in == null || in.length() < 3)
            return -1;
        int depth = -1;
        int curDepth = -1;
        Stack<Integer> stack = new Stack<>();
        stack.push(curDepth);
        for (int i = 0; i < in.length(); i++) {
            switch (in.charAt(i)) {
            case '0':
                stack.push(curDepth);
                break;
            case '(':
                curDepth++;
                depth = Math.max(depth, curDepth);
                
                break;
            case ')':
                curDepth--;
                if (stack.size() < 2)
                    return -1;
                int c1 = stack.pop();
                int c2 = stack.pop();
                if (c1 != c2)
                    return -1;
                stack.push(curDepth);
                
                
                break;
            default:
                return -1;
                    
                
            }
        }
        
        if (stack.isEmpty() || stack.peek() != -1)
            return -1;
        
        return depth;
    }
    
    public int getTreeDepth_old(String in) {
        if (in == null || in.length() < 3)
            return -1;
        int depth = -1;

        int leftP = 0;
        int zero = 0;
        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            if (c == '(') {
                zero = 0;
                leftP++;
                depth = Math.max(depth, leftP);
            } else if (c ==')') {
                leftP--;
                if (leftP < 0)
                    return -1;
                zero = 0;
            } else if (c == '0'){
                zero++;
                if (zero > 2) 
                    return -1;
            } else
                return -1;
        }
        
        
        if (leftP != 0)
            return -1;
        
        return depth-1;
    }
    
    public static void main(String args[]) {
        StringTreeDepth s = new StringTreeDepth();
        System.out.println(s.getTreeDepth("((00)0)"));
    }

}
