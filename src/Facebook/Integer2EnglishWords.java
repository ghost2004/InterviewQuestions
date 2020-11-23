package Facebook;

/*
 * Leetcode 273. Integer to English Words
 * 
 * Convert a non-negative integer num to its English words representation.

 

Example 1:

Input: num = 123
Output: "One Hundred Twenty Three"
Example 2:

Input: num = 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: num = 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Example 4:

Input: num = 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
public class Integer2EnglishWords {
    private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] THOUSANDS = {"", " Thousand", " Million", " Billion"};
    
    
    private String getWord(int num) {
         if (num < 20)
            return LESS_THAN_20[num];
         else if (num < 100) 
            return TENS[num/10] + " " + getWord(num%10);
         else 
            return  LESS_THAN_20[num/100] + " Hundred " + getWord(num%100);
    }
    
    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";
        
        int idx = 0;
        String words = "";
        
        while (num > 0) {
            if (num % 1000 != 0) {
                words = getWord(num%1000).trim() +  THOUSANDS[idx] + " " + words;
            }
            num /= 1000;
            idx ++;
        }
        
        return words.trim();
    }
}
