package bgu.spl.net.impl.objects;

import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerData {
    private static ServerData instance = null;
    private final Map<String, User> users;

    private ServerData() {
        users = new ConcurrentHashMap<>();
    }

    public static ServerData getInstance() {
        if(instance == null)
            instance = new ServerData();
        return instance;
    }

    public synchronized boolean register(String username, String password, String birthday) {
        if(users.get(username) != null)
            return false;
        try {
            users.put(username, new User(username, password, birthday));
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    public User getUser(String username) {
        return users.get(username);
    }

}
