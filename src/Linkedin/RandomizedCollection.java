package Linkedin;
/*
 * Leetcode 381. Insert Delete GetRandom O(1) - Duplicates allowed
 * Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. The probability of 
each element being returned is linearly related to the number of same value the collection 
contains.
 */
import java.util.*;
public class RandomizedCollection {
    ArrayList<Integer> list;
    HashMap<Integer, Set<Integer>> map;
    Random rand;
    
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean out = false;
        Set<Integer> set = map.get(val);
        
        if (set == null) {
            set = new LinkedHashSet<Integer>();
            map.put(val, set);
        } else
            out = true;
        set.add(list.size());
        list.add(val);
        return out;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        Set<Integer> set = map.get(val);
        if (set == null)
            return false;
        int idx = set.iterator().next();
        set.remove(idx);
        int last = list.get(list.size()-1);
        if (idx != list.size()-1) {
            
            list.set(idx, last);
            Set<Integer> set2 = map.get(last);
            set2.remove(list.size()-1);
            set2.add(idx);
            
        }
        list.remove(list.size()-1);
        if (set.isEmpty())
            map.remove(val);
            
        return true;
        
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
