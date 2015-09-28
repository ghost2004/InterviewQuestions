package Linkedin;
/*
 * Given two words word1 and word2, find the minimum number of steps required to 
 * convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null)
            return 0;
        if (word1 == null || word1.length() == 0)
            return word2.length();
        if (word2 == null || word2.length() == 0)
            return word1.length();
        if (word1.equals(word2))
            return 0;
        int m = word1.length();
        int n = word2.length();
        int i,j;
        // DP Matrix 
        // distance[i][j] means the edit distance between word1(0,i) and word(0,j) 
        int distance[][] = new int[m+1][n+1];
        
        for (i = 0; i <= m; i++) {
            distance[i][0] = i;
        }
        
        for (i = 0; i <= n; i++) {
            distance[0][i] = i;
        }
        
        for (i = 1; i <= m; i++) {
            for (j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    distance[i][j] = distance[i-1][j-1]; 
                } else {
                    
                    int replace = distance[i-1][j-1] + 1;
                    int insert = distance[i-1][j] + 1;
                    int delete = distance[i][j-1] +1;
                    
                    distance[i][j] = Math.min(replace, Math.min(insert, delete));
                }
            }
        }
        
        
        return distance[m][n];
    }
    
    public static void main(String args[]) {
        EditDistance e = new EditDistance();
        System.out.println(e.minDistance("", "a"));
    }
}
