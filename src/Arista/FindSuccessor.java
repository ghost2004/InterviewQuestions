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
           else if (key < cur.val) {
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
        if (suc.val < key)
            return null;
        return suc;
    }
    
    public void prtSuc(TreeNode root, int key) {
        TreeNode r = findSuccessor(root, key);
        if (r == null)
            System.out.println("null");
        else 
            System.out.println(r.val);
    }
    
    public static void main(String args[]) {
        FindSuccessor f = new FindSuccessor();
        TreeNode t1 = TreeNode.deserialize("100,50,30,#,#,70,#,#,150,130");
        f.prtSuc(t1, 100);
        f.prtSuc(t1, 50);
        f.prtSuc(t1, 30);
        f.prtSuc(t1, 10);
        f.prtSuc(t1, 150);

    }
}
