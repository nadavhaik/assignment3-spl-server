package bgu.spl.net.api.impl;

import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.srv.ConnectionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionsImpl<T> implements Connections<T> {
    private static int nextId = 0;
    private static synchronized int generateNextId() {
        return nextId++;
    }

    private final ConcurrentHashMap<Integer, ConnectionHandler<T>> connections;

    public ConnectionsImpl() {
        this.connections = new ConcurrentHashMap<>();
    }

    public int addConnection(ConnectionHandler<T> connectionHandler) {
        int id = generateNextId();
        connections.put(id, connectionHandler);
        return id;
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
        for(Map.Entry<Integer, ConnectionHandler<T>> entry : connections.entrySet())
            entry.getValue().send(msg);
    }

    @Override
    public void disconnect(int connectionId) {
        // TODO: IMPLEMENT
    }
}
