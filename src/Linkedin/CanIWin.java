package Linkedin;

/* 
 * Leetcode 464. Can I Win
 * 
 * In "the 100 game," two players take turns adding, to a running
total, any integer from 1..10. The player who first causes the running
total to reach or exceed 100 wins.
What if we change the game so that players cannot re-use integers?
For example, if two players might take turns drawing from a common pool of
numbers
of 1..15 without replacement until they reach a total >= 100. This problem
is
to write a program that determines which player would win with ideal play.

Write a procedure, "Boolean canIWin(int maxChoosableInteger, int desiredTotal)",
which returns true if the first player to move can force a win with optimal
play.

Your priority should be programmer efficiency; don't focus on minimizing
either space or time complexity.
*/
import java.util.*;
public class CanIWin {
    
    HashMap<Integer, Boolean> map;
    boolean state[];
    
    private int getKey() {
        int key = 0;
        for (int i = state.length-1; i >= 0; i--) {
            if (state[i]) 
                key |= 1;
            key <<= 1;
        }
            
        return key;    
    }
    
    private boolean checkWin(int target) {
        if (target <= 0)
            return false;
        int key = getKey();
        
        if (!map.containsKey(key)) {
            for(int i =1; i < state.length; i++) {
                if (!state[i]) {
                    state[i] = true;
                    if (!checkWin(target-i)) {
                        map.put(key, true);
                        state[i] = false;
                        return true;
                        
                    }
                    state[i] = false;
                }
            }
            
            map.put(key, false);
        }
        
        return map.get(key);
    }
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (1+maxChoosableInteger)*maxChoosableInteger/2;
        if (sum < desiredTotal)
            return false;
        if (desiredTotal <= maxChoosableInteger)
            return true;
        
        map = new HashMap<Integer, Boolean>();
        state = new boolean[maxChoosableInteger+1];
        return checkWin(desiredTotal);
    }
}
