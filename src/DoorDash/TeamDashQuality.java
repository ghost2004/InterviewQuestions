package DoorDash;

import java.util.*;

/*
 * When assembling a team of dashers to deliver and set up a catering order, we focus on professionalism and speed of the dashers. 
 * To calculate the overall quality of a team of dashers, we take the sum of each dasher's speed, and multiply it by 
 * the lowest professionalism rating of all the dashers. Given information about several available dashers, select dashers to 
 * create a team of less than or equal to a particular size. Determine the maximum quality of the team that can be created. 
 * 
 * Example 
 * n=5 
 * speed = [4, 3, 15, 5, 6] 
 * professionalism = [7, 6, 1, 2, 8] 
 * maxDashers = 3 
 * The maximum number of dashers to use is maxDashers = 3 chosen from n=5 available dashers. A dasher[i]'s speed and professionalism rating are 
 * speed[i] and professionalism[i]. Select the first, second, and fifth dashers. The quality of the team is:
 *  (speed[0] + speed[1] + speed[4]) * min(professionalism[0], professionalism[1], professionalism[4]) 
 *  = (4 +3+6) * min(7, 6, 8) = 13 + 6 = 78. 
 *  This is the highest quality that can be achieved, so the answer is 78.
 *  
 *  
 */
public class TeamDashQuality {
    static class Dasher {
        int _speed;
        int _professionalism;
        public Dasher(int speed, int professionalism) {
          _speed = speed;
          _professionalism = professionalism;
        }
    }

    public static int computeQuality(int n, int speed[], int professionalism[], int maxDashers) {
        Dasher dashers[] = new Dasher[n];
        
        for (int i = 0; i < n; i++) {
            dashers[i] = new Dasher(speed[i], professionalism[i]);
        }
        
        Arrays.sort(dashers, new Comparator<Dasher>(){
            @Override 
            public int compare(Dasher a, Dasher b) {
                return b._professionalism - a._professionalism;
            }
        });
        
        PriorityQueue<Dasher> heap = new PriorityQueue<>(maxDashers, new Comparator<Dasher>(){
            @Override 
            public int compare(Dasher a, Dasher b) {
                return a._speed - b._speed;
            }
        });
        
        int max = 0;
        int curSpeedTotal = 0;
        for (int i = 0; i < n; i++) {
            if (heap.size() >= maxDashers) {
                Dasher out = heap.poll();
                curSpeedTotal -= out._speed;
            }
            heap.offer(dashers[i]);
            curSpeedTotal += dashers[i]._speed;
            max = Math.max(max, curSpeedTotal * dashers[i]._professionalism);
        }
        
        return max;
    }
    
    public static void main(String args[]) {
        int speed[] = {4, 3, 15, 5, 6};
        int pro[] = {7, 6, 1, 2, 8};
        System.out.println(computeQuality(5, speed, pro, 3));
        int speed2[] = {11, 10, 7};
        int pro2[] = {6, 4, 8};
        System.out.println(computeQuality(3, speed2, pro2, 2));
        int speed3[] = {12, 112, 100, 13, 55};
        int pro3[] = {31, 4, 100, 55, 50};
        System.out.println(computeQuality(5, speed3, pro3, 3));
    }
}
