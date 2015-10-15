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
        //linked list for candidates
        LinkedList<WordNode> queue = new LinkedList<WordNode>();
        // visited nodes
        HashSet<String> visited = new HashSet<String>();
        // unvisited nodes
        HashSet<String> unvisited = new HashSet<String>();
        
        // at beginning, no word is visited
        unvisited.addAll(wordList);
        unvisited.add(endWord);
        
        // put the first word into queue, step is 1 and no previous node
        queue.offer(new WordNode(beginWord, 1, null));
        int minStep = 0;
        int preStep = 0;
        while (!queue.isEmpty()) {
            // get word node from queue
            WordNode n = queue.poll();
            String key = n.word;
            // check if we reach the end
            if (key.equals(endWord)) {
                // set min step if it was not set before
                if (minStep == 0)
                    minStep = n.step;
                if (n.step <= minStep) {
                    // get the min step
                    if (n.step < minStep) {
                        // clear the result if this one is shorter than older one
                        minStep = n.step;
                        result.clear();
                    }
                    // reverse the linked list and put into result
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
            
            // only remove the node from visited when we get less steps
            if (preStep < n.step) {
                unvisited.removeAll(visited);
            }
            // update previous step
            preStep = n.step;
            char array[] = key.toCharArray();
            // change the possible characters in the string
            for (int i = 0; i < array.length; i++) {
                for (char a = 'a'; a <= 'z';a++) {
                    char temp = array[i];
                    if(array[i]!=a){
                        array[i]=a;
                    }

                    String next = new String(array);
                    if (unvisited.contains(next)) {
                        // if it is not visited yet, put it into queue
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
