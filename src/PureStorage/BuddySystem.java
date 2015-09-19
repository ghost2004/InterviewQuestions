
package PureStorage;

/*
 * 
 *     Given a complete binary tree with nodes of values of either 1 or 0, the following rules always hold:
    (1) a node's value is 1 if and only if all its subtree nodes' values are 1
    (2) a leaf node can have value either 1 or 0
    Implement the following 2 APIs:
    set_bit(offset, length), set the bits at range from offset to offset+length-1
    clear_bit(offset, length), clear the bits at range from offset to offset+length-1
    
    i.e. The tree is like:
                 0
              /     \
             0        1
           /  \      /  \
          1    0    1    1
         /\   / \   / 
        1  1 1   0 1
        Since it's complete binary tree, the nodes can be stored in an array:
        [0,0,1,1,0,1,1,1,1,1,0,1] 

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
        int cur = offset;
        int end = offset + len >= array.length ? array.length-1: offset + len-1;
        
        while (cur <= end) {
            array[cur] = 0;
            int idx = cur;
            while (idx > 0 && array[(idx-1)/2] == 1) {
                idx = (idx-1)/2;
                array[idx] = 0;
            }
            cur++;
        }
        
    }
    
    
    public void printArray() {
        for (int i =0 ; i < array.length; i++) {
            System.out.print("("+i+")" + array[i]+" ");
        }
        System.out.println();
    }
    
    
    public void setDown(int offset) {
        if (offset < 0 || offset >= array.length || array[offset] == 1)
            return;
        array[offset] = 1;
        setDown(offset*2+1);
        setDown(offset*2+2);
    }
    
    public void setBit(int offset, int len) {
        if (offset < 0 || offset >= array.length)
            return;

        int cur = offset;
        int end = offset + len >= array.length ? array.length-1: offset + len-1;
        while (cur <= end) {
            array[cur] = 1;
            setDown(cur*2+1);
            setDown(cur*2+2);
            int idx = cur;
            while (idx > 0) {
                int parent = (idx-1)/2;
                int sibling = idx % 2 == 0 ? idx-1: idx+1;
                boolean flag = array[sibling] == 1? true:false;
                if (flag) {
                    idx = parent;
                    array[parent] = 1;
                } else
                    break;
            }
            cur++;
        }
        
            
    }
    
    public static void main(String args[]) {
        
        int a1[] = {   0,
                     0,   1,
                   0, 1, 1,1,
                 0,1,1,1};
        
        BuddySystem b = new BuddySystem(a1);
        
        b.setBit(7, 1);
        b.printArray();
        b.clearBit(7, 4);
        b.printArray();
    }
}

