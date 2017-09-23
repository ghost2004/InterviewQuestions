package Linkedin;

import java.util.Arrays;

/*
 * Leetcode 135. Candy
 * There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?


 */
public class Candy {
    public int candy(int[] ratings) {
        if (ratings == null)
            return 0;
        int length = ratings.length;
        if (length < 2)
            return length;
        
        // candy for each child
        int candies[] = new int[length];
        // by default everyone should get one
        Arrays.fill(candies, 1);
        
        // scan from left to right, make sure get more candies if rating is higher than left neighbors
        for (int i = 1; i < length; i++) {
            if (ratings[i] > ratings[i-1])
                candies[i] = candies[i-1] + 1;
        }
        
        int result = candies[length - 1];
        // scan from right to left, make sure get more candies if rating is higher than right neighbors
        for (int i = length - 1; i > 0; i--) {
            if (ratings[i-1] > ratings[i])
                candies[i-1] = Math.max(candies[i]+1, candies[i-1]);
            // add it into final result
            result += candies[i-1];
        }
        
        return result;
    }
    
    public static void main(String args[]) {
        int a1[] = {1,2,3};
        Candy c= new Candy();
        System.out.println(c.candy(a1));
    }

}
