package Google;
/*
 * Leetcode 399. Evaluate Division
 * 
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real 
 * number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , 
where equations.size() == values.size(), and the values are positive. This represents the equations. 
Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is 
no contradiction.
 */
import java.util.*;
public class EvaluateDivision {
    HashMap<String, HashMap<String, Double>> map;
    HashSet<String> visited;
    private Double dfs(String start, String end) {
        if (!map.containsKey(end))
            return null;
        
        if (visited.contains(start))
            return null;
        
        if (start.equals(end))
            return 1.0;
        
        HashMap<String, Double> p = map.get(start);
        if (p == null)
            return null;
        
        visited.add(start);
        for (String k:p.keySet()) {
            Double res = dfs(k, end);
            if (res != null) {
                visited.remove(start);
                return p.get(k)*res;
            }
        }
        
        visited.remove(end);
        
        return null;
    }
    
    private void insertMap(String from, String to, double v) {
        HashMap<String, Double> p = map.get(from);
        if (p == null) {
            p =  new HashMap<>();
            map.put(from, p);
        }
        p.put(to, v);
    }
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        map = new HashMap<>();
        
        double out[] = new double[queries.length];
        
        for (int i = 0; i < equations.length; i++) {
            insertMap(equations[i][0], equations[i][1],values[i]);
            insertMap(equations[i][1], equations[i][0],1.0/values[i]);
        }
        
        for (int i = 0; i < queries.length; i++) {
            visited = new HashSet<>();
            Double res = dfs(queries[i][0], queries[i][1]);
            if (res != null)
                out[i] = res;
            else
                out[i] = -1;
        }
        
        return out;
    }
    
    public static void main(String args[]) {
        EvaluateDivision e = new EvaluateDivision();
        String[][] equations = {{"a","b"},{"b","c"}};
        double[] values =         {2.0,3.0};
        String[][] queries = {{"a","a"}};
        double[] out = e.calcEquation(equations, values, queries);
        System.out.println(out[0]);
        
    }

}
