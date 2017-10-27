package Google;
/*
 * LeetCode 616. Add Bold Tag in a String
 * Given a string s and a list of strings dict, you need to add a closed pair of bold tag 
 * <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings 
 * overlap, you need to wrap them together by only one pair of closed bold tag. Also, 
 * if two substrings wrapped by bold tags are consecutive, you need to combine them.
    Example 1:
    Input: 
    s = "abcxyz123"
    dict = ["abc","123"]
    Output:
    "<b>abc</b>xyz<b>123</b>"
    Example 2:
    Input: 
    s = "aaabbcc"
    dict = ["aaa","aab","bc"]
    Output:
    "<b>aaabbc</b>c"
    Note:
    The given dict won't contain duplicates, and its length won't exceed 100.
    All the strings in input have length in range [1, 1000].
 */
public class BoldTagString {
    public String addBoldTag(String s, String[] dict) {
        boolean tag[] = new boolean[s.length()];
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            for (String k:dict) {
                if (s.startsWith(k, i))
                    end = Math.max(end, i+k.length());
            }
            tag[i] = i < end;
        }
        
        StringBuilder result = new StringBuilder();
        
        int idx = 0;
        
        while (idx < s.length()) {
            if (!tag[idx]) {
                result.append(s.charAt(idx++));
                
            } else {
                int prev = idx;
                while (idx < s.length() && tag[idx])
                    idx++;
                result.append("<b>");
                result.append(s.substring(prev, idx));
                result.append("</b>");
            }
            
        }
        
        return result.toString();
    }
    
    public static void main(String args[]) {
        BoldTagString b = new BoldTagString();
        String s1 = "abcxyz123";
        String dict1[] = {"abc","123"};
        String s2 = "aaabbcc";
        String dict2[] = {"aaa","aab","bc"};
        
        System.out.println(b.addBoldTag(s1, dict1));
        System.out.println(b.addBoldTag(s2, dict2));
        
    }
}
