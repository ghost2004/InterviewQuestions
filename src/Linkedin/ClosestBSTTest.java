package Linkedin;

import static org.junit.Assert.*;

import org.junit.Test;
import Common.TreeNode;
import java.util.*;
public class ClosestBSTTest {

    @Test
    public void testClosestValue() {
        TreeNode t = TreeNode.deserialize("9,4,3,#,#,6,5,#,#,7,#,#,17,#,22,20,#");
        ClosestBST b = new ClosestBST();
        assertEquals(4, b.closestValue(t, 4));
        assertEquals(17, b.closestValue(t, 18));
        assertEquals(9, b.closestValue(t, 12));
        assertEquals(3, b.closestValue(t, 1));
        assertEquals(22, b.closestValue(t, 25));
    }

    private boolean checkList(List<Integer> list, int answer[]) {
        if (list.size() != answer.length)
            return false;
        Integer array[] = list.toArray(new Integer[list.size()]);
        Arrays.sort(array);
        
        for (int i = 0; i < answer.length; i++) {
            if (array[i] != answer[i])
                return false;
        }
        return true;
    }
    
    @Test
    public void testClosestValueRange() {
        TreeNode t = TreeNode.deserialize("9,4,3,#,#,6,5,#,#,7,#,#,17,#,22,20,#");
        ClosetBSTII b = new ClosetBSTII();
        
        List<Integer> list = b.closestKValues(t, 4, 3);
        int a1[] = { 3,4,5};
        assertEquals(true, checkList(list, a1));
        list = b.closestKValues(t, 15, 4);
        int a2[] = {9,17,20,22};
        assertEquals(true, checkList(list, a2));
        list = b.closestKValues(t, 50, 3);
        int a3[] = {17,20,22};
        assertEquals(true, checkList(list, a3));
        list = b.closestKValues(t, 6, 6);
        int a4[] = {3,4,5,6,7,9};
        assertEquals(true, checkList(list, a4));
        list = b.closestKValues(t, 1, 7);
        int a5[] = {3,4,5,6,7,9,17};
        assertEquals(true, checkList(list, a5));

    }
}
