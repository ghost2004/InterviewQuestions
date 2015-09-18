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
    
    public void printArray() {
        for (int i =0 ; i < array.length; i++) {
            System.out.println("("+i+")" + array[i]+" ");
        }
        System.out.println();
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
            int sibling = idx % 2 == 0 ? idx-1: idx+1;
            boolean flag = array[sibling] == 1? true:false;
            if (flag) {
                idx = parent;
                array[parent] = 1;
            } else
                break;
                
        }
            
    }
    
    public static void main(String args[]) {
        
        int a1[] = { 0,0,1,0,1,1,0,1,1,1};
        
        BuddySystem b = new BuddySystem(a1);
        
        b.setBit(7, 1);
    }
}
