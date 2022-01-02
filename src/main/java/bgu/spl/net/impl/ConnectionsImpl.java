package bgu.spl.net.impl;

import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.impl.objects.User;
import bgu.spl.net.srv.ConnectionHandler;

import java.util.HashMap;
import java.util.List;

public class ConnectionsImpl<T> implements Connections<T> {
    private HashMap<User, ConnectionHandler> connectionHandlerList;

    @Override
    public boolean send(int connectionId, T msg) {
        // TODO: IMPLEMENT
        return false;
    }

    @Override
    public void broadcast(T msg) {
        // TODO: IMPLEMENT
    }

    @Override
    public void disconnect(int connectionId) {
        // TODO: IMPLEMENT
    }
}
