package Linkedin;
/*
 * Leetcode 244 Shortest Word Distance II
 * 
 * This is a follow up of Shortest Word Distance. The only difference is now you are given 
 * the list of words and your method will be called repeatedly many times with different 
 * parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method 
that takes two words word1 and word2 and return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 ="coding", word2 = "practice", return 3.
Given word1 = "makes", word2 = "coding", return 1.
 */
import java.util.*;
public class WordDistance {
    private HashMap<String, ArrayList<Integer>> map;
    public WordDistance(String words[]) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            ArrayList<Integer> list = map.get(words[i]);
            if (list == null) {
                list = new ArrayList<>();
                list.add(i);
                map.put(words[i], list);
            } else
                list.add(i);
        }
    }
    
    public int shorest(String word1, String word2) {
        int dist = Integer.MAX_VALUE;
        ArrayList<Integer> list1 = map.get(word1);
        ArrayList<Integer> list2 = map.get(word2);
        int idx1 = 0;
        int idx2 = 0;
        
        while (idx1 < list1.size() && idx2 < list2.size()) {
            dist = Math.min(dist, Math.abs(list1.get(idx1) - list2.get(idx2)));
            if (idx1 < idx2)
                idx1++;
            else
                idx2++;
        }
        
        return dist;
    }
}
