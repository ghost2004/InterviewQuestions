package Linkedin;
/* This class will be given a list of words (such as might be tokenized
* from a paragraph of text), and will provide a method that takes two
* words and returns the shortest distance (in words) between those two
* words in the provided text.
* Example:
*   WordDistanceFinder finder = new WordDistanceFinder(Arrays.asList("the", "quick", "brown", "fox", "quick"));
*   assert(finder.distance("fox","the") == 3);
*   assert(finder.distance("quick", "fox") == 1);

*/
import java.util.*;
public class WordDistanceFinder {
    private HashMap<String, ArrayList<Integer>> map;
    public WordDistanceFinder (List<String> words) {
        // implementation here
        map = new HashMap<String,ArrayList<Integer>>();
        Iterator<String> iter = words.iterator();
        int index = 0;
        while (iter.hasNext()) {
           String key = iter.next();
           ArrayList<Integer> list = map.get(key);
           if (list == null) {
               list = new ArrayList<Integer>();
               map.put(key, list);
           }
           list.add(index);
           index++;
        }
           
    }
    public int distance (String wordOne, String wordTwo) {
        // implementation here
        ArrayList<Integer> listOne = map.get(wordOne);
        if (listOne == null)
            return -1;
        ArrayList<Integer> listTwo = map.get(wordTwo);
        if (listTwo == null)
            return -1;

        
        int idx1 = 0;
        int idx2 = 0;
        int minDiff = Integer.MAX_VALUE;
        
        while (idx1 < listOne.size() && idx2 < listTwo.size()) {
            int diff  = Math.abs(listOne.get(idx1) - listTwo.get(idx2));
            if (diff == 1)
                return 1;
            minDiff = Math.min(minDiff, diff);
            if (listOne.get(idx1)  > listTwo.get(idx2))
                idx2++;
            else 
                idx1++;
        }
        
        return minDiff;
    }
    
    public static void main(String args[]) {
        WordDistanceFinder finder = new WordDistanceFinder(Arrays.asList("the", "quick", "brown", "fox", "quick"));
        System.out.println(finder.distance("fox","the") );
        System.out.println(finder.distance("quick", "fox"));
    }
}
