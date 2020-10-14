package DoorDash;
/*
 * Follow up for Leetcode 1099
 * 
 * This time we have 4 lists of integers, pick one from each list, the sum of 4 integers is less or equal than K
 * 
 * return all possible solutions
 * 
 * given four arrays representing prices of four clothing items and a total budget, 
 * find number of combinations of possible purchases where you have to buy one of each clothing item.
    e.g. input: [1,2,3] [2] [3] [2,3],  budget = 10
    output: 5 combinations ([1,2,3,2] [1,2,3,3] [2,2,3,2] [2,2,3,3] [3,2,3,2])

 */
import java.util.*; 
public class FourSumLessEqK {
    private static class Solution1 {
        private int[] listA;
        private int[] listB;
        private int[] listC;
        private int[] listD;
        
        class PNode implements Comparable<PNode> {
            int _idx1;
            int _idx2;
            int _idx3;
            int _idx4;
            int _val;
            
            public PNode(int idx1, int idx2, int idx3, int idx4) {
                _idx1 = idx1;
                _idx2 = idx2;
                _idx3 = idx3;
                _idx4 = idx4;
                _val = listA[idx1] + listB[idx2] + listC[idx3] + listD[idx4];
            }
            
            public int[] getSolution() {
                return new int[] {listA[_idx1], listB[_idx2], listC[_idx3], listD[_idx4]};
            }

            
            public String toStringIdx() {
                return String.format("%d-%d-%d-%d", _idx1, _idx2, _idx3, _idx4);
            }
            
            @Override
            public int compareTo(PNode p) {
                return this._val - p._val;
            }
        }
        
        public List<int[]> fourSumLessEqK(int[] A, int[] B, int[] C, int[] D, int K) {
            Arrays.sort(A);
            listA = A;
            Arrays.sort(B);
            listB = B;
            Arrays.sort(C);
            listC = C;
            Arrays.sort(D);
            listD = D;
            
            List<int[]> result = new ArrayList<>();
            if (listA[0] + listB[0] + listC[0] + listD[0] > K)
                return result;
            PriorityQueue<PNode> pq = new PriorityQueue<>();
            Set<String> visited = new HashSet<>();
            PNode node = new PNode(0, 0, 0, 0);
            visited.add(node.toStringIdx());
            pq.add(node);
          
            
            while (!pq.isEmpty()) {
                PNode pnode = pq.poll();
                result.add(pnode.getSolution());
                if (pnode._idx1 < listA.length - 1) {
                    PNode next = new PNode(pnode._idx1+1, pnode._idx2, pnode._idx3, pnode._idx4);
                    String v = next.toStringIdx();
                    if (next._val <= K && !visited.contains(v)) {
                        pq.add(next);
                        visited.add(v);
                    }
                }
                if (pnode._idx2 < listB.length - 1) {
                    PNode next = new PNode(pnode._idx1, pnode._idx2+1, pnode._idx3, pnode._idx4);
                    String v = next.toStringIdx();
                    if (next._val <= K && !visited.contains(v)) {
                        pq.add(next);
                        visited.add(v);
                    }
                }
                if (pnode._idx3 < listC.length - 1) {
                    PNode next = new PNode(pnode._idx1, pnode._idx2, pnode._idx3+1, pnode._idx4);
                    String v = next.toStringIdx();
                    if (next._val <= K && !visited.contains(v)) {
                        pq.add(next);
                        visited.add(v);
                    }
                }
                if (pnode._idx4 < listD.length - 1) {
                    PNode next = new PNode(pnode._idx1, pnode._idx2, pnode._idx3, pnode._idx4+1);
                    String v = next.toStringIdx();
                    if (next._val <= K && !visited.contains(v)) {
                        pq.add(next);
                        visited.add(v);
                    }
                }
            }
            
            return result;
        }
        
    }
    
    public static void main(String args[]) {
        Solution1 f = new Solution1();
        int A[] = {1,2,3};
        int B[] = {2};
        int C[] = {3};
        int D[] = {2,3};
        
        List<int[]> r = f.fourSumLessEqK(A, B, C, D, 10);
        for (int[] a : r) {
            for (int i : a) 
                System.out.print(i + ",");
            System.out.println();
        }
        
    }

}
