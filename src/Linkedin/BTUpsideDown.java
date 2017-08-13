package Linkedin;
/*
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling 
 * (a left node that shares the same parent node) or empty, flip it upside down and turn 
 * it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
   1
  / \
 2   3
/ \
4  5

return the root of the binary tree [4,5,2,#,#,3,1].
 4
/ \
5  2
  / \
 3   1
 */
import Common.TreeNode;
public class BTUpsideDown {
    public TreeNode UpsideDownBinaryTree(TreeNode root) { 
        if (root == null || root.left == null)
            return root;
        TreeNode  newRoot = UpsideDownBinaryTree(root.left);
        
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        
        return newRoot;
                    
    }
    
    public static void main(String args[]) {
        TreeNode t = TreeNode.deserialize("1,2,4,#,#,5,#,#,3");
        t.printTree();
        BTUpsideDown b = new BTUpsideDown();
        t = b.UpsideDownBinaryTree(t);
        t.printTree();
    }

}
