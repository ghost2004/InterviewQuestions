package Facebook;

import Common.TreeNode;

/*
 * Leetcode 333 Largest BST Subtree
 * 
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:
    10
    / \
   5  15
  / \   \ 
 1   8   7
The Largest BST Subtree in this case is the highlighted one. 
The return value is the subtree's size, which is 3.
Hint:

You can recursively use algorithm similar to 98. Validate Binary Search Tree at each node of the tree, which will result in O(nlogn) time complexity.
Follow up:
Can you figure out ways to solve it with O(n) time complexity?
 */
public class LargestBSTSubtree {
    private class BTState {
        int lowerBound;
        int upperBound;
        boolean isBST;
        int size;
        
        public BTState() {
            lowerBound = Integer.MIN_VALUE;
            upperBound = Integer.MAX_VALUE;
            isBST = true;
            size = 0;
        }
    }
    int max = 0;
    
    public int largestBSTSubtree(TreeNode root) {
        dfs(root);
        return max;
    }
    
    private BTState dfs(TreeNode node) {
        BTState result = new BTState();
        if (node == null)
            return result;
        BTState left = dfs(node.left);
        BTState right = dfs(node.right);
        result.lowerBound = Math.min(left.lowerBound, node.val);
        result.upperBound = Math.max(right.upperBound, node.val);
        
        if (left.isBST && right.isBST &&node.val >= left.upperBound && node.val <= right.lowerBound) {
            result.size = left.size + right.size + 1;
            max = Math.max(max, result.size);
        } else {
            result.isBST = false;
        }
        
        return result;
    }

}
