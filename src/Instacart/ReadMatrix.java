package Instacart;

import java.io.*;
import java.util.*;

/*
 * For this challenge, you will need to parse data from STDIN to find a character in a matrix.
 * Below is an example of input you will receive from STDIN
 * [2,4]
 * AFKPU
 * BGLQV
 * CHMRW
 * DINSX
 * EJQTY
 * 
 * The first line is the [X,Y] coordinates of the character in the matrix([0,0] is the bottom left character)
 * The remaining lines contain a matrix of random characters, with a character located at the coordinates from line 1.
 * So, in the example above, we are looking for a character at the coordinate [2,4].
 * Moving right 2 spaces, and up 4, we found K.
 */
public class ReadMatrix {
    
    private static int[] readCoordinates(String line) {
        int out[] = new int[2];
        String coordinates[] = line.substring(1,line.length()-1).split(",");
        if (coordinates.length != 2)
            return null;
        try {
            out[0] = Integer.valueOf(coordinates[0].trim());
            out[1] = Integer.valueOf(coordinates[1].trim());
        } catch (NumberFormatException e) {
            return null;
        }
        
        if (out[0] < 0 || out[1] < 0)
            return null;
        return out;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int x = 0;
        int y = 0;
        boolean firstLine = true; 
        List<String> matrix = new ArrayList<>();
        while (true) {
            String text = br.readLine();
            
            if (firstLine) {
                int coordinates[] = readCoordinates(text);
                if (coordinates == null) {
                    System.out.println("Wrong coordinates, please try format like [x,y] again)");
                } else {
                    x = coordinates[0];
                    y = coordinates[1];
                    firstLine = false;
                }
            } else if (text.length() == 0) {
                break;
            } else {
                matrix.add(text);
            }
        }
        
        br.close();
        
        if(y > matrix.size() - 1 || x > matrix.get(0).length() - 1) {
            System.out.println("Invalid coordinates for " + x + ", "+ y);
        } else {
            System.out.println(matrix.get(matrix.size() - y - 1).charAt(x));
        }
    }
}
