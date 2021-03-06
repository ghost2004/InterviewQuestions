package Linkedin;
/*
 * Leetcode 270  Closest Binary Search Tree Value
 * Given a non-empty binary search tree and a target value, find the value 
 * in the BST that is closest to the target
 * 
 * 
 */

import Common.TreeNode;
public class ClosestBST {

    public int closestValue(TreeNode root, int target) {
        if (root.val == target)
            return target;
        TreeNode child = root.val > target ? root.left:root.right;
        if (child == null)
            return root.val;
        int candidate = closestValue(child, target);
        
        if (Math.abs(target - root.val) < Math.abs(target - candidate))
            return root.val;
        return candidate;
    }
    
    public static void main(String args[]) {
        ClosestBST b = new ClosestBST();
        TreeNode t = TreeNode.deserialize("9,4,3,#,#,6,5,#,#,7,#,#,17,#,22,20,#");
        t.printTree();
        System.out.println(b.closestValue(t, 18));
    }
}
