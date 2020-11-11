package DoorDash;

/*
 * Leetcode 355. Design Twitter
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets 
 * in the user's news feed. Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user 
followed or by the user herself. Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
Example:

Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);
 */

import java.util.*;
public class Twitter {
    private static int timeStamp = 0;
    private Map<Integer, User> userMap;
    private class Tweet {
        int _tweetId;
        int _timestamp;
        Tweet _next;
        public Tweet(int id) {
            _tweetId = id;
            _timestamp = timeStamp++;
            _next = null;
        }
        
    }
    private class User {
        int _userId;
        Tweet _tweetHead;
        Set<Integer> _following;
        
        public User(int id) {
            _userId = id;
            _tweetHead = null;
            _following = new HashSet<>();
            _following.add(id);
        }
        
        public void post(int id) {
            Tweet tweet = new Tweet(id);
            tweet._next = _tweetHead;
            _tweetHead = tweet;
        }
        
        public void follow(int id) {
            _following.add(id);
        }
        
        public void unfollow(int id) {
            _following.remove(id);
        }
    }
    
    /** Initialize your data structure here. */
    public Twitter() {
        userMap = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        User user = userMap.getOrDefault(userId, new User(userId));
        user.post(tweetId);
        userMap.put(userId, user);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        User user = userMap.get(userId);
        if (user == null)
            return result;
        Set<Integer> users = user._following;
        PriorityQueue<Tweet> pq = new PriorityQueue<>( (a, b) -> b._timestamp - a._timestamp);
        
        for (int userid : users) {
            Tweet tweet = userMap.get(userid)._tweetHead;
            if (tweet != null)
                pq.add(tweet);
        }
        int count = 0;
        while (!pq.isEmpty() && count < 10) {
            Tweet tweet = pq.poll();
            result.add(tweet._tweetId);
            tweet = tweet._next;
            if (tweet != null)
                pq.add(tweet);
            count++;
        }
        
        return result;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
       
        User follower = userMap.getOrDefault(followerId, new User(followerId));
        User followee = userMap.getOrDefault(followeeId, new User(followeeId));
        follower.follow(followeeId);
        userMap.put(followerId, follower);
        userMap.put(followeeId, followee);
        
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId==followeeId)
            return;
        User follower = userMap.get(followerId);
        if (follower != null)
            follower.unfollow(followeeId);
    }

}
