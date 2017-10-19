package Google;
/*
 * Leetcode 261 Graph Valid Tree
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
 * write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Hint:

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices 
are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] 
is the same as [1, 0] and thus will not appear together in edges.


 */
import java.util.*;
public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        boolean visited[] = new boolean[n];
        for (int i = 0; i < n; i++)
            map.put(i, new ArrayList<Integer>());
        
        for (int i = 0; i < edges.length; i++) {
            map.get(edges[i][0]).add(edges[i][1]);
            map.get(edges[i][1]).add(edges[i][0]);
        }
        
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(0);
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (visited[node])
                return false;
            visited[node] = true;
            List<Integer> list = map.get(node);
            for (int t:list) {
                if (!visited[t])
                    queue.offer(t);
                
            }
            
        }
        
        for (int i = 0; i < n; i++)
            if (!visited[i])
                return false;
        
        return true;
    }
    
    public static void main(String args[]) {
        GraphValidTree g = new GraphValidTree();
        int a1[][] = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        int a2[][] = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        
        System.out.println(g.validTree(5, a1));
        System.out.println(g.validTree(5, a2));
    }
}
