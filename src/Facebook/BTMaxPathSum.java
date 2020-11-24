package Facebook;

import Common.TreeNode;

/*
 * Leetcode 124. Binary Tree Maximum Path Sum
 * Given a binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node
 in the tree along the parent-child connections. The path does not need to go through the root.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
 */
public class BTMaxPathSum {
    private int max;
    public int getMax(TreeNode node) {
        if (node == null) 
            return 0;
        int left = Math.max(0, getMax(node.left));
        int right = Math.max(0, getMax(node.right));
        
        int localMax = left + right + node.val;
        int exportMax = Math.max(left, right) + node.val;
        
        max = Math.max(localMax, max);
        max = Math.max(exportMax, max);
        return exportMax;
    }
    
    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        getMax(root);
        return max;
    }
}
