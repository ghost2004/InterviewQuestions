package Linkedin;
/*
 * Leetcode 113. Path Sum II
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

        For example:
        Given the below binary tree and sum = 22,
                      5
                     / \
                    4   8
                   /   / \
                  11  13  4
                 /  \    / \
                7    2  5   1
        return
        [
           [5,4,11,2],
           [5,8,4,5]
        ]
 */
import java.util.*;
import Common.TreeNode;
public class PathSumII {
    
    private void dfs(List<List<Integer>> result, ArrayList<Integer> stack, TreeNode node, int sum) {
        if (node == null)
            return;
        if (node.left == null && node.right == null) {
            if (node.val == sum) {
                ArrayList<Integer> list = new ArrayList<>(stack);
                list.add(node.val);
                result.add(list);
            }
            return;
        }

        stack.add(node.val);
        dfs(result, stack, node.left, sum-node.val);
        dfs(result, stack, node.right, sum-node.val);
        
        stack.remove(stack.size()-1);
        
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> stack = new ArrayList<>();
        dfs(result, stack, root, sum);
        return result;
    }

}
