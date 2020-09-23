package Instacart;

import java.io.*;
import java.util.*;

/*
 * For this challenge, the goal is to construct a password from a series of chunks. The chunks will now looks like:
 * 1
 * [5, 6]
 * RXIBDIBF
 * DVMPXTBG
 * BMWAERXR
 * UPEIJGMW
 * YTALDXDH
 * JNPMOUEJ
 * XDRHCHWG
 * 
 * 0
 * [0, 1]
 * HUTQW3
 * NLEVCU
 * 
 * You will notice each chunk looks similar to the previous challenge with one addition -- the first line is the (0-based) index of the password character.
 * 
 * In our example
 * - First chunk: index 1, [5,6] is I
 * - Second chunk: index 0, [0,1] is H
 * 
 * Once you have processed all the chunks, you have the entire password and should print it to STDOUT. In our example the password is HI
 * 
 * Chunks are separated by empty line
 * 
 */
public class PasswordChunks {
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
    
    private static Character readChunk(BufferedReader br) throws IOException {
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
        
        if(y > matrix.size() - 1 || x > matrix.get(0).length() - 1) {
            System.out.println("Invalid coordinates for " + x + ", "+ y);
            return null;
        }
        
        return matrix.get(matrix.size() - y - 1).charAt(x);
    }
    
    public static void main(String[] args) throws IOException {
        
        Map<Integer, Character> map = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String text = br.readLine();
            if (text.length() == 0) 
                break;
            Integer idx;
            try {
                idx = Integer.valueOf(text.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid index for " + text);
                break;
            }
            Character c = readChunk(br);
            if (c == null) {
                break;
            } else {
                map.put(idx, c);
            }
        }
        
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < map.size(); i++) 
            buf.append(map.get(i));
        
        System.out.println(buf.toString());
    }

}
