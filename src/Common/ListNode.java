package Common;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) {
        val = x;
        next = null;
    }
    
    public void printThisNode() {
        ListNode node = this;
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("null");
    }
    
    public static void printListNode(ListNode n) {
        ListNode node = n;
        
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("null");
    }
    
    public static ListNode initFromArray(int[] array) {
        if (array == null || array.length == 0)
            return null;
        ListNode head = new ListNode(array[0]);
        ListNode cur = head;
        for (int i = 1; i < array.length; i++) {
            ListNode next = new ListNode(array[i]);
            cur.next = next;
            cur = next;
        }
        return head;
    }
}
