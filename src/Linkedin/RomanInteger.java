package Linkedin;

/*
 * About Roman numeric system
 * 
 * The numeric system represented by Roman numerals originated in ancient Rome and remained the usual way of writing 
 * numbers throughout Europe well into the Late Middle Ages. Numbers in this system are represented by combinations 
 * of letters from the Latin alphabet. Roman numerals, as used today, are based on seven symbols:[1]

Symbol  I   V   X   L   C   D   M
Value   1   5   10  50  100 500 1,000


Number      4   9   40  90  400 900
Notation    IV  IX  XL  XC  CD  CM


 */


public class RomanInteger {

    /*
     * Leetcode 12 Integer to Roman
     * Given an integer, convert it to a Roman numeral.
     * Input is guaranteed to be within the range from 1 to 3999.
     */
    
    public String intToRoman(int num) {
        int values[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };  
        
        StringBuilder sb = new StringBuilder();
        int res = num;
        
        for (int i = 0; i < values.length; i++) {
           while (res >= values[i]) {
               sb.append(numerals[i]);
               res -= values[i];
           }
        }
        
        return sb.toString();
        
    }
    
    /*
     * Leetcode 13. Roman to Integer
     * Given a roman numeral, convert it to an integer.
     * Input is guaranteed to be within the range from 1 to 3999.
     */
    public int charToInt(char c) {
        switch (c) {
        case 'I':
            return 1;
        case 'V':
            return 5;
        case 'X':
            return 10;
        case 'L':
            return 50;
        case 'C':
            return 100;
        case 'D':
            return 500;
        case 'M':
            return 1000;
        default:
            return 0;
        }
    }
    public int romanToInt(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int out = 0;
        int idx = 0;
        while (idx < s.length()) {
            int d1 = charToInt(s.charAt(idx));
            int d2 = 0;
            if (idx < s.length() - 1) {
                d2 = charToInt(s.charAt(idx+1));
            }
            
            if (d1 < d2) {
                out += d2-d1;
                idx += 2;
            } else {
                out += d1;
                idx++;
            }
                
            
        }
        
        return out;
    }
    
    
}
