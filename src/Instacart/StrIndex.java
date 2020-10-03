package Instacart;
/*
 * Leetcode 28. Implement strStr() 

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Follow-up: allow wildcard character in the needle
For example : needle "*A" in string "CDFGAGB" should return 0
             needle  "A**B" in string "CDFGAGB" should return 4
 */
import java.util.*;
public class StrIndex {
    public static int strWild(String haystack, String pattern) {
        return strWild(haystack, pattern, 0, 0);
    }
    
    public static int strWild(String haystack, String pattern, int hIdx, int pIdx) {
        if (pIdx >= pattern.length())
            return 0;
        if (hIdx >= haystack.length()) {
            while (pIdx < pattern.length() && pattern.charAt(pIdx) == '*')
                pIdx++;
            if (pIdx == pattern.length())
                return 0;
            return -1;
        }
        
        int starIdx = pIdx;
        
        while (starIdx < pattern.length() && pattern.charAt(starIdx) != '*')
            starIdx++;
        
        if (starIdx == pIdx) {
            int sIdx = strWild(haystack, pattern, hIdx, pIdx+1);
            if (sIdx == -1)
                return -1;
            return hIdx;
        }
            
        String p = pattern.substring(pIdx, starIdx);
        int cIdx = strStr(haystack.substring(hIdx), p);
        if (cIdx == -1)
            return -1;
        
        int dIdx = strWild(haystack, pattern, hIdx+cIdx+p.length(), starIdx);
        if (dIdx == -1)
            return -1;
        
        return hIdx+cIdx;
        
    }
  
    public static int strStr(String haystack, String needle) {
        if (haystack == null)
            return -1;
        if (needle == null || needle.length() == 0) 
            return 0;
        
        int hLen = haystack.length();
        int nLen = needle.length();
        if (hLen < nLen)
            return -1;
        HashMap<Character, Integer> offsetMap = new HashMap<>();
        
        for (int i = 0; i < nLen; i++) {
            offsetMap.put(needle.charAt(i), nLen - i);
        }
        
        int i = 0;
        while (i <= hLen - nLen) {
            int j = 0; 
            while (j < nLen && needle.charAt(j) == haystack.charAt(i + j))
                j++;
            if (j == nLen)
                return i;
            if (i == hLen - nLen) 
                return -1;
            Integer offset = offsetMap.get(haystack.charAt(i + nLen));
            if (offset == null) {
                i += nLen + 1;
            } else {
                i += offset;
            }
        }
        
        
        return -1;
    }
    
    public static void main(String args[]) {
        System.out.println(strStr("a", "a"));
        System.out.println(strWild("CDFGAGB", "*A"));
        System.out.println(strWild("CDFGAGB", "A**B"));
        System.out.println(strWild("CDFGAGB", "F*A*B"));
        System.out.println(strWild("CDFGAGB", "F*A*BL"));
        System.out.println(strWild("CDFGAGBL", "F*A*BL"));
        System.out.println(strWild("CDFGAGBL", "F*A*BL*"));
        System.out.println(strWild("CDFGAGBL", "*F**A*BL*"));
        System.out.println(strWild("FACEBOOKLINKEDINGOOGLE", "*F**A*BL*"));
        System.out.println(strWild("FACEBOOKLINKEDINGOOGLE", "*F***B*L*"));
    }

}
