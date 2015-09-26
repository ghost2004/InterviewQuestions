package Linkedin;
/*
 * Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
 */
public class ValidNumber {
    public static boolean isNumber(String s) {
        if (s == null || s.length() == 0)
            return false;
        boolean dotFlag = false;
        boolean eFlag = false;
        boolean numFlag = false;
        String p = s.trim();
        if (p.length() == 0)
            return false;
        int idx = 0;
        if (p.charAt(idx) == '+' || p.charAt(idx) == '-')
            idx++;
        for (; idx < p.length(); idx++) {
            char key = p.charAt(idx);
            if (Character.isDigit(key)) {
                numFlag = true;
            }
            else if (key == 'e') {
                if (!eFlag) {
                    if (!numFlag)
                        return false;
                    numFlag = false;
                    eFlag = true;
                    continue;
                } 
                return false;
            }else if (key == '.') {
                if (!dotFlag) {
                    if (eFlag)
                        return false;
                    dotFlag = true;
                    continue;
                } 
                return false;
            } else if (key == '+' || key == '-') {
                if (p.charAt(idx-1) != 'e')
                    return false;
            } else
                return false;
                
        }
        

        
        return numFlag;
    }
    
    public static void main(String args[]) {
        String t = "  tere ";
        System.out.println(t.trim());
    }
}
