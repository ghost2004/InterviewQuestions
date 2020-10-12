package DoorDash;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Leetcode 210. Course Schedule II
 * 
 * There are a total of n courses you have to take labelled from 0 to n - 1.

Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi] this means you must take the course bi before the course ai.

Given the total number of courses numCourses and a list of the prerequisite pairs, return the ordering of courses you should take to finish all courses.

If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be 
taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]
 */
public class CourseScheduleII {
    private class GraphNode {
        public int _inDegree;
        public List<Integer> _linkedNodes;
        public int _nodeIdx;
        
        public GraphNode(int node) {
            _nodeIdx = node;
            _inDegree = 0;
            _linkedNodes = new ArrayList<>();
        }
    }
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int topOrder[] = new int[numCourses];
        
        GraphNode graphMap[] = new GraphNode[numCourses];
        for (int i = 0; i < numCourses; i++)
            graphMap[i] = new GraphNode(i);
        
        for (int p[] : prerequisites) {
            graphMap[p[0]]._inDegree++;
            graphMap[p[1]]._linkedNodes.add(p[0]);
        }
        
        Stack<GraphNode> stack = new Stack<>();
        
        for (int i = 0; i < numCourses; i++) {
            if (graphMap[i]._inDegree == 0){
                stack.push(graphMap[i]);
            }    
        }
        int curIdx = 0;
        while (!stack.isEmpty()) {
            GraphNode node = stack.pop();
            topOrder[curIdx++] = node._nodeIdx;
            for (int n : node._linkedNodes) {
                if (--graphMap[n]._inDegree == 0) {
                    stack.push(graphMap[n]);
                }
            }
        }
        
        if (curIdx < numCourses) 
            return new int[0];
        
        return topOrder;
        
    }

}
