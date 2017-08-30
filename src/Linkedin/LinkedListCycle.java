package Linkedin;
/*
 * Leetcode 142. Linked List Cycle II
 *  Given a linked list, return the node where the cycle begins. 
 *  If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?
 */
import Common.ListNode;
public class LinkedListCycle {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast != slow) {
            if (fast.next == null || fast.next.next == null)
                return null;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        slow = head;
        while (slow != fast.next) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
}
