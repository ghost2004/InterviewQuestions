package Linkedin;
/*
 * Write a program that gives count of common characters presented in an array of strings. 
 * 
 
 For example: given the following three strings:
aghkafgkit
dfghako
qwemnaarkf
The output should be 3. Because the characters a, f and k are present in all 3 strings.
 */
import java.util.*;
public class CommonChar {
    public int getCommonCount(String in[]) {
        if (in == null || in.length == 0)
            return 0;
        
        HashSet<Character> set1 = new HashSet<>();
        HashSet<Character> set2;
        
        for (int i = 0; i < in[0].length(); i++) {
            set1.add(in[0].charAt(i));
        }
        
        for (int i = 1; i < in.length; i++) {
            set2 = new HashSet<>();
            for (int j = 0; j < in[i].length(); j++) {
                char c = in[i].charAt(j);
                if (set1.contains(c))
                    set2.add(c);
            }
            set1 = set2;
        }
        
        return set1.size();
    }
    
    public static void main(String args[]) {
        CommonChar p = new CommonChar();
        String a1[] = {"aghkafgkit",
                "dfghako",
                "qwemnaarkf"};
        System.out.println(p.getCommonCount(a1));
    }

}
