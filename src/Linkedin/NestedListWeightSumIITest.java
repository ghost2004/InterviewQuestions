package Linkedin;

import static org.junit.Assert.*;

import org.junit.Test;

import Common.NestedInteger;

public class NestedListWeightSumIITest {

    @Test
    public void testDepthSumInverse() {
        NestedListWeightSumII sum = new NestedListWeightSumII();
        
        String test1 = "[[1,1],2,[1,1]]";
        String test2 = "[1,[4,[6]]]";
        NestedInteger t1 = NestedInteger.getFromString(test1);
        NestedInteger t2 = NestedInteger.getFromString(test2);
        assertEquals(8, sum.depthSumInverse(t1.getList()));
        assertEquals(17, sum.depthSumInverse(t2.getList()));
    }

}
