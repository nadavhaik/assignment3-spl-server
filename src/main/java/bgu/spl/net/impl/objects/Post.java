package bgu.spl.net.impl.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class Post extends AbstractContent {
    private final HashMap<User, Boolean> sentTo;
    public Post(String content, User author) {
        super(author, content, false);
        this.sentTo = new HashMap<>();
        this.author = author;
        tagUsers();
    }


    public void markAsSentTo(User user) {
        synchronized (sentTo) {
            sentTo.put(user, true);
        }
    }

    public boolean wasSentTo(User user) {
        return sentTo.get(user) != null;
    }

    public void tagUsers() {
        List<String> potentialUsernames = new ArrayList<>();
        int start, end;
        for(start = content.indexOf('@'), end = content.indexOf(' ', start);
            start != -1 && end != -1;
            start = content.indexOf('@', end), end = content.indexOf(' ', start)) {

            potentialUsernames.add(content.substring(start, end));
        }
        if(start != -1) // content ends with a tag, something like: "blah blah blah...@<username>"
            potentialUsernames.add(content.substring(start, content.length()-1));

        for(String username : potentialUsernames) {
            User user = ServerData.getInstance().getUser(username);
            if(user != null)
                user.tagInPost(this);
        }
    }

}
