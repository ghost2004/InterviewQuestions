package Uber;
/*
 * find  the frequency of all words in the string
 * print it out in order
 *
input:
cat bat man bat cat

output:
man: 1
bat: 2
cat: 2 

 */

import java.util.*;
public class StringFreq {
    
    static final Comparator<Map.Entry<String, Integer>> entry_compare = new Comparator<Map.Entry<String, Integer>>() {
        public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
            if (a.getValue() != b.getValue())
                return a.getValue() - b.getValue();
            
            return a.getKey().compareTo(b.getKey());
        }
        
    };
    
    public void getFreq(String s) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        StringTokenizer st = new StringTokenizer(s, " ");
        while (st.hasMoreTokens()) {
            String key = st.nextToken();
            Integer val = map.get(key);
            if (val == null) {
                map.put(key,  1);
                
            } else {
                map.put(key, val+1);
            }
        }
        
        TreeSet<Map.Entry<String, Integer>> set = new TreeSet<Map.Entry<String, Integer>>(entry_compare);
        set.addAll(map.entrySet());
        Iterator<Map.Entry<String, Integer>> iter = set.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Integer> entry = iter.next();
            System.out.println(entry.getKey()+": "+entry.getValue());
        }
    }
    
    public static void main(String args[]) {
        StringFreq freq = new StringFreq();
        freq.getFreq("cat bat man bat cat");
    }
}
