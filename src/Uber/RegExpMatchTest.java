package Uber;

import static org.junit.Assert.*;

import org.junit.Test;
/*
 * Some examples:
isMatch("aa","a")  false
isMatch("aa","aa")  true
isMatch("aaa","aa")  false
isMatch("aa", "a*")  true
isMatch("aa", ".*")  true
isMatch("ab", ".*")  true
isMatch("aab", "c*a*b")  true
 */
public class RegExpMatchTest {
    RegExpMatch reg = new RegExpMatch();
    @Test
    public void testIsMatch() {
        assertEquals(false, reg.isMatch("aa", "a"));
        assertEquals(true, reg.isMatch("aa", "aa"));
        assertEquals(false, reg.isMatch("aaa", "aa"));
        assertEquals(true, reg.isMatch("aa", "a*"));
        assertEquals(true, reg.isMatch("aa", ".*"));
        assertEquals(true, reg.isMatch("ab", ".*"));
        assertEquals(true, reg.isMatch("aab", "c*a*b"));
        assertEquals(true, reg.isMatch("aa", "a."));
    }
    @Test
    public void testIsRegMatch() {
        assertEquals(false, reg.isMatch("aa", "a"));
        assertEquals(true, reg.isMatch("aa", "aa"));
        assertEquals(false, reg.isMatch("aaa", "aa"));
        assertEquals(true, reg.isMatch("aa", "a*"));
        assertEquals(true, reg.isMatch("aa", ".*"));
        assertEquals(true, reg.isMatch("ab", ".*"));
        assertEquals(true, reg.isMatch("aab", "c*a*b"));
        assertEquals(true, reg.isMatch("aa", "a."));
    }
}
