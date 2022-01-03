package bgu.spl.net.api.impl;

import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.srv.ConnectionHandler;

import java.util.HashMap;

public class ConnectionsImpl<T> implements Connections<T> {
    private final HashMap<Integer, ConnectionHandler<T>> connections;
    public ConnectionsImpl() {
        connections = new HashMap<>();
    }

    @Override
    public boolean send(int connectionId, T msg) {
        ConnectionHandler<T> conn = connections.get(connectionId);
        if(conn == null)
            return false;
        conn.send(msg);
        return true;
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
