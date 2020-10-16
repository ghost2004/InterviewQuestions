package DoorDash;
/*
 * Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / 
 operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5
Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.
 */
import java.util.*;
public class BasicCalculatorII {
    public int calculate(String s) {
        if (s == null || s.length() < 1)
            return 0;
        int number = 0;
        char sign = '+';
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = number * 10 + c - '0';
            } 
            if (c == '('){
                int j = i + 1, count = 1;
                for (; j < s.length(); j++){
                    if (s.charAt(j) == '(') count++;
                    else if (s.charAt(j) == ')') count--;

                    if (count == 0) break;
                }

                number = calculate(s.substring(i + 1, j));
                i = j;
            }
            if((!Character.isDigit(c) && c != ' ' ) || i==s.length()-1){
                switch (sign) {
                case '+':
                    stack.push(number);
                    break;
                case '-':
                    stack.push(-number);
                    break;
                case '*':
                    stack.push(stack.pop() * number);
                    break;
                case '/':
                    if (number == 0)
                        throw new ArithmeticException();
                    stack.push(stack.pop() / number);
                    break;
                default:

                    throw new ArithmeticException();
                }
                sign = c;
                number = 0;
            }
        }
        
        int result = 0;
        while (!stack.isEmpty())
            result += stack.pop();
        
        return result;
        
    }
    
    public static void main(String args[]) {
        BasicCalculatorII b =  new BasicCalculatorII();
        System.out.println(b.calculate("4/3+2"));
        
    }

}
