package Linkedin;
/*
 * Leetcode 109 Convert Sorted List to Binary Search Tree

 * Given a singly linked list where elements are sorted in ascending order, 
 * convert it to a height balanced BST.
 */
import Common.*;
public class ConvertList2BST {

    private TreeNode convertBST(ListNode head, ListNode tail) {
        if (head == tail)
            return null;
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        
        root.left = convertBST(head, slow);
        root.right = convertBST(slow.next, tail);
        
        return root;
    }
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        return convertBST(head, null);
    }
}
