package bgu.spl.net.impl.objects;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class User {
    private static long nextId = 0;
    private static synchronized long allocateNewId() {
        return nextId++;
    }
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private final long id;
    private final String username;
    private final String password;
    private final List<Post> posts;
    private long lastFetchingTime;
    private final List<User> following;
    private final List<User> followers;
    private final Map<User, Boolean> blocked;
    private final Object followersLock;
    private final Object followingLock;
    private final BlockingQueue<Post> unsentPostsIWasTaggedIn;
    private final List<PrivateMessage> inbox;
    private final Queue<AbstractContent> unpushedContent;
    private final Date birthday;

    public User(String username, String password, String birthday) throws ParseException {
        this.id = allocateNewId();
        this.username = username;
        this.password = password;
        this.birthday = dateFormat.parse(birthday);
        this.posts = new LinkedList<>();
        this.lastFetchingTime = System.currentTimeMillis();
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.blocked = new HashMap<>();
        this.followersLock = new Object();
        this.followingLock = new Object();
        this.unsentPostsIWasTaggedIn = new LinkedBlockingQueue<>();
        this.inbox = new ArrayList<>();
        this.unpushedContent = new ConcurrentLinkedQueue<>();
    }

    public Queue<AbstractContent> getUnpushedContent() {
        return unpushedContent;
    }

    public long getId() {
        return id;
    }

    public boolean sendPM(User to, String content, String sendDateTime) {
        if(this.hasBlocked(to) || to.hasBlocked(this))
            return false;
        to.addToInbox(new PrivateMessage(content, this, to, sendDateTime));
        return true;
    }

    private void addToInbox(PrivateMessage privateMessage) {
        synchronized (inbox) {
            inbox.add(0, privateMessage);
        }
    }

    public synchronized void fetchNewContent() {
        List<AbstractContent> newContent = new ArrayList<>();
        long methodStartTime = System.currentTimeMillis();

        for(User followingUser : following) {
            List<Post> unsentPosts = followingUser.fetchPosts(lastFetchingTime, methodStartTime);
            for(Post unsentPublicPost : unsentPosts) {
                if(!unsentPublicPost.wasSentTo(this)) {
                    newContent.add(unsentPublicPost);
                    unsentPublicPost.markAsSentTo(this);
                }
            }
        }

        List<PrivateMessage> unsentMessages = this.fetchPrivateMessages(lastFetchingTime, methodStartTime);
        newContent.addAll(unsentMessages);

        while (!unsentPostsIWasTaggedIn.isEmpty()) {
            Post post = unsentPostsIWasTaggedIn.poll();
            if(!post.wasSentTo(this) && !this.hasBlocked(post.getAuthor()) && !post.getAuthor().hasBlocked(this) &&
                lastFetchingTime <= post.getCreationTime() && post.getCreationTime() < methodStartTime) {
                newContent.add(post);
                post.markAsSentTo(this);
            }
        }

        newContent.sort(Comparator.comparing(AbstractContent::getCreationTime));
        unpushedContent.addAll(newContent);
        lastFetchingTime = methodStartTime;
    }

    public List<Post> fetchPosts(long fromTime, long toTime) {
        List<Post> relevantPosts = new LinkedList<>();
        for (Post post : posts) {
            if (fromTime <= post.getCreationTime())
                break;
            if(post.getCreationTime() < toTime)
                relevantPosts.add(post);
        }

        return relevantPosts;
    }

    public List<PrivateMessage> fetchPrivateMessages(long fromTime, long toTime) {
        List<PrivateMessage> relevantMessages = new LinkedList<>();
        for (PrivateMessage pm : inbox) {
            if (fromTime <= pm.getCreationTime())
                break;
            if(pm.getCreationTime() < toTime)
                relevantMessages.add(pm);
        }

        return relevantMessages;
    }

    public void post(String content) {
        posts.add(0, new Post(content, this));
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void tagInPost(Post post) {
        unsentPostsIWasTaggedIn.add(post);
    }

    public boolean follow(User other) {
        if(this.following.contains(other))
            return false;

        synchronized (this.followingLock) {
            if(!(this.hasBlocked(other) || other.hasBlocked(this))) // It's weird to check it every time - but we want to avoid bugs made due simultaneous follow/unfollow
                this.following.add(other);
            else
                return false;
        }

        synchronized (other.followersLock) {
            if(!(this.hasBlocked(other) || other.hasBlocked(this)))
                other.followers.add(this);
            else
                return false;
        }

        return true;
    }

    public void unfollow(User other) {
        synchronized (this.followingLock) {
            this.following.remove(other);
        }
        synchronized (other.followersLock) {
            other.followers.remove(this);
        }
    }

    public void block(User other) {
        blocked.put(other, true);
        this.unfollow(other);
        other.unfollow(this);
    }

    public boolean hasBlocked(User other) {
        return blocked.get(other) != null;
    }


}
