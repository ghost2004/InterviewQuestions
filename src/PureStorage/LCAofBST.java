package PureStorage;
/*
 * Given a binary search tree (BST), find the lowest common ancestor (LCA)
 *  of two given nodes in the BST.

According to the definition of LCA on Wikipedia: ¡°The lowest common ancestor 
is defined between two nodes v and w as the lowest node in T that has both
 v and w as descendants (where we allow a node to be a descendant of itself).¡±

        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another
 example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of 
 itself according to the LCA definition.
 */
import Common.TreeNode;
public class LCAofBST {
    // recursive solution
    public TreeNode findLCA(TreeNode node, int min, int max) {
        if (node == null)
            return null;
        if (node.val > min && node.val < max) 
            return node;
        
        if (node.val == min || node.val == max)
            return node;
        
        if (node.val > max)
            return findLCA(node.left, min, max);
        
        return findLCA(node.right, min, max);
        
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        int min,max;
        if (p.val > q.val) {
            min = q.val;
            max = p.val;
        } else {
            min = p.val;
            max = q.val;
        }
        
        return findLCA(root, min, max);
    }
    
    // iterative solution
    public TreeNode lcaFind(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        TreeNode cur = root;
        int min,max;
        if (p.val > q.val) {
            min = q.val;
            max = p.val;
        } else {
            min = p.val;
            max = q.val;
        }
        
        while (cur != null) {
            if (cur.val > min && cur.val < max)
                return cur;
            if (cur.val == min || cur.val ==max)
                 return cur;
            if (cur.val < min)
                cur = cur.right;
            else 
                cur = cur.left;
        }
        
        return cur;
                    
    }
    
}
