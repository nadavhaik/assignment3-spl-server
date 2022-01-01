package bgu.spl.net.impl.protocol;

import bgu.spl.net.impl.ProtocolException;
import bgu.spl.net.impl.objects.User;

import java.util.ArrayList;

public abstract class ServerToClientMessage extends AbstractProtocolMessage {
    protected User user;

    public ServerToClientMessage(ArrayList<Byte> message, User user) throws ProtocolException {
        this.user = user;
    }

    abstract byte[] encode();
}
