package Linkedin;
/*
 * Same to LEETCODE  Find the Celebrity  
* Given a matrix of following between N LinkedIn users (with ids from 0 to N-1): 
* followingMatrix[i][j] == true iff user i is following user j 
* thus followingMatrix[i][j] doesn't imply followingMatrix[j][i]. 
* Let's also agree that followingMatrix[i][i] == false 
* 
* Influencer is a user who is: 
* - followed by everyone else and 
* - not following anyone himself 
* 
* This method should find an Influencer by a given matrix of following, 
* or return -1 if there is no Influencer in this group. 
*/ 


public class InfluencerFinder {
    public int getInfluencer(boolean[][] followingMatrix) {
        int candidate = 0;
        for (int i = 0; i < followingMatrix.length; i++) {
            if(followingMatrix[candidate][i])
                candidate = i;
        }
        
        for (int i = 0; i < followingMatrix.length; i++) {
            if (i == candidate)
                continue;
            if (followingMatrix[candidate][i] || (!followingMatrix[i][candidate]))
                return -1;
        }
        
        return candidate;
    }
    
    public static void main(String args[]) {
        InfluencerFinder finder = new InfluencerFinder();
        boolean t1[][] = { {false, false, false}, {true, false, false} ,{true, true, false}};
        boolean t2[][] = { {false, false, true}, {true, false, false} ,{true, true, false}};
        boolean t3[][] = { {false, false, true}, {true, false, true} ,{false, false, false}};
        System.out.println(finder.getInfluencer(t1));
        System.out.println(finder.getInfluencer(t2));
        System.out.println(finder.getInfluencer(t3));
    }

}
