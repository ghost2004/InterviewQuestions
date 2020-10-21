package Facebook;

/*
 * Leetcode 1570. Dot Product of Two Sparse Vectors
 * Given two sparse vectors, compute their dot product.

Implement class SparseVector:

SparseVector(nums) Initializes the object with the vector nums
dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot 
product between two SparseVector.

Follow up: What if only one of the vectors is sparse?

 

Example 1:

Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
Output: 8
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
Example 2:

Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
Output: 0
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
Example 3:

Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
Output: 6
 

Constraints:

n == nums1.length == nums2.length
1 <= n <= 10^5
0 <= nums1[i], nums2[i] <= 100
 */
import java.util.*;
public class DotProductTwoSparseVectors {
    static class SparseVector {
        public Map<Integer, Integer> _map;
        public SparseVector(int[] vector) {
            _map = new HashMap<>();
            for (int i = 0; i < vector.length; i++) {
                if (vector[i] != 0)
                    _map.put(i, vector[i]);
            }
            
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector sv) {
            if (sv._map.size() < this._map.size()) 
                return sv.dotProduct(this);
            int result = 0;
            
            for (Map.Entry<Integer, Integer> kv : _map.entrySet()) {
                Integer val = sv._map.getOrDefault(kv.getKey(), 0);
                result += val * kv.getValue();
            }
            
            return result;
            
        }
    }
    
    
    public static void main(String args[]) {
        int test_1_1[] = {1,0,0,2,3};
        int test_1_2[] = {0,3,0,4,0};
        SparseVector test1_sv1 = new SparseVector(test_1_1);
        SparseVector test1_sv2 = new SparseVector(test_1_2);
        System.out.println(test1_sv1.dotProduct(test1_sv2));
        
        int test_2_1[] = {0,1,0,0,0};
        int test_2_2[] = {0,0,0,0,2};
        SparseVector test2_sv1 = new SparseVector(test_2_1);
        SparseVector test2_sv2 = new SparseVector(test_2_2);
        System.out.println(test2_sv1.dotProduct(test2_sv2));
        
        int test_3_1[] = {0,1,0,0,2,0,0};
        int test_3_2[] = {1,0,0,0,3,0,4};
        SparseVector test3_sv1 = new SparseVector(test_3_1);
        SparseVector test3_sv2 = new SparseVector(test_3_2);
        System.out.println(test3_sv1.dotProduct(test3_sv2));
        
    }

}
