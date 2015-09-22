package Uber;
/*
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest 
 * transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lower case alphabetic characters.
 */
import java.util.*;
public class WordLadderII {
    class WordNode {
        public String word;
        public int step;
        public WordNode prev;
        
        public WordNode(String w, int s, WordNode p) {
            this.word = w;
            this.step =s;
            this.prev = p;
        }
    }
    
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (beginWord.equals(endWord)) {
            List<String> item = new ArrayList<String>();
            item.add(beginWord);
            result.add(item);
            return result;
        }
        LinkedList<WordNode> queue = new LinkedList<WordNode>();
        HashSet<String> visited = new HashSet<String>();
        HashSet<String> unvisited = new HashSet<String>();
        
        unvisited.addAll(wordList);
        unvisited.add(endWord);
        
        queue.offer(new WordNode(beginWord, 1, null));
        int minStep = 0;
        int preStep = 0;
        while (!queue.isEmpty()) {
            WordNode n = queue.poll();
            String key = n.word;
            if (key.equals(endWord)) {
                if (minStep == 0)
                    minStep = n.step;
                if (n.step <= minStep) {
                    if (n.step < minStep) {
                        minStep = n.step;
                        result.clear();
                    }
                    List<String> item = new ArrayList<String>();
                    WordNode cur = n;
                    while (cur != null) {
                        item.add(0, cur.word);
                        cur = cur.prev;
                    }
                    result.add(item);
                }
                continue;
            }
            
            if (preStep < n.step) {
                unvisited.removeAll(visited);
            }
            
            preStep = n.step;
            char array[] = key.toCharArray();
            for (int i = 0; i < array.length; i++) {
                for (char a = 'a'; a <= 'z';a++) {
                    char temp = array[i];
                    if(array[i]!=a){
                        array[i]=a;
                    }

                    String next = new String(array);
                    if (unvisited.contains(next)) {
                        queue.add(new WordNode(next, preStep+1, n));
                        visited.add(next);
                    }
                    array[i] = temp;
                }
            }
            
        }
        
        return result;
    }
    
    public void printLadder(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> list = findLadders(beginWord, endWord, wordList);
        for (int i = 0; i < list.size(); i++) {
            List<String> item = list.get(i);
            for (int j = 0; j < item.size(); j++) {
                System.out.print(item.get(j));
                if (j < item.size()-1)
                    System.out.print("-->");
            }
            System.out.println();
        }
    }
    
    public static void main(String args[]) {
        WordLadderII w = new WordLadderII();
        HashSet<String> dict = new HashSet<String>();
        dict.add("hot");
        dict.add("hog");
        dict.add("dog");
        w.printLadder("hot", "dog", dict);
        
    }
}
