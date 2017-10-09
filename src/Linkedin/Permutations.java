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
    
    // in space swap solution
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
    
    /*
     * Leetcode 31. Next Permutation
     * Implement next permutation, which rearranges numbers into the lexicographically 
     * next greater permutation of numbers.

        If such arrangement is not possible, it must rearrange it as the lowest possible 
        order (ie, sorted in ascending order).
        
        The replacement must be in-place, do not allocate extra memory.
        
        Here are some examples. Inputs are in the left-hand column and its corresponding 
        outputs are in the right-hand column.
        1,2,3 -> 1,3,2
        3,2,1 -> 1,2,3
        1,1,5 -> 1,5,1
     */
    private void reverse(int nums[], int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
        
    }
    
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2)
            return;
        int len = nums.length;
        // scan the array from right to left, find the first Descending number 
        int firstDescending = len-2;
        
        while (firstDescending >= 0 && nums[firstDescending] >= nums[firstDescending+1])
            firstDescending--;
        // if first Descending number is -1, 
        // it means the whole array is sorted in Descending order
        // like 4,3,2,1,  so the next should be 1,2,3,4
        // reverse the whole array
        if (firstDescending == -1) {
            reverse(nums, 0, len-1);
            return;
        }
        
        // scan the array from right to left, 
        // find the first number less than first Descending number 
        int firstSwap = len-1;
        while (nums[firstDescending] >= nums[firstSwap])
            firstSwap --;
        // swap this 2 numbers
        // example 6，3，4，9，8，7，1
        // first Descending number is 4
        // first number need to be swap is 7
        // after swap it will be 6，3，7，9，8，4，1
        // the number after swap point will be Descending
        // 9，8，4，1
        swap(nums,firstDescending,firstSwap);
        // reverse that part, and we got the result
        reverse(nums, firstDescending+1, len-1);
        
    }
    
    public static void main(String args[]) {
        Permutations p = new Permutations();
        int a1[] = {1, 2};
        p.nextPermutation(a1);
        for (int i:a1)
            System.out.print(i+" ");
        System.out.println();
    }
    

}
