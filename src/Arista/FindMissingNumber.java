package Arista;
/*
 * Find a missing number in a continuous number list. For   example, 6 is missing in 2, 3, 4, 5, 7
 */
public class FindMissingNumber {
    public int findMissing(int a[]) {
        // setup left and right index for binary search
        int left = 0;
        int right = a.length - 1;
        int expect = a[0];
        while (left < right) {
            // only 2 elements left here, just return the middle value of them
            if (right == left +1)
                return a[left]+1;
            // get the middle index
            int mid = (left+right)/2;
            // here is what we expected if it was a continuous array
            expect = a[left] + mid - left;
            if (a[mid] == expect) {
                // everything is there in left sub-array, search in the right sub-array
                left = mid+1;
            } else {
                // something missing in the left sub-array
                right = mid;
            }
            
        }
        
        return expect;
    }
    
    public static void main(String args[]) {
        FindMissingNumber f = new FindMissingNumber();
        int a1[] = { 2, 3, 4, 5, 7};
        int a2[] = { 1, 3, 4, 5,6, 7,8};
        int a3[] = { 1, 2,3, 4, 5,6, 7,8,10};
        int a4[] = { 1, 2,3, 4, 5,6, 7,9,10,11,12,13,14,15,16,17};
        System.out.println(f.findMissing(a1));
        System.out.println(f.findMissing(a2));
        System.out.println(f.findMissing(a3));
        System.out.println(f.findMissing(a4));
    }

}
