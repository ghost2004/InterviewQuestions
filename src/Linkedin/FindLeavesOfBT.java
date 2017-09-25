package Linkedin;
/*
 * Leetcode 366 Find Leaves of Binary Tree 
 * Given a binary tree, find all leaves and then remove those leaves. Then repeat 
 * the previous steps until the tree is empty.

Example:
Given binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Returns [4, 5, 3], [2], [1].

Explanation:
1. Remove the leaves [4, 5, 3] from the tree

          1
         / 
        2          
2. Remove the leaf [2] from the tree

          1          
3. Remove the leaf [1] from the tree

          []         
Returns [4, 5, 3], [2], [1].
 */
import java.util.*;
import Common.TreeNode;
public class FindLeavesOfBT {
    private int height(TreeNode node, List<List<Integer>> result) {
        if (node == null)
            return -1;
        int height = 1 + Math.max(height(node.left, result), height(node.right, result));
        
        if (height >= result.size())
            result.add(new ArrayList<>());
        
        result.get(height).add(node.val);
        
        return height;
    }
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        height(root, result);
        return result;
    }
    
    public static void main(String args[]) {
        TreeNode root = TreeNode.deserialize("1,2,4,#,#,5,#,#,3");
        List<List<Integer>> r = new FindLeavesOfBT().findLeaves(root);
        for (List<Integer> list:r) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

}
