package Linkedin;
/*
 * Leetcode 127. Word Ladder
 * Given two words (beginWord and endWord), and a dictionary's word list, 
 * find the length of shortest transformation sequence from beginWord to 
 * endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord 
is not a transformed word.
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
 */
import java.util.*;
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord))
            return 0;
        
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> visited = new HashSet<>();
        begin.add(beginWord);
        end.add(endWord);
        
        int len = 1;
        while (!begin.isEmpty() && !end.isEmpty()) {
            if (begin.size() > end.size()) {
                Set<String> tmp = begin;
                begin = end;
                end = tmp;
            }
            
            Set<String> set = new HashSet<>();
            for (String s:begin) {
                char arr[] = s.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    char cur = arr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == cur)
                            continue;
                        arr[i] = c;
                        String t = new String(arr);
                        if (end.contains(t))
                            return len+1;
                        if (dict.contains(t) && !visited.contains(t)) {
                            set.add(t);
                            visited.add(t);
                        }
                        arr[i] = cur;
                    }
                }
            }
            begin = set;
            len++;
            
        }
        
        return 0;
    }
    
    public static void main(String args[]) {
        WordLadder w = new WordLadder();
        String words[] = {"hot","dot","dog","lot","log"};
        List<String> list = Arrays.asList(words);
        System.out.println(w.ladderLength("hit", "cog", list));
    }

}
