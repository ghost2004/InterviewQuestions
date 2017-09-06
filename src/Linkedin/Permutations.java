package Linkedin;



import java.util.*;
public class Permutations {
    /*
     * Leetcode 46. Permutations
     * Given a collection of distinct numbers, return all possible permutations.

    For example,
    [1,2,3] have the following permutations:
    [
      [1,2,3],
      [1,3,2],
      [2,1,3],
      [2,3,1],
      [3,1,2],
      [3,2,1]
    ]
     */
    private void perm(List<List<Integer>> result, List<Integer> buf, int nums[], boolean used[]) {
        if (buf.size() == nums.length) {
            result.add(new ArrayList<>(buf));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i])
                continue;
            used[i] = true;
            buf.add(nums[i]);
            perm(result, buf, nums, used);
            buf.remove(buf.size()-1);
            used[i] = false;
            
        }
    }
    
    public List<List<Integer>> permute(int[] nums) {
        boolean used[] = new boolean[nums.length];
        Arrays.fill(used, false);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> buf = new ArrayList<>();
        
        perm(result, buf, nums, used);
        return result;
        
    }
    
    /*
     * Leetcode 47 Permutations II
     * Given a collection of numbers that might contain duplicates, return all possible unique permutations.

    For example,
    [1,1,2] have the following unique permutations:
    [
      [1,1,2],
      [1,2,1],
      [2,1,1]
    ]
     */
    private void permU(List<List<Integer>> result, List<Integer> buf, int nums[], boolean used[]) {
        if (buf.size() == nums.length) {
            result.add(new ArrayList<>(buf));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || ( i > 0 && nums[i] == nums[i-1] && !used[i-1]))
                continue;
            used[i] = true;
            buf.add(nums[i]);
            permU(result, buf, nums,used);
            buf.remove(buf.size()-1);
            used[i] = false;
            
        }
        
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean used[] = new boolean[nums.length];
        Arrays.fill(used, false);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> buf = new ArrayList<>();
        Arrays.sort(nums);
        permU(result, buf, nums, used);
        return result;
    }
    
    

}
