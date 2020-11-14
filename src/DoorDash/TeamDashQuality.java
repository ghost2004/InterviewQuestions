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
 *  Same to  * Leetcode 1383. Maximum Performance of a Team
 * 
 * There are n engineers numbered from 1 to n and two arrays: speed and efficiency, where speed[i] and efficiency[i] represent the speed 
 * and efficiency for the i-th engineer respectively. Return the maximum performance of a team composed of at most k engineers, since 
 * the answer can be a huge number, return this modulo 10^9 + 7.

The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their engineers. 

 

Example 1:

Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
Output: 60
Explanation: 
We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7).
 That is, performance = (10 + 5) * min(4, 7) = 60.
Example 2:

Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
Output: 68
Explanation:
This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team.
 That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
Example 3:

Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
Output: 72
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
        
        long max = 0;
        long curSpeedTotal = 0;
        for (int i = 0; i < n; i++) {
            if (heap.size() >= maxDashers) {
                Dasher out = heap.poll();
                curSpeedTotal -= out._speed;
            }
            heap.offer(dashers[i]);
            curSpeedTotal += dashers[i]._speed;
            max = Math.max(max, curSpeedTotal * dashers[i]._professionalism);
        }
        
        return  (int) (max % (long)(1e9 + 7));
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
        
        int speed4[] = {2,10,3,1,5,8};
        int pro4[] = {5,4,3,9,7,2};
        System.out.println(computeQuality(6, speed4, pro4, 2));

        System.out.println(computeQuality(6, speed4, pro4, 3));

        System.out.println(computeQuality(6, speed4, pro4, 4));
    }
}
