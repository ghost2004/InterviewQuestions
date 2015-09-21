package Uber;
/*
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of 
 * shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lower case alphabetic characters.
 */
import java.util.*;
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
                
        LinkedList<String> queue = new LinkedList<String>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        queue.offer(beginWord);
        map.put(beginWord, 1);
        int length = beginWord.length();
        
        while (!queue.isEmpty()) {
            String candidate = queue.poll();
            int steps =  map.get(candidate)+1;
            char buf[] = candidate.toCharArray();
            
            for (int i = 0; i < length; i++) {
                
                for (char c = 'a'; c <= 'z'; c++) {
                    if (buf[i] == c)
                        continue;
                    buf[i] = c;
                    String key = new String(buf);
                    if (key.equals(endWord)) {
                        return steps;
                    }
                    if (!map.containsKey(key) && wordList.contains(key)) {
                        queue.offer(key);
                        map.put(key, steps);
                    }
                    buf[i] = candidate.charAt(i);
                }
            }
        }
        
        return 0;
    }
}
