package Linkedin;

import java.util.*;
public class Subset {
    /*
     * Leetcode 78. Subsets
     * Given a set of distinct integers, nums, return all possible subsets.

    Note: The solution set must not contain duplicate subsets.

    For example,
    If nums = [1,2,3], a solution is:

    [
      [3],
      [1],
      [2],
      [1,2,3],
      [1,3],
      [2,3],
      [1,2],
      []
    ]
     */
    private void bp_subset(List<List<Integer>> result, List<Integer> buf, int nums[], int idx ) {
        result.add(new ArrayList<>(buf));
        for (int i = idx; i < nums.length; i++) {
            buf.add(nums[i]);
            bp_subset(result, buf, nums, i+1);
            buf.remove(buf.size()-1);
        }
    }
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> buf = new ArrayList<>();
        Arrays.sort(nums);
        bp_subset(result, buf, nums, 0);
        return result;
    }
    /*
     * Leetcode 90. Subsets II
     * Given a collection of integers that might contain duplicates, nums, return all possible subsets.

        Note: The solution set must not contain duplicate subsets.
        
        For example,
        If nums = [1,2,2], a solution is:
        
        [
          [2],
          [1],
          [1,2,2],
          [2,2],
          [1,2],
          []
        ]
     */
    
    private void dp_dupset(List<List<Integer>> result, List<Integer> buf, int nums[], int idx) {
        result.add(new ArrayList<>(buf));
        for (int i = idx; i < nums.length; i++) {
            if (i > idx && nums[i] == nums[i-1])
                continue;
            buf.add(nums[i]);
            dp_dupset(result, buf, nums, i+1);
            buf.remove(buf.size()-1);
        }
    }
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> buf = new ArrayList<>();
        Arrays.sort(nums);
        dp_dupset(result, buf, nums,0);
        return result;
    }
}
