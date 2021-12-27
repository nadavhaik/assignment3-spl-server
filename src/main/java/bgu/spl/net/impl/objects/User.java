package bgu.spl.net.impl.objects;

import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class User {
    private final long id;
    private String username;
    private String password;
    private List<Post> posts;
    private final Object postsLock;
    private long lastPushingTime;
    private List<User> following;
    private List<User> followers;
    private List<User> blockedFollowers;
    private Object followersLock;
    private Object followingLock;



    public User(String username, String password) {
        this.id = 1; // TODO: Change
        this.username = username;
        this.password = password;
        this.posts = new LinkedList<>();
        this.postsLock = new Object();
        this.lastPushingTime = System.currentTimeMillis();
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.blockedFollowers = new ArrayList<>();
        this.followersLock = new Object();
        this.followingLock = new Object();
    }


    public List<Post> fetchPosts(long lastFetchingTime) {
        List<Post> relevantPosts = new LinkedList<>();
        for (Post post : posts) {
            if (post.getCreationTime() < lastFetchingTime)
                break;
            relevantPosts.add(post);
        }

        return relevantPosts;
    }

    public void addPost(String content) {
        posts.add(0, new Post(content));
    }

    public void addFollower(User user){
        synchronized (followersLock) {
            followers.add(user);
        }
    }

    public void addToFollowing(User user){
        synchronized (followingLock) {
            following.add(user);
        }
    }

    public void addToBlockedFollowers(User user){
        blockedFollowers.add(user);
    }


}
