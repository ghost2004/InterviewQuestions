package Linkedin;
/*
 * Start with a standard doubly-linked list. Now imagine that in addition to next and 
 * previous pointers, each element has a child pointer, which may or may not point to 
 * a separate doubly-linked list. These child lists may have one or more children of 
 * their own, and so on, to produce a multilevel data structure
 * 
 * Flatten the list so that all the nodes appear in a single-level, doubly-linked list. 
 * You are given the head of the first level in the list.
 */
import Common.MultiLevelNode;
public class FlattenLevelList {
    MultiLevelNode flattenList(MultiLevelNode head) {
        if (head == null)
            return null;
        MultiLevelNode tail = head;
        // get the tail of first level
        while (tail.next != null) {
            tail = tail.next;
        }
        MultiLevelNode cur = head;
        
        while (cur != null) {
            if (cur.down != null) {
                MultiLevelNode child = cur.down;
                while (child != null) {
                    tail.next = child;
                    tail = tail.next;
                    child = child.next;
                }
            }
            cur = cur.next;
        }
        
        
        return head;
    }

    
}
