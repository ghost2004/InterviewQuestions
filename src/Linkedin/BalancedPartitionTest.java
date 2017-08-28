package Linkedin;

import static org.junit.Assert.*;

import org.junit.Test;

public class BalancedPartitionTest {

    @Test
    public void testFindMinV1() {
        BalancedPartition b = new BalancedPartition();
        int array1[] = {1, 6, 11, 5};
        int array2[] = {1, 5, 11, 5};
        int array3[] = {1, 2, 3, 5, 6};
        assertEquals(1, b.findMinV1(array1));
        assertEquals(0, b.findMinV1(array2));
        assertEquals(1, b.findMinV1(array3));
        
    }
    @Test
    public void testFindMin() {
        BalancedPartition b = new BalancedPartition();
        int array1[] = {1, 6, 11, 5};
        int array2[] = {1, 5, 11, 5};
        int array3[] = {1, 2, 3, 5, 6};
        assertEquals(1, b.findMin(array1));
        assertEquals(0, b.findMin(array2));
        assertEquals(1, b.findMin(array3));
        
    }

}
