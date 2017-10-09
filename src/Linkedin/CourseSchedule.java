package Linkedin;
/*
 * Leetcode 207. Course Schedule
 * There are a total of n courses you have to take, labeled from 0 to n - 1.

    Some courses may have prerequisites, for example to take course 0 you have to 
    first take course 1, which is expressed as a pair: [0,1]
    
    Given the total number of courses and a list of prerequisite pairs, is it 
    possible for you to finish all courses?
    
    For example:
    
    2, [[1,0]]
    There are a total of 2 courses to take. To take course 1 you should have finished 
    course 0. So it is possible.
    
    2, [[1,0],[0,1]]
    There are a total of 2 courses to take. To take course 1 you should have finished 
    course 0, and to take course 0 you should also have finished course 1. So it is 
    impossible.
    
    Note:
    The input prerequisites is a graph represented by a list of edges, not adjacency
     matrices. Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.
 */
import java.util.*;
public class CourseSchedule {
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length < 1 || numCourses == 0)
            return true;
        int len = prerequisites.length;
        int i;
        int pCounter[] = new int[numCourses];
        
        for (i = 0; i < len; i++) {
            pCounter[prerequisites[i][0]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        
        for (i = 0; i < numCourses; i++) {
            if (pCounter[i] == 0)
                queue.offer(i);
        }
        int noPre = queue.size();
        
        while (!queue.isEmpty()) {
            int c = queue.poll();
            for (i = 0; i < len; i++) {
                if (prerequisites[i][1] == c) {
                    pCounter[prerequisites[i][0]]--;
                    if (pCounter[prerequisites[i][0]] == 0){
                        queue.offer(prerequisites[i][0]);
                        noPre++;
                    }
                }
                    
            }
        }
        
        return noPre == numCourses;
    }

}
