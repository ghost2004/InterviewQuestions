package Google;
/*
 * Leetcode 373. Find K Pairs with Smallest Sums
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

Return: [1,2],[1,4],[1,6]

The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:
Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

Return: [1,1],[1,1]

The first 2 pairs are returned from the sequence:
[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:
Given nums1 = [1,2], nums2 = [3],  k = 3 

Return: [1,3],[2,3]

All possible pairs are returned from the sequence:
[1,3],[2,3]
 */
import java.util.*;
public class FindKPairsSmallestSums {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> out = new ArrayList<>();
        if (k <= 0 || nums1.length == 0 || nums2.length == 0)
            return out;

        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->a[0]+a[1]-b[0]-b[1]);
        
        for (int i = 0; i < nums1.length && i < k; i++)
            queue.offer(new int[]{nums1[i], nums2[0], 0});
        
        while (k-- > 0 && !queue.isEmpty()) {
            int c[] = queue.poll();
            int in[] = new int[2];
            in[0] = c[0];
            in[1] = c[1];
            out.add(in);
            
            if (c[2] == nums2.length-1)
                continue;
            
            queue.offer(new int[]{c[0], nums2[c[2]+1], c[2]+1});
        }
        
        return out;
    }
}
