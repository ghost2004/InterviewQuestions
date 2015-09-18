package PureStorage;

/*
 * Buddy system is a complete binary tree, a node in this tree could be one or zero.
 * 
 * The value of node will be 1 only when all of its children are 1
 * 
 * Implement following methods:
 * 
 * clearBit(int offset, int len);
 * 
 * setBit(int offset, int len);
 * 
 */
public class BuddySystem {
    
    private int array[];
    public BuddySystem(int arr[]) {
        this.array = arr;
    }
    
    public void clearBit(int offset, int len) {
        if (offset < 0 || offset >= array.length)
            return;
        int idx = offset;
        array[idx] = 0;
        while (idx > 0 && array[(idx-1)/2] == 1) {
            idx = (idx-1)/2;
            array[idx] = 0;
        }
    }
    
    public boolean isAllSet(int offset) {
        if (offset >= array.length)
        if (array[offset] == 0)
            return false;
        return  isAllSet(offset*2+1) && isAllSet(offset*2+2);
    }
    public void setBit(int offset, int len) {
        if (offset < 0 || offset >= array.length)
            return;
        if (array[offset] == 1)
            return;
        if (!isAllSet(offset*2+1) || !isAllSet(offset*2+2)) 
            return;
        array[offset] = 1;
        
        int idx = offset;
        while (idx > 0) {
            int parent = (offset-1)/2;
            int left = parent*2 +1;
            int right = parent*2+2;
            boolean flag = false;
            if (left == idx) {
                flag = array[right] == 1;
            } else {
                flag = array[left] == 1;
            }
            
            if (flag) {
                idx = parent;
                array[parent] = 1;
            } else
                break;
                
        }
            
    }
    
    public static void main(String args[]) {
        
    }
}
