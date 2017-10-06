package Linkedin;
/*
 * Leetcode 341. Flatten Nested List Iterator
 * Given a nested list of integers, implement an iterator to flatten it.

    Each element is either an integer, or a list -- whose elements may also be 
    integers or other lists.
    
    Example 1:
    Given the list [[1,1],2,[1,1]],
    
    By calling next repeatedly until hasNext returns false, the order of elements 
    returned by next should be: [1,1,2,1,1].
    
    Example 2:
    Given the list [1,[4,[6]]],
    
    By calling next repeatedly until hasNext returns false, the order of elements 
    returned by next should be: [1,4,6].
 */
import java.util.*;
import Common.NestedInteger;

public class NestedIterator implements Iterator<Integer> {
    LinkedList<NestedInteger> queue = new LinkedList<>();
    
    public NestedIterator(List<NestedInteger> nestedList) {
        if (nestedList != null)
            queue.addAll(nestedList);
    }

    @Override
    public Integer next() {
        if (queue.isEmpty())
            return null;
        return queue.removeFirst().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!queue.isEmpty()) {
            if (queue.peekFirst().isInteger())
                return true;
            List<NestedInteger> t = queue.removeFirst().getList();
            for (int i = t.size()-1; i >= 0;i--) {
                queue.addFirst(t.get(i));
            }
            
            
        }
        return false;
    }
}
