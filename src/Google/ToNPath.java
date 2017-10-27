package Google;
/*
 * Given an integer N, start from 0, every time add 2^i, which i can be any integer
 * Print out all possibility to reach N
 * 
 * For Example 
 * N = 4
 * Result is 
 * [0,1,2,3,4], [0,1,2,4], [0,1,3,4], [0,2,4], [0,2,3,4], [0,4]
 * 
 */
import java.util.*;
public class ToNPath {
    private void bt_com(List<List<Integer>> result, List<Integer> buf, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(buf));
            return;
        }
        for (int i = 1; i <= target; i*=2 ) {
            buf.add(i+buf.get(buf.size()-1));
            bt_com(result, buf, target - i);
            buf.remove(buf.size()-1);
        }
    }
    
    public void showNPath(int N) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> buf = new ArrayList<>();
        buf.add(0);
        bt_com(result, buf, N);
        
        for (List<Integer> list:result) {
            System.out.print("[");
            for (Integer i:list) {
                System.out.print(i+",");
            }
            System.out.println("]");
        }
    }
    
    public static void main(String args[]) {
        ToNPath t = new ToNPath();
        t.showNPath(4);
    }
    
}
