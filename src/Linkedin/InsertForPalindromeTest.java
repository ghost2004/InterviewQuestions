package Linkedin;

import static org.junit.Assert.*;

import org.junit.Test;

public class InsertForPalindromeTest {

    private InsertForPalindrome test = new InsertForPalindrome();
    @Test
    public void testFindInsertMin2() {
        assertEquals(1, test.findInsertMin2("ab"));
        assertEquals(0, test.findInsertMin2("aa"));
        assertEquals(3, test.findInsertMin2("abcd"));
        assertEquals(2, test.findInsertMin2("abcda"));
        assertEquals(4, test.findInsertMin2("abcde"));
        assertEquals(3, test.findInsertMin2("geeks"));
    }

    @Test
    public void testFindInsertMin() {
        assertEquals(1, test.findInsertMin("ab"));
        assertEquals(0, test.findInsertMin("aa"));
        assertEquals(3, test.findInsertMin("abcd"));
        assertEquals(2, test.findInsertMin("abcda"));
        assertEquals(4, test.findInsertMin("abcde"));
        assertEquals(3, test.findInsertMin("geeks"));
    }

}
