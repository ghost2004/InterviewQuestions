package Linkedin;
/*
 * Leetcode 347. Top K Frequent Elements
 * Given a non-empty array of integers, return the k most frequent elements.
    
    For example,
    Given [1,1,1,2,2,3] and k = 2, return [1,2].
    
    Note: 
    You may assume k is always valid, 1 <= k <= number of unique elements.
    Your algorithm's time complexity must be better than O(n log n), where n is 
    the array's size.
 */
import java.util.*;
public class TopKFreqElement {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        List<Integer> bucket[] = new List[nums.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i:nums) {
            map.put(i, map.getOrDefault(i, 0)+1);
        }
        
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (bucket[entry.getValue()-1] == null) {
                bucket[entry.getValue()-1] = new ArrayList<>();
            }
            bucket[entry.getValue()-1].add(entry.getKey());
        }
        
        for (int i = bucket.length-1; i >= 0 && res.size() < k; i--) {
            if (bucket[i] != null)
                res.addAll(bucket[i]);
        }
        
        return res;
    }
}
