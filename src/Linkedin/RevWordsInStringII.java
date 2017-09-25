package Linkedin;
/*
 * Leetcode 186 Reverse Words in a String II
 * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?
 */
public class RevWordsInStringII {

    
    private void reverse(char s[], int i, int j) {
        int left = i;
        int right = j;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
                
    }
    
    public void reverseWords(char[] s) {
        int idx = 0;
        reverse(s, 0, s.length-1);
        
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                reverse(s, idx, i-1);
                idx = i+1;
            }
        }
        
        reverse(s, idx, s.length-1);
    }
    
    public static void main(String args[]) {
        RevWordsInStringII r = new RevWordsInStringII();
        String a = "the sky is blue";
        char a1[] = a.toCharArray();
        r.reverseWords(a1);
        System.out.println(new String(a1));
        
    }
}
