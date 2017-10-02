package Linkedin;
/*
 * Leetcode 438. Find All Anagrams in a String
 * 
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

    Strings consists of lowercase English letters only and the length of both strings s 
    and p will not be larger than 20,100.
    
    The order of output does not matter.
    
    Example 1:
    
    Input:
    s: "cbaebabacd" p: "abc"
    
    Output:
    [0, 6]
    
    Explanation:
    The substring with start index = 0 is "cba", which is an anagram of "abc".
    The substring with start index = 6 is "bac", which is an anagram of "abc".
    Example 2:
    s
    s: "abab" p: "ab"
    
    Output:
    [0, 1, 2]
    
    Explanation:
    The substring with start index = 0 is "ab", which is an anagram of "ab".
    The substring with start index = 1 is "ba", which is an anagram of "ab".
    The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
import java.util.*;
public class AllAnagramsInStr {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() < p.length())
            return list;
        int map[] = new int[256];
        for (int i = 0; i < p.length(); i++)
            map[p.charAt(i)]++;
        int left = 0;
        int right = 0;
        int count = p.length();
        
        while (right < s.length()) {
            if (map[s.charAt(right)] > 0 ) {
                count--;
            }
            map[s.charAt(right)] --;
            //System.out.println("right: "+ right+ ", " +s.charAt(right)+ " In map: " + map[s.charAt(right)] + " count " + count);
            right++;
            
            if (count == 0)
                list.add(left);
            
            if (right - left == p.length()) {
                //System.out.println("left: "+ left+ ", " +s.charAt(left)+ " In map: " + map[s.charAt(left)] );
                if (map[s.charAt(left)] >= 0) {
                    count++;
                    
                }
                map[s.charAt(left)]++;
                left++;
            }
            
        }
        
        
        
        return list;
        
    }
    public List<Integer> findAnagramsMap(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() < p.length())
            return list;
        
        HashMap<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        
        int count = p.length();
        int left = 0;
        int right = 0;
        
        for (right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            Integer value = map.get(c);
            if (value != null) {
                if (value > 0)
                    count--;
                map.put(c, value-1);
            }
            
            if (right - left == p.length()) {
               c = s.charAt(left);
               value = map.get(c);
               if (value != null) {
                   if (value >= 0)
                       count++;
                   map.put(c, value+1);
               }
               
               left++;
               
            }
            
            if (count == 0)
                list.add(left);
            
        }
        return list;
        
    }
    public static void main(String args[]) {
        AllAnagramsInStr a = new AllAnagramsInStr();
        System.out.println(a.findAnagramsMap("cbaebabacd", "abc"));
    }
}
