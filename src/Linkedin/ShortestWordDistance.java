package Linkedin;
/*
 * Leetcode     243 Shortest Word Distance
 * Given a list of words and two words word1 and word2, return the shortest distance 
 * between these two words in the list.

For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = "coding", word2 = "practice", return 3. 
Given word1 = "makes", word2 = "coding", return 1.
 */

public class ShortestWordDistance {
    public int shortest(String words[], String word1, String word2) {
        int idx1 = -1;
        int idx2 = -2;
        int dis = Integer.MAX_VALUE;
        
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1))
                idx1 = i;
            else if (words[i].equals(word2))
                idx2 = i;
            if (idx1 != -1 && idx2 != -1)
                dis = Math.min(dis, Math.abs(idx1-idx2));
        }
        
        return dis;
    }

}
