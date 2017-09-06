package Linkedin;
/*
 * Leetcode 460. LFU Cache
 * Design and implement a data structure for Least Frequently Used (LFU) cache. 
 * It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in 
the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When 
the cache reaches its capacity, it should invalidate the least frequently used item 
before inserting a new item. For the purpose of this problem, when there is a tie 
(i.e., two or more keys that have the same frequency), the least recently used key 
would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2 );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
 */
import java.util.*;
public class LFUCache {
    class LFUNode {
        public int count;
        public LinkedHashSet<Integer> keys;
        public LFUNode prev, next;
        public LFUNode(int n){
            this.count = n;
            keys = new LinkedHashSet<>();
            prev = null;
            next = null;
        }
    }
    
    private LFUNode head;
    private int cap;
    private HashMap<Integer, Integer> valueMap;
    private HashMap<Integer, LFUNode> nodeMap;
    
    
    public LFUCache(int capacity) {
        this.cap = capacity;
        valueMap = new HashMap<>();
        nodeMap = new HashMap<>();
        head = null;
    }
    private void removeNode(LFUNode node) {
        if (node.prev == null) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        } 
        if (node.next != null) {
            node.next.prev = node.prev;
        } 
    }
    
    private void increaseCount(int key) {
        LFUNode node = nodeMap.get(key);
        node.keys.remove(key);
        
        if (node.next == null || node.next.count != node.count+1) {
            LFUNode n = node.next;
            node.next = new LFUNode(node.count+1);
            node.next.keys.add(key);
            node.next.prev = node;
            node.next.next = n;
            if (n != null) {
                n.prev = node.next;
            }
        } else {
            node.next.keys.add(key);
        }
        
        nodeMap.put(key, node.next);
        if (node.keys.isEmpty()) 
            removeNode(node);
        
    }
    
    public int get(int key) {
        Integer cnt = valueMap.get(key);
        
        if (cnt == null)
            return -1;
        increaseCount(key);
        
        return cnt;
        
    }
    private void evicts() {
        if (head == null) 
            return;
        Integer candidate = head.keys.iterator().next();
        
        head.keys.remove(candidate);
        if (head.keys.isEmpty())
            removeNode(head);
        valueMap.remove(candidate);
        nodeMap.remove(candidate);
    }
    
    private void addHead(int key) {
        if (head == null || head.count != 1) {
            LFUNode node = new LFUNode(1);
            node.keys.add(key);
            node.next = head;
            if (head != null)
                head.prev = node;
            head = node;
        } else {
            head.keys.add(key);
        }
        
        nodeMap.put(key, head);
    }
    
    
    public void put(int key, int value) {
        if (cap == 0)
            return;
        if (valueMap.containsKey(key)) {
            valueMap.put(key, value);
            increaseCount(key);
        } else {
            if (valueMap.size() >= cap) {
                evicts();
            } 
            valueMap.put(key, value);
            addHead(key);
        }
        
    }
    
    public static void main(String args[]) {
        LFUCache f = new LFUCache(1);
        f.put(2, 1);
        System.out.println(f.get(2));
        f.put(3, 2);
        System.out.println(f.get(3));
        System.out.println(f.get(2));
        
        
    }

}
