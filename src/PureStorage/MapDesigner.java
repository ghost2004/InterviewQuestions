package PureStorage;
/*
 * Design a Map<Integer, Integer>
 * 
 * Match following requirement
 * 
 * add: O(1)  deletion: O(1)  lookup: O(1)  clear:O(1)  iterate: O(number of elements)¡£
 * 
 * Assume the memory is big enough and there is no collision in the map
 */
import java.util.*;
public class MapDesigner {
    private class MapNode{
        public Integer value;
        public Integer version;
    }
    private HashMap<Integer, MapNode> map;
    private Integer version;
    
    public MapDesigner() {
        this.map = new HashMap<Integer, MapNode>();
        this.version = 1;
    }
    
    public void put(Integer key, Integer value) {
        MapNode node = map.get(key);
        if (node == null) {
            node = new MapNode();
        }
        node.value = value;
        node.version = this.version;
        map.put(key, node);
    }
    
    public Integer get(Integer key) {
        MapNode node = map.get(key);
        if (node == null)
            return null;
        if (node.version != this.version)
            return null;
        
        return node.value;
    }
    
    public void clear() {
        this.version++;
    }
    
    public void remove(Integer key) {
        map.remove(key);
    }
   
}
