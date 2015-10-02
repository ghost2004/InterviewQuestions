package Linkedin;
/*
 * Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 ¡ú a2
                   ¨K
                     c1 ¡ú c2 ¡ú c3
                   ¨J            
B:     b1 ¡ú b2 ¡ú b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
 */
import Common.ListNode;
public class IntersectionOf2List {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) 
            return null;
        int lenA = 0;
        int lenB = 0;
        ListNode p1 = headA;
        while (p1 != null) {
            lenA++;
            p1 = p1.next;
        }
        ListNode p2 = headB;
        while (p2 != null) {
            lenB++;
            p2 = p2.next;
        }
        p1 = headA;
        p2 = headB;
        
        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB;i++) {
                p1 = p1.next;
            }
        } else {
            for (int i = 0; i < lenB - lenA;i++) {
                p2 = p2.next;
            }
            
        }
        
        while (p1 != null) {
            if (p1 == p2)
                return p1;
            p1 = p1.next;
            p2 = p2.next;
        }
        
        return null;
        
    }

}
