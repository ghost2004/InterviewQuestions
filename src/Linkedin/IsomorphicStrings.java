package Linkedin;
/*
 * Leetcode 205. Isomorphic Strings
 * 
 * Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. 
No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.


 */

import java.util.*;

public class IsomorphicStrings {
    
    public boolean isIsomorphic(String s, String t) {
        
        HashMap<Character, Character> map = new HashMap<>();
        HashSet<Character> values = new HashSet<>();
        
        for (int i = 0; i < s.length(); i++) {
            
            Character key = s.charAt(i);
            Character val = map.get(key);
            
            if (val != null) {
                if (!val.equals(t.charAt(i)))
                    return false;
                continue;
            } else if (values.contains(t.charAt(i)))
                return false;
            map.put(key, t.charAt(i));
            values.add(t.charAt(i));
        }
        
        
        
        return true;
    }
    
    public static void main(String args[]) {
        IsomorphicStrings s = new IsomorphicStrings();
        System.out.println(s.isIsomorphic("ab", "aa"));
    }

}
