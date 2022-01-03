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
        connections.get(connectionId).send(msg);
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
