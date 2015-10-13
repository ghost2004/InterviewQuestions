package Uber;
/*
 * Given a restaurant menu and a budget, output all the possible ways to use up the budget.
 */
import java.util.*;
public class MenuBuget {
    private void search(int[] candidates, int idx, int target, List<Integer> buf, List<List<Integer>> out) {
        if (idx >= candidates.length)
            return;
        for (int i = idx; i < candidates.length;i++) {
            if (candidates[i] == target) {
                ArrayList<Integer> item = new ArrayList<Integer>(buf);
                item.add(candidates[i]);
                out.add(item);
                break;
            } else if (candidates[i] > target) {
                break;
            }
            int size = buf.size();
            buf.add(candidates[i] );
            search(candidates, i, target-candidates[i], buf, out);
            buf.remove(size);
            
        }
        
        
        
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> buffer = new ArrayList<Integer>();
        Arrays.sort(candidates);
        search(candidates, 0, target, buffer, result);
        return result;
    }
}
