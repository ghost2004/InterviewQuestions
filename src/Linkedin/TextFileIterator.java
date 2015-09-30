package Linkedin;
/*
 * Implement a (Java) Iterable object that iterates lines one by one from a 
text file..
 */
import java.util.*;
import java.io.*;
public class TextFileIterator implements Iterable<String> {
    private BufferedReader br;
    private String cache;
    private boolean end;
    public TextFileIterator(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            br = new BufferedReader(fr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            public boolean hasNext() {
                if (cache != null) {
                    return true;
                } else if (end) {
                    return false;
                }
                try {
                    cache = br.readLine();
                    if (cache == null) {
                        end = true;
                        return false;
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                return true;
            }
            public String next() {
                if (!hasNext())
                    throw new NoSuchElementException("No more lines");
                String out = cache;
                cache = null;
                return out;
            }
        };
    }
}
