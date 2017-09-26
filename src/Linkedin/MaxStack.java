package Linkedin;
/*
 *  Implement Max Stack with following features:
 *  
 *  push() , pop() --> same as regular stack
 *  popMax()       --> pop the max value in stack
 *  peekMax()      --> get the max value in stack
 */
import java.util.*;
class StackNode implements Comparable<StackNode> {
    int value;
    StackNode prev;
    StackNode next;
    public StackNode(int val) {
        value = val;
        prev = next = null;
    }
    
    public int compareTo(StackNode node) {
        return node.value - this.value;
    }
}
public class MaxStack {
    private StackNode head;
    private PriorityQueue<StackNode> pq;
    
    public MaxStack() {
        pq = new PriorityQueue<>();
        head = new StackNode(-1);
    }
    
    public void push(int val) {
        StackNode node = new StackNode(val);
        node.next = head;
        head.prev = node;
        head = node;
        
        pq.add(node);
        
    }
    
    public int pop() {
        if (head.next == null)
            throw new NoSuchElementException();
        StackNode node = head;
        head = head.next;
        node.next = null;
        head.prev = null;
        
        pq.remove(node);
        return node.value;
    }
    
    public int peek() {
        if (head.next == null)
            throw new NoSuchElementException();
        
        return head.value;
        
    }
    
    public int peekMax() {
        if (pq.isEmpty())
            throw new NoSuchElementException();
        return pq.peek().value;
    }
    
    public int popMax() {
        if (pq.isEmpty())
            throw new NoSuchElementException();
        StackNode node = pq.poll();
        if (head == node) {
            head = node.next;
            node.next = null;
            
        } else {
           node.prev.next = node.next;
           node.next.prev = node.prev;
        }
        
        return node.value;
    }
    
    public static void main(String args[]) {
        MaxStack stack = new MaxStack();
        
        stack.push(2);
        stack.push(1);
        System.out.println("Max peek after 2->1 " + stack.peekMax());
        stack.push(6);
        stack.push(5);
        stack.push(5);
        System.out.println("Max peek after 2->1->6->5->5 " + stack.popMax() + " now max:"+stack.peekMax());
        stack.popMax();
        System.out.println("Max peek after pop 6,5:  " + stack.peekMax());
        stack.pop();
        System.out.println("Max peek  " + stack.peekMax()+ " stack peek " + stack.peek());
    }

}
