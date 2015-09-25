package Uber;

import java.util.*;
public class WebQos {
    private int interval;
    private LinkedList<Long> token_list;
    private long limit;
    
    public void foo() {
        
    }
    public WebQos (int interval, int limit) {
        this.interval = interval;
        token_list = new LinkedList<Long>();
        this.limit = limit;
        Long t = new Long(-1);
        for (int i = 0; i < limit; i++) {
            token_list.addLast(t);
        }
    }
    public int handle_request() {
        if (token_list.isEmpty())
            return -1;
        long now = System.currentTimeMillis();
        long next = token_list.peek();
        long sec = now/interval;
        if (now < next)
            return -1;
        
        token_list.removeFirst();
        foo();
        
        token_list.addLast(sec*interval+interval);
        return 0;
    }
    
}
