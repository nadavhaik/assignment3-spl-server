package bgu.spl.net.impl.objects;

import java.util.LinkedList;
import java.util.List;

public class Post extends ObjectWithCreationTime {
    private final String content;
    public Post(String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public List<User> getTaggedUsers() {
        List<User> taggedUsers = new LinkedList<>();
        // TODO: IMPLEMENT
        return taggedUsers;
    }
}
