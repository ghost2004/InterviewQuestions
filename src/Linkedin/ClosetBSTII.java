package Linkedin;
/*
 * Leetcode Closest Binary Search Tree Value II
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 

Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:

1. Consider implement these two helper functions:
　　i. getPredecessor(N), which returns the next smaller node to N.
　　ii. getSuccessor(N), which returns the next larger node to N.
2. Try to assume that each node has a parent pointer, it makes the problem much easier.
3. Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
4. You would need two stacks to track the path in finding predecessor and successor node separately.
 */
import java.util.*;
import Common.TreeNode;
public class ClosetBSTII {
    
    private void inorder(PriorityQueue<Integer> pq, TreeNode node, int k) {
        if (node == null)
            return;
        inorder(pq, node.left, k);
        pq.offer(node.val);
        if (pq.size() > k)
            pq.poll();
        
        inorder(pq, node.right, k);
    }
    
    public List<Integer> closestKValues(TreeNode root, int target, int k) {
        List<Integer> result =  new ArrayList<>(k);
        PriorityQueue<Integer> pq  = new PriorityQueue<>(k, new Comparator<Integer>() {
            public int compare(Integer a1, Integer a2) {
                int diff1 = Math.abs(target - a1);
                int diff2 = Math.abs(target - a2);
                return diff2-diff1;
            }
        }) ;
        inorder(pq, root, k);
        while (!pq.isEmpty()){
            result.add(pq.poll());
        }
        
        return result;
    }
}
