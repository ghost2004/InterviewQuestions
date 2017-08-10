package Linkedin;
/*
 * Given a list of child->parent relationships, build a binary tree out of it. All the element Ids inside 
 * the tree are unique.

Example:

Given the following relationships:

Child Parent IsLeft
15 20 true
19 80 true
17 20 false
16 80 false
80 50 false
50 null false
20 50 true

You should return the following tree:

    50
   /  \
  20   80
 / \   / \
15 17 19 16
Function Signature


 * Represents a pair relation between one parent node and one child node inside a binary tree
 * If the _parent is null, it represents the ROOT node

class Relation {
    int parent;
    int child;
    bool isLeft;
};
 */
import Common.TreeNode;
import java.util.*;
public class BuildBTFromRelationship {
    
    public TreeNode buildTree(Integer child[], Integer parent[],  boolean isLeft[]) {
        TreeNode root = null;
        if (parent == null || parent.length < 1 
                || child == null || child.length != parent.length
                || isLeft == null || isLeft.length != parent.length)
            return root;
        HashMap<Integer, TreeNode> map = new HashMap<>();
        
        for (int i = 0; i < parent.length; i++) {
            TreeNode node  = map.get(child[i]);
            if (node == null) {
                node = new TreeNode(child[i]);
                map.put(child[i], node);
            }
            
            TreeNode pnode;

            if (parent[i] != null) {
                pnode = map.get(parent[i]);
                if (pnode == null) {
                    pnode = new TreeNode(parent[i]);
                    map.put(parent[i], pnode);
                }
                
                if (isLeft[i])
                    pnode.left = node;
                else
                    pnode.right = node;
                
                
            } else 
                root = node;
        }
        return root;
    }
    
    public static void main(String args[]) {
        BuildBTFromRelationship b = new BuildBTFromRelationship();
        Integer child[] =  {15, 19, 17, 16, 80, 50, 20};
        Integer parent[] = {20, 80, 20, 80, 50, null, 50};
        boolean isLeft[] = {true, true, false, false, false, false, true};
        
        TreeNode root = b.buildTree(child, parent, isLeft);
        System.out.println(root.serialize());
    }

}
