package Linkedin;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringTreeDepthTest {

    @Test
    public void testGetTreeDepth() {
        StringTreeDepth s = new StringTreeDepth();
        assertEquals(0, s.getTreeDepth("(00)"));
        assertEquals(1, s.getTreeDepth("((00)0)"));
        assertEquals(1, s.getTreeDepth("((00)(00))"));
        assertEquals(2, s.getTreeDepth("((00)(0(00)))"));
        assertEquals(3, s.getTreeDepth("((00)(0(0(00))))"));
        assertEquals(-1, s.getTreeDepth("x"));
        assertEquals(-1, s.getTreeDepth("0"));
        assertEquals(-1, s.getTreeDepth("(0)"));
        assertEquals(-1, s.getTreeDepth("(00)x"));
        assertEquals(-1, s.getTreeDepth("(0p)"));
    }

}
