package Uber;

import java.util.Arrays;

/*
 * Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a")  false
isMatch("aa","aa")  true
isMatch("aaa","aa")  false
isMatch("aa", "a*")  true
isMatch("aa", ".*")  true
isMatch("ab", ".*")  true
isMatch("aab", "c*a*b")  true
 */
public class RegExpMatch {
    // recursive solution
    public boolean isMatch(String s, String p) {
        // return true if both string and pattern reaches the end
        if (p.length() == 0) 
            return s.length()==0;
        
        // only one character left in pattern, check the value see if it is matched
        if (p.length() == 1) {
            if (s.length() == 1 &&(s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'))
                return true;
            return false;
        }
        
        // the next character in pattern is *
        if (p.charAt(1) == '*') {
            // if the rest of string matches return true
            if (isMatch(s,p.substring(2)))
                return true;
            // the current character matches and rest of it matches
            return (s.length() > 0 && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0)) 
                    && isMatch(s.substring(1), p));
        } else {
            // the current character matches and rest of it matches
            return (s.length() > 0 && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0))
                    && isMatch(s.substring(1), p.substring(1)));
        }
    }
    
    // dynamic programming solution
    public boolean isExpMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        boolean match[] = new boolean[sLen+1];
        Arrays.fill(match, false);
        match[sLen] = true;
        int pIdx, sIdx;
        for (pIdx = pLen-1 ;pIdx >= 0; pIdx--) {
            if (p.charAt(pIdx) == '*') {
                for (sIdx = sLen-1; sIdx >= 0; sIdx--) {
                    match[sIdx] = match[sIdx] || (match[sIdx+1] && ( 
                            p.charAt(pIdx-1) =='.' || p.charAt(pIdx-1) == s.charAt(sIdx)));  
                }
                pIdx--;
            } else {
                for (sIdx = 0; sIdx < sLen; sIdx++) {
                    match[sIdx] = match[sIdx+1] && ( 
                            p.charAt(pIdx) =='.' || p.charAt(pIdx) == s.charAt(sIdx));
                }
                match[sLen] = false;
            }
        }
        
        return match[0];
    }
}
