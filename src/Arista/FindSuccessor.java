package Arista;
/*
 * Write a function to find the next in-order successor of any given node in a BST. 
 */
import Common.TreeNode;
public class FindSuccessor {
    public TreeNode findSuccessor(TreeNode root, int key) {
        TreeNode cur,suc;
        cur = suc = root;
        while (cur != null) {
           if (cur.val == key)
               break;
           else if (cur.val < key) {
               suc = cur;
               cur = cur.left;
           } else {
               cur = cur.right;
           }
        }
        
        if (cur == null)
            return null;
        if (cur.right != null) {
            TreeNode t = cur.right;
            while (t.left!= null)
                t = t.left;
            return t;
        }
        return suc;
    }
}
