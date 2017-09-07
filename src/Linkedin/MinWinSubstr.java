package Linkedin;
/*
 * Leetcode 76. Minimum Window Substring
 * Given a string S and a string T, find the minimum window in S which will contain
 *  all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one 
unique minimum window in S.
 */
public class MinWinSubstr {
    public String minWindow(String s, String t) {
        int map[] = new int[128];
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i)] ++;
        }
        int left = 0;
        int right = 0;
        int len = Integer.MAX_VALUE;
        int start = 0;
        int counter = t.length();
        while(right < s.length()){
            if(map[s.charAt(right++)]-->0) counter--; //in t
            while(counter==0){ //valid
                if(right-left<len)  
                    len=right -(start=left);
                if(map[s.charAt(left++)]++==0) counter++;  //make it invalid
            }  
        }


        return len == Integer.MAX_VALUE ? "":s.substring(start, start+len); 
    }
    public static void main(String args[]) {
        MinWinSubstr m = new MinWinSubstr();
        System.out.println(m.minWindow("a", "a"));
    }
}
