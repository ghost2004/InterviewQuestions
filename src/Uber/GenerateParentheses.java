package Uber;
/*
 * Given n pairs of parentheses, write a function to generate all
 *  combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
 */
import java.util.*;
public class GenerateParentheses {
    
    public void genP(int left, int right, String buf,List<String> list  ) {
        if (left == 0 && right == 0) {
            list.add(buf);
            return;
        }
        
        if (left > 0) 
            genP(left-1, right,buf+"(",list);
        
        if (right > left)
            genP(left, right-1,buf+")",list);
            
    }
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        String buf = new String();
        genP(n, n, buf, list);
        return list;
    }

}
