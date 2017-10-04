package Linkedin;

import java.util.Arrays;

/*
 * There are N sales windows in a train station, each window has a[i] ticket
 * The sale price of ticket in each window equals the remaining ticket in this window
 * What is the max price after we sold m tickets?
 */
public class TrainTicket {
    public static int getTicketPrice(int nums[], int m) {
        Arrays.sort(nums);
        int sold = m;
        int idx = 0;
        for (; idx < nums.length; idx++) {
            if (nums[idx] < sold) {
                sold -= nums[idx];
                nums[idx] = 0;
            } else {
                nums[idx] -= sold;
                sold = 0;
                break;
            }
        }
        
        int total = 0;
        for (; idx<nums.length; idx++) {
            total += nums[idx]*nums[idx];
        }
        
        return total;
    }
    
    public static void main(String args[]) {
        int a1[] = { 2,1,3};
        int a3[] = { 2,1,3};
        int a2[] = { 1,1,3};
        System.out.println(getTicketPrice(a1,1));
        System.out.println(getTicketPrice(a3,2));
        System.out.println(getTicketPrice(a2,2));
    }

}
