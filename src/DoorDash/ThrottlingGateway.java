package DoorDash;
/*
 * The gateway has the following limits:
1) The number of transactions in any given second cannot exceed 3.
2) The number of transactions in any given 10 second period cannot exceed 20. 
  A ten-second period includes all requests arriving from any time max(1, T-9) to T (inclusive of both) for any valid time T.
3) The number of transactions in any given minute cannot exceed 60. 
  Similar to above, 1 minute is from max(1, T-59) to T.
Any request that exceeds any of the above limits will be dropped by the gateway. Given the times at which different requests arrive sorted ascending, 
find how many requests will be dropped.

Note: Even if a request is dropped it is still considered for future calculations. Although, if a request is to be dropped due to multiple violations,
 it is still counted only once.
Example
n = 27
requestTime = [1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 11, 11, 11, 11 ]
Request 1 - Not Dropped.
Request 1 - Not Dropped.
Request 1 - Not Dropped.
Request 1 - Dropped. At most 3 requests are allowed in one second.
No request will be dropped till 6 as all comes at an allowed rate of 3 requests per second and the 10-second clause is also not violated.
Request 7 - Not Dropped. The total number of requests has reached 20 now.
Request 7 - Dropped. At most 20 requests are allowed in ten seconds.
Request 7 - Dropped. At most 20 requests are allowed in ten seconds.
Request 7 - Dropped. At most 20 requests are allowed in ten seconds. Note that the 1-second limit is also violated here.
Request 11 - Not Dropped. The 10-second window has now become 2 to 11. Hence the total number of requests in this window is 20 now.
Request 11 - Dropped. At most 20 requests are allowed in ten seconds.
Request 11 - Dropped. At most 20 requests are allowed in ten seconds.
Request 11 - Dropped. At most 20 requests are allowed in ten seconds. Also, at most 3 requests are allowed per second.

Hence, a total of 7 requests are dropped.
Function Description
Complete the droppedRequests function in the editor below.
droppedRequests has the following parameter(s):
int requestTime[n]: an ordered array of integers that represent the times of various requests
Returns
int: the total number of dropped requests
Constraints
1 <= n <= 106
1 <= requestTime[i] <= 109
Input Format For Custom Testing
Sample Case 0
Sample Input For Custom Testing
STDIN Function
----- --------
5 ¡ú requestTime[] size n = 5
1 ¡ú requestTime = [ 1, 1, 1, 1, 2 ]
1
1
1
2
Sample Output
1
Explanation
There are 4 requests that arrive at second 1. This exceeds the per second limit so one packet is dropped. No other limits are exceeded.
 */
import java.util.*;
public class ThrottlingGateway {
    class SecondBucket {
        int _timestamp;
        int _count;
        public SecondBucket(int timestamp, int count) {
            _timestamp = timestamp;
            _count = count;
        }
    }
    
    class TokenBucket {
        int _tokenCount;
        int _maxToken;
        int _refreshTime;
        
        LinkedList<SecondBucket> _bucketList;
        
        public TokenBucket(int refreshTime, int maxToken) {
            _bucketList = new LinkedList<>();
            _maxToken = maxToken;
            _tokenCount = 0;
            _refreshTime = refreshTime;
        }
        
        
        public int addRequest(SecondBucket bucket) {
            if (!_bucketList.isEmpty()) {
                SecondBucket first = _bucketList.getFirst();
                while (first != null && first._timestamp < bucket._timestamp - _refreshTime + 1) {
                    _tokenCount -= first._count;
                    _bucketList.removeFirst();
                    first = _bucketList.isEmpty() ? null :_bucketList.getFirst();
                }
            }
            
            _bucketList.addLast(bucket);
            _tokenCount += bucket._count;
                
            if (_tokenCount <= _maxToken)
                return 0;
            return Math.min(bucket._count, _tokenCount - _maxToken);
        }
        
    }
    
    public int droppedRequests(int[] requestTime) {
        if (requestTime == null || requestTime.length < 1)
            return 0;

        TokenBucket tenSecond = new TokenBucket(10, 20);
        TokenBucket oneMinute = new TokenBucket(60, 60);
        
        int drop = 0;
        int idx = 0; 
        int curTimestamp = requestTime[0];
        int curCount = 0;
        while (idx <  requestTime.length) {
            while (idx < requestTime.length && requestTime[idx] == curTimestamp) {
                curCount++;
                idx++;
            }
            SecondBucket bucket = new SecondBucket(curTimestamp, curCount);
            
            
            int drop_1 = Math.max(0,  curCount - 3);
            int drop_2 = tenSecond.addRequest(bucket);
            int drop_3 = oneMinute.addRequest(bucket);
            //System.out.println(String.format("timestamp %d, counter: %d drop1:%d drop2:%d drop3:%d", curTimestamp, curCount, drop_1, drop_2, drop_3));
            
            drop += Math.max(drop_3, Math.max(drop_1, drop_2));
            
            if (idx < requestTime.length) {
                curCount = 0;
                curTimestamp = requestTime[idx];
            }
        }
        
        return drop;
    }
    
    public static void main(String args[]) {
        ThrottlingGateway g = new ThrottlingGateway();
        
        int test1[] = {1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 11, 11, 11, 11};
        System.out.println(g.droppedRequests(test1));
        int test2[] = {1, 1, 1, 1, 2 };
        System.out.println(g.droppedRequests(test2));
        int test3[] = {1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 11, 11, 11, 11, 71};
        System.out.println(g.droppedRequests(test3));
        int test4[] = {1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 11, 11, 11, 11,
                12, 13, 14, 14, 14, 15, 15, 15, 16, 16};
        System.out.println(g.droppedRequests(test4));
        int test5[] = {1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 11, 11, 11, 11,
                22, 23, 24, 24, 24, 25, 25, 25, 26, 26};
        System.out.println(g.droppedRequests(test5));
        int test6[] = {1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 11, 11, 11, 11,
                22, 23, 24, 24, 24, 25, 25, 25, 26, 26, 27, 27, 27, 28, 28, 28, 29, 29, 29,
                41, 41, 41, 42, 42, 42, 43, 43, 43,
                51, 51, 51, 52, 52, 53, 53, 54, 55, 56};
        System.out.println(g.droppedRequests(test6));
        int test7[] = {1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2};
        System.out.println(g.droppedRequests(test7));
        int test8[] = {1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 11, 11, 11, 11,
                22, 23, 24, 24, 24, 25, 25, 25, 26, 26, 27, 27, 27, 28, 28, 28, 29, 29, 29,
                41, 41, 41, 42, 42, 42, 43, 43, 43,
                51, 51, 51, 52, 52, 60, 61};
        System.out.println(g.droppedRequests(test8));
    }
}
