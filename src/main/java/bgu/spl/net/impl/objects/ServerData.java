package bgu.spl.net.impl.objects;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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

    public synchronized boolean register(String username, String password, Date birthday) {
        if(users.get(username) != null)
            return false;
        users.put(username, new User(username, password, birthday));

        return true;
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public List<User> getAllUsers() {
        LinkedList<User> allUsers = new LinkedList<>();
        for(Map.Entry<String, User> entry : this.users.entrySet())
            allUsers.add(entry.getValue());
        return allUsers;
    }

}
