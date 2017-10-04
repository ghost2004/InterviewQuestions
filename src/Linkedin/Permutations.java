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
    
    private void permS(int nums[], int idx, List<List<Integer>> result) {
        if (idx >= nums.length) {
            List<Integer> t = new ArrayList<>();
            for (int i:nums)
                t.add(i);
            result.add(t);
            return;
        }
        
        for (int i = idx; i < nums.length; i++) {
            swap(nums, idx, i);
            permS(nums, idx+1, result);
            swap(nums, idx, i);
        }
    }
    private void swap(int a[], int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permS(nums, 0, result);
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
