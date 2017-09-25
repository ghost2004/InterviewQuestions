package Linkedin;
/*
 * Leetcode 187. Repeated DNA Sequences
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, 
 * for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify 
 * repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur 
more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
 */
import java.util.*;
public class RepeatDNASeq {

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 10)
            return result;
        int map[] = new int[26];
        map['A'-'A'] = 0;
        map['C'-'A'] = 1;
        map['G'-'A'] = 2;
        map['T'-'A'] = 3;
        Set<Integer>  set = new HashSet<>();
        Set<Integer> added = new HashSet<>();
        
        int mask = (1<<20)-1;
        int hash = 0;
        
        for (int i = 0; i < 10; i++) {
            hash = (hash << 2) + map[s.charAt(i)-'A'];
        }
        
        set.add(hash);

        for (int i = 10; i < s.length(); i++) {
            hash = (hash << 2) + map[s.charAt(i)-'A'];
            hash &= mask;

            if (set.contains(hash) && !added.contains(hash)) {
                result.add(s.substring(i-9, i+1));
                added.add(hash);
            }
            set.add(hash);
        }
        
        
        return result;
        
    }
    
    public static void main(String args[]) {
        RepeatDNASeq r = new RepeatDNASeq();
        String test = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> res = r.findRepeatedDnaSequences(test);
        for (String s:res) 
            System.out.print(s+" ");
        System.out.println();
    }
}
