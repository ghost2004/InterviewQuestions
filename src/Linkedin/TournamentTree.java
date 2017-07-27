package Linkedin;
/*
* A tournament tree is a binary tree 
* where the parent is the minimum of the two children. 
* Given a tournament tree find the second minimum value in the tree. 
* A node in the tree will always have 2 or 0 children. 
* Also all leaves will have distinct and unique values. 
*         2 
*      /    \ 
*     2      3 
*    / \    /  \    
*   4   2   5   3 
* 
* In this given tree the answer is 3. 
 */

import Common.TreeNode;
public class TournamentTree {
    public int secondMin(TreeNode root) {
        int min = 0;
        
        if (root.left == null || root.right == null)
            return root.val;
        TreeNode node;
        if (root.left.val == root.val) {
            min = root.right.val;
            node = root.left;
        } else {
            min = root.left.val;
            node = root.right;
        }
        
        return Math.min(min, secondMin(node));
    }
}
