[TOC]
## 设计题
### 设计推特
![](https://upload-images.jianshu.io/upload_images/10460153-1b10b9933e046449.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/10460153-b7e0fcb5839cc4e7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```java
class Twitter {
/*
    * 要求：
    * 1.postTweet(userId, tweetId): 创建一条新的推文
    * 2.getNewsFeed(userId): 检索最近的十条推文。
    *   每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
    * 3.follow(followerId, followeeId): 关注一个用户
    * 4.unfollow(followerId, followeeId): 取消关注一个用户
    *
    * 分析：
    * 1. 对于要求3、4，只需要维护我关注的人，不需要知道谁关注了我，为了删除和添加方便，
    *    将[我关注的人id列表]设计成HashSet,将[每个人和它对应的关注列表]设计成HashMap
    * 2. 将每个人的id和他的推文列表，依旧放在HashMap里。
    *    对于每个人的推文列表，只需按顺序添加，不用删除、修改等。选择链表存储即可(无需考虑扩容)
    *    检索最近的10条推文，需要将关注人和自己的推文列表合并，然后选出top10【多路合并】
    *    （利用优先级队列，存储推文时，应该存储一个时间戳字段，用于比较哪个先出列）。
    * * */

    /*
    * 推文类
    * */
    private class Tweet{
        private int id;
        private int timestamp; //时间戳
        private Tweet next; //下个推文指针

        public Tweet(int id, int timestamp) {
            this.id = id;
            this.timestamp = timestamp;
        }
    }
    //id和推文的关系
    private Map<Integer,Tweet> twitter;

    //id和他关注的用户列表的关系
    private Map<Integer, Set<Integer>> followings;

    //全局变量的时间戳
    private static int timestamp = 0;

    //合并k组推文的数据结构
    private static PriorityQueue<Tweet> maxHeap;

    /** Initialize your data structure here. */
    public Twitter() {
        twitter = new HashMap<>();
        followings = new HashMap<>();
        maxHeap = new PriorityQueue<>((o1,o2) -> o2.timestamp - o1.timestamp);
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        timestamp++;
        // 先判断是否存在该用户id
        if(twitter.containsKey(userId)){
            //将新消息和老消息合并，重新改变消息头
            Tweet oldhead = twitter.get(userId);
            Tweet newhead = new Tweet(tweetId,timestamp);
            newhead.next = oldhead;
            twitter.put(userId,newhead);
        }else {
            twitter.put(userId,new Tweet(tweetId,timestamp));
        }
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        //由于是全局使用的，使用之前要进行清空
        maxHeap.clear();
        // 如果自己发了推文，也要算上
        if(twitter.containsKey(userId)){
            maxHeap.offer(twitter.get(userId));
        }
        Set<Integer> followingList = followings.get(userId);
        if(followingList != null && followingList.size() > 0){
            for (Integer followingId : followingList) {
                Tweet tweet = twitter.get(followingId);
                if(tweet != null){
                    maxHeap.offer(tweet);
                }
            }
        }

        List<Integer> res = new ArrayList<>(10);
        int count = 0;
        while (!maxHeap.isEmpty() && count < 10){
            Tweet tweet = maxHeap.poll();
            res.add(tweet.id);

            // 将取出tweet推文的next加入。因为每个人的推文已有序，所以对堆里面存取头节点即可
            if(tweet.next != null){
                maxHeap.offer(tweet.next);
            }
            count++;
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        //被关注人 不能是自己
        if(followerId == followeeId){
            return;
        }
        //获取我自己的关注列表
        Set<Integer> followingList = followings.get(followerId);
        if(followingList == null){
            Set<Integer> init = new HashSet<>();
            init.add(followeeId);
            followings.put(followerId,init);
        }else {
            if(followingList.contains(followeeId)){
                return;
            }
            followingList.add(followeeId);
        }
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followeeId == followerId){
            return;
        }
        Set<Integer> followingList = followings.get(followerId);
        if(followingList == null){
            return;
        }
        //删除之前无需再进行判断，查到后直接删除
        followingList.remove(followeeId);
    }
}
```