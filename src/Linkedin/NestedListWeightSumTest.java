package Linkedin;

import static org.junit.Assert.*;

import org.junit.Test;

import Common.NestedInteger;

public class NestedListWeightSumTest {

    @Test
    public void testDepthSum() {
        NestedListWeightSum sum = new NestedListWeightSum();
        
        String test1 = "[[1,1],2,[1,1]]";
        String test2 = "[[1,[1,1]],2,[1,1]]";
        NestedInteger t1 = NestedInteger.getFromString(test1);
        NestedInteger t2 = NestedInteger.getFromString(test2);
        assertEquals(10, sum.depthSum(t1.getList()));
        assertEquals(14, sum.depthSum(t2.getList()));
        
    }

}
