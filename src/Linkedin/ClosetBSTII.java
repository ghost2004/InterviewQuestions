package Linkedin;
/*
 * Leetcode 272 Closest Binary Search Tree Value II
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: 
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 

Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:

1. Consider implement these two helper functions:
getPredecessor(N), which returns the next smaller node to N.
getSuccessor(N), which returns the next larger node to N.
2. Try to assume that each node has a parent pointer, it makes the problem much easier.
3. Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
4. You would need two stacks to track the path in finding predecessor and successor node separately.
 */
import java.util.*;

import Common.TreeNode;
public class ClosetBSTII {
    
    /*
     * Priority Queue solution
     * 
     * Travel the BST inorder and keep the top K elements
     */
    private void inorder(PriorityQueue<Integer> pq, TreeNode node, int k) {
        if (node == null)
            return;
        inorder(pq, node.left, k);
        pq.offer(node.val);
        if (pq.size() > k)
            pq.poll();
        
        inorder(pq, node.right, k);
    }
    
    public List<Integer> closestKValues2(TreeNode root, int target, int k) {
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
    
    
    /*
     * 2 Stacks solution
     * One stack keep the nodes less than target
     * One stack keep the nodes greater than target
     */
    public List<Integer> closestKValues(TreeNode root, int target, int k) {
        List<Integer> result =  new LinkedList<>();
        // Nodes less than target
        Stack<TreeNode> pre = new Stack<>();
        // Nodes greater than target
        Stack<TreeNode> suc = new Stack<>();
        TreeNode node = root;
        
        // search the target in BST
        // keep the search path in these 2 stacks
        while (node != null) {
            if (node.val <= target) {
                pre.push(node);
                node = node.right;
            } else {
                suc.push(node);
                node = node.left;
            }
        }
        
        
        for (int idx = k; idx > 0; idx--) {
            if (suc.isEmpty() || (!pre.isEmpty() && target - pre.peek().val < suc.peek().val - target)) {
                // choose elements in Predecessor stack
                node = pre.pop();
                result.add(node.val);
                if (node.left != null) {
                    // the left subtree should be  less than target
                    node = node.left;
                    pre.push(node);
                    // push right (which is closer to target) children to Predecessor
                    while (node.right != null) {
                        node = node.right;
                        pre.push(node);
                    }
                }
                
            } else {
                // choose elements in successors stack
                node = suc.pop();
                result.add(node.val);
                if (node.right != null) {
                    // the right subtree should  be greater than target
                    node = node.right;
                    suc.push(node);
                    while(node.left != null) {
                        // push left (which is closer to target) children to successors
                        node = node.left;
                        suc.push(node);
                    }
                }
            }
        }
        
        
        return result;
    }
    
    public static void main(String args[]) {
        TreeNode t = TreeNode.deserialize("9,4,3,#,#,6,5,#,#,7,#,#,17,#,22,20,#");
        ClosetBSTII b = new ClosetBSTII();
        t.printTree();
        System.out.println();
        System.out.println(b.closestKValues(t, 15, 4));
    }
}
