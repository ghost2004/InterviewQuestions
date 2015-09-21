package Uber;
/*
 * Design a map , implement following methods
 * 
 * put(key,value,time)  -- put a pair into map with time stamp
 * get(key, time)  -- get the value before that time
 * 
 */
import java.util.*;
public class MapWithTime {
    private HashMap<Integer, TreeMap<Integer, Integer>> entryMap;
    public MapWithTime() {
        entryMap = new HashMap<Integer, TreeMap<Integer, Integer>> ();
    }
    
    public void put(int key, int value, int time) {
        TreeMap<Integer,Integer> map = entryMap.get(key);
        if (map == null) {
            map = new TreeMap<Integer, Integer>();
            entryMap.put(time, map);
        }
        map.put(time, value);
    }
    
    public Integer get(int key, int time) {
        TreeMap<Integer,Integer> map = entryMap.get(key);
        if (map == null)
            return null;
        Map.Entry<Integer, Integer> entry =  map.lowerEntry(time);
        if (entry == null)
            return null;
        return entry.getValue();
    }

}
