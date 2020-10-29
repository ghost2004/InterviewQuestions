package DoorDash;

/*
 * Leetcode 1472. Design Browser History
 * 
 * You have a browser of one tab where you start on the homepage and you can visit another url, get back in the history number of 
 * steps or move forward in the history number of steps.

Implement the BrowserHistory class:

BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
void visit(string url) Visits url from the current page. It clears up all the forward history.
string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x, you will return only
 x steps. Return the current url after moving back in history at most steps.
string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and steps > x, you will forward 
only x steps. Return the current url after forwarding in history at most steps.
 
 */
import java.util.*;

public class BrowserHistory {
    private Stack<String> _history;
    private Stack<String> _future;
    public BrowserHistory(String homepage) {
        _history = new Stack<>();
        _future = new Stack<>();
        _history.push(homepage);
    }
    
    public void visit(String url) {
        _history.push(url);
        _future.clear();
        
    }
    
    public String back(int steps) {
        while (steps > 0 && _history.size() > 1) {
            _future.push(_history.pop());
            steps--;
        }
        return _history.peek();
    }
    
    public String forward(int steps) {
        while (steps > 0 && _future.size() > 0) {
            _history.push(_future.pop());
            steps--;
        }
        return _history.peek();
    }

}
