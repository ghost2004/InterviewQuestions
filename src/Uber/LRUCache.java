package Uber;
/*
 * Design and implement a data structure for Least Recently Used (LRU) cache. 
 * It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists 
in the cache, otherwise return -1.

set(key, value) - Set or insert the value if the key is not already present. When 
the cache reached its capacity, it should invalidate the least recently used item 
before inserting a new item.

 */
import java.util.*;
public class LRUCache {
    public class LRUNode {
        public int key;
        public int value;
        public LRUNode prev;
        public LRUNode next;
        
        public LRUNode(int k, int v) {
            key = k;
            value = v;
            
        }
    }
    
    private HashMap<Integer, LRUNode> map;
    private int cap;
    private LRUNode head;
    private LRUNode tail;
    
    public LRUCache(int capacity) {
        cap = capacity;
        map = new HashMap<Integer, LRUNode>();
        head = null;
        tail = null;
        
    }
    
    private void removeNode(LRUNode node) {
        if (node.prev == null) 
            head = node.next;
        else 
            node.prev.next = node.next;
        
        
        if (node.next != null)
            node.next.prev = node.prev;
        else
            tail = node.prev;
    }
    
    private void insertHead(LRUNode node) {

        
        node.prev = null;
        node.next = head;
        
        if (head != null)
            head.prev = node;
            
        head = node;
        
        if (tail == null)
            tail = head;
        
    }
    
    
    public int get(int key) {
        LRUNode node = map.get(key);
        if (node == null)
            return -1;
        if (head == node)
            return node.value;

        removeNode(node);
        insertHead(node); 
        
        return node.value;
    }
    
    public void set(int key, int value) {
        LRUNode node = map.get(key);
        if (node == null) {
            node = new LRUNode(key, value);
            if (map.size() == cap) {
                int removeKey = tail.key;
                map.remove(removeKey);
                removeNode(tail);
            }
            map.put(key, node);
            insertHead(node);
        } else {
            node.value = value;
            if (head == node)
                return;
            removeNode(node);
            insertHead(node); 
        }
    }
}
