package Linkedin;
/*
 * Given a array with +ve and -ve integer , find the maximum sum such that you are not allowed 
 * to skip 2 contiguous elements ( i.e you have to select at least one of them to move forward).

eg :-

10 , 20 , 30, -10 , -50 , 40 , -50, -1, -3

Output : 10+20+30-10+40-1 = 89


 */
public class MaxSumWithTwist {
    public int getMaxSum(int arr[]) {
        if (arr == null || arr.length == 0)
            return 0;
        
        int dp[] = new int[arr.length+2];
        
        for (int i = arr.length-1; i >= 0; i--) {
            dp[i] = arr[i] + Math.max(dp[i+1], dp[i+2]);
        }
        
        
        return Math.max(dp[0], dp[1]);
    }
    
    public static void main(String args[]) {
        MaxSumWithTwist m = new MaxSumWithTwist();
        int a1[] = {10 , 20 , 30, -10 , -50 , 40 , -50, -1, -3};
        int a2[] = {1,-1,-2,3,4};
        System.out.println(m.getMaxSum(a1));
        System.out.println(m.getMaxSum(a2));
    }
}
