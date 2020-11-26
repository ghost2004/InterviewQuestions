package Facebook;
/*
 * Leetcode 408. Valid Word Abbreviation
 * 
 * Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note: Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:

Given s = "internationalization", abbr = "i12iz4n":

Return true.
Example 2:

Given s = "apple", abbr = "a2e":

Return false.
 */
public class ValidWordAbbreviation {
    public static boolean validWordAbbreviation(String word, String abbr) {
        int counter = 0;
        int wLen = word.length();
        int aLen = abbr.length();
        int ptr = 0;
        
        for (int i = 0; i < aLen; i++) {
            char c = abbr.charAt(i);
            if (c >= '0' && c <= '9') {
                if (counter == 0 && c == '0')
                    return false;
                counter = counter * 10 + (c - '0');
            } else {
                ptr += counter;
                if (ptr >= wLen || word.charAt(ptr++) != c)
                    return false;
                counter = 0;
            }
        }
        
        return ptr + counter == wLen;
    }
    
    public static void assertEquals(boolean expected, boolean actual) {
        String result = expected == actual ? "Passed" : "Failed";
        System.out.println(result);
    }
    
    public static void test0(){
        String word =  "..";
        String abbr =  "2";
        boolean expected = true;
        boolean actual = validWordAbbreviation(word, abbr);
        assertEquals(expected, actual);

    }


    public static void test1(){
        String word =  "internationalization";
        String abbr =  "i12iz4n";
        boolean expected = true;
        boolean actual = validWordAbbreviation(word, abbr);
        assertEquals(expected, actual);

    }

    
    public static void test2(){
        String word =  "apple";
        String abbr =  "a2e";
        boolean expected = false;
        boolean actual = validWordAbbreviation(word, abbr);
        assertEquals(expected, actual);

    }

    
    public static void test3(){
        String word =  "internationalization";
        String abbr =  "i5a11o1";
        boolean expected = true;
        boolean actual = validWordAbbreviation(word, abbr);
        assertEquals(expected, actual);

    }

    
    public static void test4(){
        String word =  "hi";
        String abbr =  "1";
        boolean expected = false;
        boolean actual = validWordAbbreviation(word, abbr);
        assertEquals(expected, actual);
    }

    
    public static void test5(){
        String word =  "a";
        String abbr =  "1";
        boolean expected = true;
        boolean actual = validWordAbbreviation(word, abbr);
        assertEquals(expected, actual);
    }

    
    public static void test6(){
        String word =  "a";
        String abbr =  "2";
        boolean expected = false;
        boolean actual = validWordAbbreviation(word, abbr);
        assertEquals(expected, actual);
    }

    
    public static void test7(){
        String word =  "hi";
        String abbr =  "1i";
        boolean expected = true;
        boolean actual = validWordAbbreviation(word, abbr);
        assertEquals(expected, actual);
    }

    
    public static void test8(){
        String word =  "hi";
        String abbr =  "3";
        boolean expected = false;
        boolean actual = validWordAbbreviation(word, abbr);
        assertEquals(expected, actual);
    }

    
    public static void test9(){
        String word =  "hi";
        String abbr =  "11";
        boolean expected = false;
        boolean actual = validWordAbbreviation(word, abbr);
        assertEquals(expected, actual);
    }

    
    public static void test10(){
        String word =  "word";
        String abbr =  "1o1d";
        boolean expected = true;
        boolean actual = validWordAbbreviation(word, abbr);
        assertEquals(expected, actual);
    }

    
    public static void test11(){
        String word =  "abbreviation";
        String abbr =  "a010n";
        boolean expected = false;
        boolean actual = validWordAbbreviation(word, abbr);
        assertEquals(expected, actual);
    }
    
    public static void main(String args[]) {
        test0();
        test1();
        
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
        test9();
        test10();
        test11();
    }
    
}
