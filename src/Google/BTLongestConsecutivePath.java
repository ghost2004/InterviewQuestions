package Google;

import Common.TreeNode;
public class BTLongestConsecutivePath {
    
    /*
     * Leetcode 298 Binary Tree Longest Consecutive Sequence 
     * 
     * Given a binary tree, find the length of the longest consecutive sequence path.

        The path refers to any sequence of nodes from some starting node to any node in 
        the tree along the parent-child connections. The longest consecutive path need 
        to be from parent to child (cannot be the reverse).
        
        For example,
        
           1
            \
             3
            / \
           2   4
                \
                 5 
        Longest consecutive sequence path is 3-4-5, so return 3.
        
           2
            \
             3
            / 
           2    
          / 
         1 
        Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
     */
    private int dfs(TreeNode node, int curLen, int prev) {
        if (node == null)
            return curLen;
        int cur = node.val == prev + 1 ?  curLen+1:1;
        int left = dfs(node.left, cur, node.val);
        int right = dfs(node.right, cur, node.val);
        
        return Math.max(cur, Math.max(left, right));
    }
    public int longestConsecutive(TreeNode root) {
        if (root == null)
            return 0;
        return dfs(root, 0, root.val-1);
    }
    
    /*
     * [LeetCode 549] Binary Tree Longest Consecutive Sequence 
     * Given a binary tree, you need to find the length of Longest Consecutive Path 
     * in Binary Tree.

    Especially, this path can be either increasing or decreasing. For example, 
    [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] 
    is not valid. On the other hand, the path can be in the child-Parent-child 
    order, where not necessarily be parent-child order.

    Example 1:

    Input:
            1
           / \
          2   3
    Output: 2
    Explanation: The longest consecutive path is [1, 2] or [2, 1].
     

    Example 2:

    Input:
            2
           / \
          1   3
    Output: 3
    Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
     

    Note: All the values of tree nodes are in the range of [-1e7, 1e7].
     */
    
    public int longestConsecutiveII(TreeNode root) {
        if (root == null)
            return 0;
        return dfs(root, 0, root.val-1);
    }

}
