package Facebook;

/*
 * Leetcode 301. Remove Invalid Parentheses
 * 
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
 * 
 */
import java.util.*;
public class RemoveInvalidParentheses {
    private boolean validation(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                count++;
            if (c == ')') {
                count--;
                if (count < 0)
                    return false;
            }
        }
        
        return count == 0;
    }
    
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        boolean found = false;
        queue.offer(s);
        while (!queue.isEmpty()) {
            String t = queue.poll();
            
            if (validation(t)) {
                found = true;
                result.add(t);
            }
            if (found)
                continue;
            for (int i = 0; i < t.length(); i++) {
                char c = t.charAt(i);
                if (c == '(' || c == ')') {
                    String n = t.substring(0, i) + t.substring(i+1);
                    if (!visited.contains(n)) {
                        visited.add(n);
                        queue.offer(n);
                    }
                }
            }
        }
        
        return result;
        
    }
}
