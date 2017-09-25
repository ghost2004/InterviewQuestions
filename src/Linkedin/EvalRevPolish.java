package Linkedin;
/*
 * Leetcode 150. Evaluate Reverse Polish Notation
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
import java.util.*;
public class EvalRevPolish {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0)
            return 0;
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].length() == 1 && !Character.isDigit(tokens[i].charAt(0))) {
                if (stack.size() < 2)
                    return 0;
                int t2 = stack.pop();
                int t1 = stack.pop();
                switch (tokens[i].charAt(0)) {
                case  '+':
                    stack.push(t1+t2);
                    break;
                case '-':
                    stack.push(t1-t2);
                    break;
                case '*':
                    stack.push(t1*t2);
                    break;
                case '/':
                    stack.push(t1/t2);
                    break;
                }
            } else 
                stack.push(Integer.parseInt(tokens[i]));
        }
        
        
        return stack.peek();
    }

}
