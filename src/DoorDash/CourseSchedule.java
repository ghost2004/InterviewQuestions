package DoorDash;

/*
 * Leetcode 207. Course Schedule
 * 
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
 

Constraints:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
1 <= numCourses <= 10^5
 */
import java.util.*;
public class CourseSchedule {
    private class GraphNode {
        public int _inDegree;
        public List<Integer> _linkedNodes;
        
        public GraphNode() {
            _inDegree = 0;
            _linkedNodes = new ArrayList<>();
        }
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <=1 || prerequisites == null || prerequisites.length == 0)
            return true;
        GraphNode graphMap[] = new GraphNode[numCourses];
        for (int i = 0; i < numCourses; i++)
            graphMap[i] = new  GraphNode();
        
        for (int p[] : prerequisites) {
            graphMap[p[0]]._inDegree++;
            graphMap[p[1]]._linkedNodes.add(p[0]);
        }
        
        int count = 0;
        Stack<GraphNode> stack = new Stack<>();
        
        for (int i = 0; i < numCourses; i++) {
            if (graphMap[i]._inDegree == 0){
                stack.push(graphMap[i]);
            }    
        }
        
        while (!stack.isEmpty()) {
            GraphNode node = stack.pop();
            count++;
            for (int idx : node._linkedNodes) {
                if (--graphMap[idx]._inDegree == 0 )
                    stack.push(graphMap[idx]);
                
            }
        }
        
        return count == numCourses;
    }
}
