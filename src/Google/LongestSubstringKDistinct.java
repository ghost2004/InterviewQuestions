package Google;
/*
 * Leetcode 340. Longest Substring with At Most K Distinct Characters 
 * 
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/

Given a string, find the length of the longest substring T that contains 
at most k distinct characters.

For example, 

Given s = "eceba" and k = 2,

T is "ece" which its length is 3.
 */
public class LongestSubstringKDistinct {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int maxLen = 0;
        
        int map[] = new int[256];
        int mapSize = 0;
        
        int start = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map[c]++ == 0) {
                mapSize++;
                if (mapSize > k) {
                    char left = s.charAt(start++);
                    
                    if (--map[left] == 0)
                        mapSize--;
                }
            }
            maxLen = Math.max(maxLen, i - start+1);
            
        }
        
        return maxLen;
    }
    
    public static void main(String args[]) {
        LongestSubstringKDistinct s = new LongestSubstringKDistinct();
        
        System.out.println(s.lengthOfLongestSubstringKDistinct("eceba", 2));
    }
}
