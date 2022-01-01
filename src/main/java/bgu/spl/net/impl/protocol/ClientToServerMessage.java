package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;

import java.util.ArrayList;

public abstract class ClientToServerMessage extends AbstractProtocolMessage {
    public ClientToServerMessage(ArrayList<Byte> message) throws ProtocolException {
        super();
        decode(message);
    }

    abstract void decode(ArrayList<Byte> message) throws ProtocolException;
    abstract void execute() throws ProtocolException;
    abstract ServerToClientMessage executeAndRespond();
}
