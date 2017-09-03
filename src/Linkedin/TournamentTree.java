package Linkedin;
/*
 * Leetcode 671. Second Minimum Node In a Binary Tree
Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in 
this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the
smaller value among its two sub-nodes.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' 
value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:
Input: 
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.
Example 2:
Input: 
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.
 */

import Common.TreeNode;
import java.util.*;
public class TournamentTree {
    
    PriorityQueue<Integer> pq;
    public void travel(TreeNode node) {
        if (node == null)
            return;
        pq.offer(node.val);
        travel(node.left);
        travel(node.right);
    }
    public int findSecondMinimumValue_pq(TreeNode root) {
        pq = new PriorityQueue<Integer>();
        travel(root);
        
        while (!pq.isEmpty() && pq.peek() == root.val)
            pq.poll();
        
        if (pq.isEmpty())
            return -1;
        
        return pq.peek();
        
    }
    
    private int secondMin(TreeNode node, int parent) {
        if (node == null)
            return Integer.MAX_VALUE;
        if (node.val != parent)
            return node.val;
        int left = secondMin(node.left, node.val);
        int right = secondMin(node.right, node.val);
        
        return Math.min(left, right);
    }
    
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null)
            return -1;
        int min = secondMin(root, root.val);
        return min == Integer.MAX_VALUE ? -1:min;
    }
    
    /* Failed in leetcode
    public int secondMin(TreeNode root) {
        int min = 0;
        
        if (root.left == null || root.right == null)
            return root.val;
        
        if (root.left.val == root.right.val)
            return -1;
        TreeNode node;
        if (root.left.val == root.val) {
            min = root.right.val;
            node = root.left;
        } else {
            min = root.left.val;
            node = root.right;
        }
        
        int c = secondMin(node);
        
        if (c == -1)
            return node.val;
        
        return Math.min(min, c);
    }
    */
}
